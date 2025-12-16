package py.com.celeste.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.celeste.banco.domain.models.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

}
