package py.com.celeste.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.celeste.banco.domain.models.Producto;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByCodigo(String codigo);

}
