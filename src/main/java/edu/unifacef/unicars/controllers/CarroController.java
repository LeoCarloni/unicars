package edu.unifacef.unicars.controllers;

import edu.unifacef.unicars.exceptions.BadRequestException;
import edu.unifacef.unicars.exceptions.NotFoundException;
import edu.unifacef.unicars.models.carro.Carro;
import edu.unifacef.unicars.models.carro.CarroPost;
import edu.unifacef.unicars.models.carro.CarroPut;
import edu.unifacef.unicars.repositories.carro.CarroPutRepository;
import edu.unifacef.unicars.repositories.carro.CarroRepository;
import edu.unifacef.unicars.repositories.carro.CarroPostRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private CarroPostRepository carroPostRepository;

    @Autowired
    private CarroPutRepository carroPutRepository;

    @PostMapping()
    @ApiOperation("Cadastra um carro")
    public void create(@Valid @RequestBody CarroPost carroPost){

        String identificador = carroPost.getIdentificador();
        Optional<Carro> carro = this.carroRepository.findByIdentificador(identificador);

        if(carro.isEmpty()){
            carroPost.setDisponivel(true);
            carroPost.setAtivo(true);
            this.carroPostRepository.save(carroPost);
        } else{
            throw new BadRequestException("Identificador já cadastrado");
        }
    }

    @GetMapping()
    @ApiOperation("Retorna todos os carros")
    public PageResponse<Carro> findAll(Integer page, Integer size){
        Page<Carro> pageCarros = carroRepository.findAll(PageRequest.of(page, size));
        return new PageResponse(pageCarros);
    }

    @GetMapping(value = "/{identificador}")
    @ApiOperation("Retorna um carro")
    public Carro find(@PathVariable("identificador") String identificador){
        return this.carroRepository
                .findByIdentificador(identificador)
                .orElseThrow(() -> new NotFoundException("Carro não encontrado"));
    }

    @PutMapping(value = "/{identificador}")
    @ApiOperation("Atualiza um carro")
    public ResponseEntity<CarroPut> update(@PathVariable("identificador") String identificador, @RequestBody CarroPut carro){

        Optional<CarroPut> carroData = carroPutRepository.findByIdentificador(identificador);
        if(carroData.isPresent()){

            CarroPut _carro = carroData.get();

            _carro.setIdentificador(identificador);

            if (carro.getClassificacao() != null){
                _carro.setClassificacao(carro.getClassificacao());
            }
            if (carro.getMarca() != null){
                _carro.setMarca(carro.getMarca());
            }
            if (carro.getModelo() != null){
                _carro.setModelo(carro.getModelo());
            }
            if (carro.getAno() != null){
                _carro.setAno(carro.getAno());
            }
            if (carro.getCor() != null){
                _carro.setCor(carro.getCor());
            }
            if (carro.getPlaca() != null){
                _carro.setPlaca(carro.getPlaca());
            }
            if (carro.getAtivo() != null){
                //Não deixa desativar um carro que está alugado
                if(!carro.getAtivo() && !carroData.get().getDisponivel()){
                    throw new BadRequestException("O carro informado não está disponivel!");
                } else{
                    _carro.setAtivo(carro.getAtivo());
                }
            }
            if (carro.getDisponivel() != null){
                _carro.setDisponivel(carro.getDisponivel());
            }
            return new ResponseEntity<>(carroPutRepository.save(_carro), HttpStatus.OK);

        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
