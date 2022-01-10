package edu.unifacef.unicars.controllers;

import edu.unifacef.unicars.exceptions.BadRequestException;
import edu.unifacef.unicars.exceptions.NotFoundException;
import edu.unifacef.unicars.models.carro.Classificacao;
import edu.unifacef.unicars.models.valor.Valor;
import edu.unifacef.unicars.repositories.valor.ValorRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "{classificacao}/valores")
public class ValorController {

    @Autowired
    private ValorRepository valorRepository;

    @PostMapping()
    @ApiOperation("Cadastra um valor")
    public void create(@Valid @RequestBody Valor valor, @PathVariable("classificacao")Classificacao classificacao){
        valor.setClassificacao(classificacao);
        Optional<Valor> exist = this.valorRepository.findByClassificacao(classificacao);
        if(exist.isPresent()){
            throw new NotFoundException("Já existe um valor cadastrado para essa classe!");
        } else{
            this.valorRepository.save(valor);
        }
    }

    @GetMapping()
    @ApiOperation("Retorna o valor da classificacao")
    public Optional<Valor> find(@PathVariable("classificacao") Classificacao classificacao){
        Optional<Valor> valor = this.valorRepository.findByClassificacao(classificacao);
        if(!valor.isPresent()){
            throw new NotFoundException("Valor não cadastrado ainda!");
        } else{
            return valor;
        }
    }

    @PutMapping()
    @ApiOperation("Atualiza um valor")
    public Valor update(@PathVariable("classificacao") Classificacao classificacao, @RequestBody Valor valor) {
        Optional<Valor> valorData = valorRepository.findByClassificacao(classificacao);

        if(valor.getValor() != null){
            if (valorData.isPresent()) {
                Valor _valor = valorData.get();
                _valor.setValor(valor.getValor());
                _valor.setClassificacao(_valor.getClassificacao());
                valorRepository.save(_valor);
                return valorData.get();
            } else {
                throw new NotFoundException("Valor dessa classificação ainda não foi cadastrado!");
            }
        } else{
            throw new BadRequestException("Valor não pode ser Nulo");
        }
    }
}
