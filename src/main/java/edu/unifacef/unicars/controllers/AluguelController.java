package edu.unifacef.unicars.controllers;

import edu.unifacef.unicars.exceptions.BadRequestException;
import edu.unifacef.unicars.exceptions.NotFoundException;
import edu.unifacef.unicars.models.aluguel.Aluguel;
import edu.unifacef.unicars.models.aluguel.AluguelPost;
import edu.unifacef.unicars.models.aluguel.AluguelPut;
import edu.unifacef.unicars.models.carro.Carro;
import edu.unifacef.unicars.models.cliente.Cliente;
import edu.unifacef.unicars.repositories.aluguel.AluguelRepository;
import edu.unifacef.unicars.repositories.aluguel.AluguelPutRepository;
import edu.unifacef.unicars.repositories.aluguel.AluguelPostRepository;
import edu.unifacef.unicars.repositories.carro.CarroRepository;
import edu.unifacef.unicars.repositories.cliente.ClienteRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/alugueis")
public class AluguelController {

    @Autowired
    private AluguelPostRepository aluguelPostRepository;

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private AluguelPutRepository aluguelPutRepository;

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @ApiOperation("Cadastra um aluguel")
    public void create(@Valid @RequestBody AluguelPost aluguelPost){

        String identificador = aluguelPost.getCarro().getIdentificador();
        String cnh = aluguelPost.getCliente().getCnh();
        Optional<Carro> carro = this.carroRepository.findByIdentificador(identificador);
        Optional<Cliente> cliente = this.clienteRepository.findByCnh(cnh);

        if(cliente.isPresent()){
            if(carro.isPresent()){
                Boolean disponivel = carro.get().getDisponivel();
                Boolean ativo = carro.get().getAtivo();

                if(!disponivel){
                    throw new BadRequestException("Carro não está disponivel");
                }
                if(!ativo){
                    throw new BadRequestException("Carro não está ativo");
                }

                Carro _carro = carro.get();
                _carro.setDisponivel(false);
                carroRepository.save(_carro);

                this.aluguelPostRepository.save(aluguelPost);
            }
            else{
                throw new NotFoundException("Carro não cadastrado");
            }
        } else {
            throw new NotFoundException("Cliente não cadastrado");
        }
    }

    @GetMapping()
    @ApiOperation("Retorna todos os alugueis")
    public PageResponse<Aluguel> findAll(Integer page, Integer size){
        Page<Aluguel> pageAlugueis = aluguelRepository.findAll(PageRequest.of(page, size));
        return new PageResponse(pageAlugueis);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Retorna um aluguel")
    public Aluguel find(@PathVariable("id") String id){
        return this.aluguelRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Aluguel não encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualiza um aluguel")
    public Aluguel update(@PathVariable("id") String id, @RequestBody AluguelPut aluguel) {

        Optional<Aluguel> aluguelData = aluguelRepository.findById(id);

        if (aluguelData.isPresent()) {
            String identificador = aluguelData.get().getCarro().getIdentificador();
            Optional<Carro> carro = this.carroRepository.findByIdentificador(identificador);

            Aluguel _aluguel = aluguelData.get();

            _aluguel.setOdometro_inicial(aluguelData.get().getOdometro_inicial());
            _aluguel.setCliente(aluguelData.get().getCliente());
            _aluguel.setCarro(aluguelData.get().getCarro());
            _aluguel.setRetirada(aluguelData.get().getRetirada());

            if(aluguel.getDevolucao() == null){
                throw new BadRequestException("Data da devolução deve ser informada!");
            }
            if(aluguel.getOdometro_final() == null){
                throw new BadRequestException("O valor do odometro deve ser informada!");
            }
            if(aluguel.getTanque() == null){
                throw new BadRequestException("A quantidade do tanque deve ser informada!");
            }

            _aluguel.setDevolucao(aluguel.getDevolucao());
            _aluguel.setOdometro_final(aluguel.getOdometro_final());
            _aluguel.setTanque(aluguel.getTanque());

            Carro _carro = carro.get();
            _carro.setDisponivel(true);
            carroRepository.save(_carro);

            aluguelRepository.save(_aluguel);
            return aluguelData.get();

        } else {
            throw new NotFoundException("Aluguel não encontrado!");
        }
    }
}
