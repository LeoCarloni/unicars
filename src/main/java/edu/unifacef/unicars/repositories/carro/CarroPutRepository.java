package edu.unifacef.unicars.repositories.carro;

import edu.unifacef.unicars.models.carro.Carro;
import edu.unifacef.unicars.models.carro.CarroPut;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarroPutRepository extends MongoRepository<CarroPut, String> {
    public Optional<CarroPut> findByIdentificador(String identificador);
}