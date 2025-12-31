package py.com.celeste.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.celeste.banco.domain.models.Cuenta;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    boolean existsByNumeroCuenta(String numeroCuenta);

}
