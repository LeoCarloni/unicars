package edu.unifacef.unicars.usecases;

import edu.unifacef.unicars.controllers.ValorController;
import edu.unifacef.unicars.models.carro.Classificacao;
import edu.unifacef.unicars.models.valor.Valor;
import edu.unifacef.unicars.repositories.valor.ValorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddValorTest {

    @InjectMocks
    private ValorController valorController;

    @Mock
    private ValorRepository valorRepository;

    @Test
    public void shouldCreateNewCategoryPrice(){
        Valor valorMock = new Valor();
        valorMock.setId("10");
        valorMock.setClassificacao(Classificacao.LUXO);
        valorMock.setValor(50.00);

        Valor expectedStoreValor =
                Valor.builder()
                        .id(valorMock.getId())
                        .classificacao(valorMock.getClassificacao())
                        .valor(valorMock.getValor())
                        .build();

        when(valorRepository.findByClassificacao(valorMock.getClassificacao())).thenReturn(Optional.empty());

        valorController.create(valorMock, valorMock.getClassificacao());
        verify(valorRepository).save(expectedStoreValor);
    }
}
