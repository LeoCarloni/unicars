package edu.unifacef.unicars.repositories.valor;

import edu.unifacef.unicars.models.carro.Classificacao;
import edu.unifacef.unicars.models.valor.Valor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ValorRepository extends MongoRepository<Valor, String> {
    public Optional<Valor> findByClassificacao(Classificacao classificacao);
}
