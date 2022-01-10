package edu.unifacef.unicars.models.valor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.unifacef.unicars.models.carro.Classificacao;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("valores")
public class Valor {

    @Id
    @Generated
    @JsonIgnore
    private String id;

    @Indexed
    @JsonIgnore
    private Classificacao classificacao;

    @NotNull(message = "Campo obrigatorio")
    private Double valor;
}
