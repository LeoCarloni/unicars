package edu.unifacef.unicars.models.aluguel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Document("alugueis")
public class AluguelPut {

    @Id
    @Generated
    @JsonIgnore
    private String id;

    @Indexed
    @NotNull(message = "Campo obrigatorio")
    private String odometro_final;

    @Indexed
    @NotNull(message = "Campo obrigatorio")
    private Date devolucao;

    @Indexed
    @NotNull(message = "Campo obrigatorio")
    private Tanque tanque;
}
