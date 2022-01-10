package edu.unifacef.unicars.repositories.carro;

import edu.unifacef.unicars.models.carro.CarroAluguel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarroAluguelRepository extends MongoRepository<CarroAluguel, String> {
}
