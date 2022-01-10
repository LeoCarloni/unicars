package edu.unifacef.unicars.models.carro;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document("carros")
public class Carro {

    @Id
    @Generated
    private String id;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String identificador;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private Classificacao classificacao;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String marca;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String modelo;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String placa;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private Integer ano;

    @Indexed
    @NotNull(message = "Campo Obrigatorio")
    private String cor;

    @Indexed
    private Boolean disponivel;

    @Indexed
    private Boolean ativo;
}
