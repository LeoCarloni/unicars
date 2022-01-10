package edu.unifacef.unicars.repositories.cliente;

import edu.unifacef.unicars.models.cliente.ClientePost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientePostRepository extends MongoRepository<ClientePost, String> {
}
