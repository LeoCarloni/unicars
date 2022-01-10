package edu.unifacef.unicars.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private List<String> errors = new ArrayList<>();

    public void adicionaErros(String erro){
        errors.add(erro);
    }

}