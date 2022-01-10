package edu.unifacef.unicars.models.carro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;

@Data
public class CarroAluguel {

    @Id
    @JsonIgnore
    private String id;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String identificador;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private Classificacao classificacao;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String modelo;
}
