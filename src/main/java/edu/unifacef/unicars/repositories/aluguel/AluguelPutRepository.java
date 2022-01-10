package edu.unifacef.unicars.repositories.aluguel;

import edu.unifacef.unicars.models.aluguel.AluguelPut;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AluguelPutRepository extends MongoRepository<AluguelPut, String> {
}
