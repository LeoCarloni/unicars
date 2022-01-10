package edu.unifacef.unicars.repositories.cliente;

import edu.unifacef.unicars.models.cliente.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    public Optional<Cliente> findByCnh(String cnh);
}
