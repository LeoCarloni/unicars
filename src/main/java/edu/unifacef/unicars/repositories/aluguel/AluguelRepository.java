package edu.unifacef.unicars.repositories.aluguel;

import edu.unifacef.unicars.models.aluguel.Aluguel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AluguelRepository extends MongoRepository<Aluguel, String> {
}
