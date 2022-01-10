package edu.unifacef.unicars.models.aluguel;

import edu.unifacef.unicars.models.carro.CarroAluguel;
import edu.unifacef.unicars.models.cliente.ClienteAluguel;
import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Document("alugueis")
public class Aluguel {

    @Id
    @Generated
    private String id;

    @Indexed
    @NotNull(message = "Campo obrigatorio")
    private CarroAluguel carro;

    @Indexed
    @NotNull(message = "Campo obrigatorio")
    private ClienteAluguel cliente;

    @Indexed
    @NotNull(message = "Campo obrigatorio")
    private String odometro_inicial;

    @Indexed
    @NotNull(message = "Campo obrigatorio")
    private Date retirada;

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
