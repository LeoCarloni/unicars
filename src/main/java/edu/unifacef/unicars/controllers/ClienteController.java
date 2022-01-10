package edu.unifacef.unicars.controllers;

import edu.unifacef.unicars.exceptions.BadRequestException;
import edu.unifacef.unicars.exceptions.NotFoundException;
import edu.unifacef.unicars.models.cliente.Cliente;
import edu.unifacef.unicars.models.cliente.ClientePost;
import edu.unifacef.unicars.repositories.cliente.ClientePostRepository;
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
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClientePostRepository clientePostRepository;

    @PostMapping
    @ApiOperation("Cadastra um cliente")
    public void create(@Valid @RequestBody ClientePost cliente){
        String cnh = cliente.getCnh();
        Optional<Cliente> clienteExist = this.clienteRepository.findByCnh(cnh);
        if(clienteExist.isEmpty()){
            this.clientePostRepository.save(cliente);
        } else{
            throw new BadRequestException("Cliente já cadastrado");
        }
    }

    @GetMapping()
    @ApiOperation("Retorna todos os clientes")
    public PageResponse<Cliente> findAll(Integer page, Integer size){
        Page<Cliente> pageClientes = clienteRepository.findAll(PageRequest.of(page, size));
        return new PageResponse(pageClientes);
    }

    @GetMapping(value = "/{cnh}")
    @ApiOperation("Retorna um cliente")
    public Cliente find(@PathVariable("cnh") String cnh){
        return this.clienteRepository
                .findByCnh(cnh)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Atualiza um cliente")
    public ClientePost update(@PathVariable("id") String id, @RequestBody ClientePost cliente){

        Optional<ClientePost> clienteData = clientePostRepository.findById(id);

        if(clienteData.isPresent()){
            ClientePost _cliente = clienteData.get();

            if (cliente.getNome() != null){
                _cliente.setNome(cliente.getNome());
            }

            if (cliente.getCnh() != null){
                Optional<Cliente> clienteExist = this.clienteRepository.findByCnh(cliente.getCnh());
                if(clienteExist.isEmpty()){
                    _cliente.setCnh(cliente.getCnh());
                } else{
                    throw new BadRequestException("CNH já cadastrada");
                }
            }

            if (cliente.getTelefone() != null){
                _cliente.setTelefone(cliente.getTelefone());
            }

            if (cliente.getEndereco() != null){
                if(cliente.getEndereco().getCep() == null){
                    throw new BadRequestException("CEP deve ser informado!");
                }
                if(cliente.getEndereco().getRua() == null){
                    throw new BadRequestException("Rua deve ser informado!");
                }
                if(cliente.getEndereco().getNumero() == null){
                    throw new BadRequestException("Numero deve ser informado!");
                }
                if(cliente.getEndereco().getBairro() == null){
                    throw new BadRequestException("Bairro deve ser informado!");
                }
                if(cliente.getEndereco().getCidade() == null){
                    throw new BadRequestException("Cidade deve ser informada!");
                }
                if(cliente.getEndereco().getEstado() == null){
                    throw new BadRequestException("Estado deve ser informado!");
                }
                _cliente.setEndereco(cliente.getEndereco());
            }
            clientePostRepository.save(_cliente);
            return clienteData.get();

        } else{
            throw new NotFoundException("Cliente não encontrado!");
        }
    }

}
