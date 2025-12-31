package py.com.celeste.banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.celeste.banco.domain.models.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}
