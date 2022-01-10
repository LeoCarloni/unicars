package edu.unifacef.unicars.messages;

import edu.unifacef.unicars.exceptions.BadRequestException;
import edu.unifacef.unicars.exceptions.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionCustomHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity<ErrorResponse> handlerValidationException(final MethodArgumentNotValidException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            errorResponse.adicionaErros(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public HttpEntity<ErrorResponse> handlerNotFoundException(final NotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.adicionaErros(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public HttpEntity<ErrorResponse> hanglerBadRequestException(final BadRequestException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.adicionaErros(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}