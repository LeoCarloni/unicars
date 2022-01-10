package edu.unifacef.unicars.repositories.cliente;

import edu.unifacef.unicars.models.cliente.ClienteAluguel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteAluguelRepository extends MongoRepository<ClienteAluguel, String> {
}
