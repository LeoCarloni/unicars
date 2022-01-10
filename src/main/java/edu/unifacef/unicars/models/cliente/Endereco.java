package edu.unifacef.unicars.models.cliente;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class Endereco {

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String cep;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String rua;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String numero;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String bairro;

    @Indexed
    private String complemento;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String cidade;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String estado;

}
