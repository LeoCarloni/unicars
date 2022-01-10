package edu.unifacef.unicars.models.cliente;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("clientes")
public class Cliente {

    @Id
    @Generated
    private String id;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String nome;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String cnh;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String telefone;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private Endereco endereco;
}
