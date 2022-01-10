package edu.unifacef.unicars.repositories.carro;

import edu.unifacef.unicars.models.carro.Carro;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarroRepository extends MongoRepository<Carro, String> {
    public Optional<Carro> findByIdentificador(String identificador);
}
