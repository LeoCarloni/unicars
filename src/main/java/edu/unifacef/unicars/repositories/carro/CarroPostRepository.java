package edu.unifacef.unicars.repositories.carro;

import edu.unifacef.unicars.models.carro.CarroPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarroPostRepository extends MongoRepository<CarroPost, String> {
}
