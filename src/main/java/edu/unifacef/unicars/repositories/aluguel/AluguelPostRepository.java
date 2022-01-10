package edu.unifacef.unicars.repositories.aluguel;

import edu.unifacef.unicars.models.aluguel.AluguelPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AluguelPostRepository extends MongoRepository<AluguelPost, String> {
}
