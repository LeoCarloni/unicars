package edu.unifacef.unicars.models.cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;

@Data
public class ClienteAluguel {
    @Id
    @JsonIgnore
    private String id;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String nome;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String cnh;
}
