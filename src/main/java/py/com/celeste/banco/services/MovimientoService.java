package py.com.celeste.banco.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.celeste.banco.domain.enums.EstadoCuenta;
import py.com.celeste.banco.domain.enums.TipoMovimiento;
import py.com.celeste.banco.domain.exceptions.BusinessException;
import py.com.celeste.banco.domain.models.Cuenta;
import py.com.celeste.banco.domain.models.Movimiento;
import py.com.celeste.banco.repositories.CuentaRepository;
import py.com.celeste.banco.repositories.MovimientoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public MovimientoService(CuentaRepository cuentaRepository,
                             MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public Movimiento registrarMovimiento(
            Long cuentaId,
            BigDecimal monto,
            TipoMovimiento tipo,
            String descripcion) {

        Cuenta cuenta = cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> new BusinessException("La cuenta no existe"));

        if (tipo == TipoMovimiento.DEBITO) {

            if (cuenta.getEstado() != EstadoCuenta.ACTIVA) {
                throw new BusinessException("La cuenta no está activa");
            }

            if (cuenta.getSaldo().compareTo(monto) < 0) {
                throw new BusinessException("Saldo insuficiente");
            }

            cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        }

        if (tipo == TipoMovimiento.CREDITO) {
            cuenta.setSaldo(cuenta.getSaldo().add(monto));
        }

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimiento.setMonto(monto);
        movimiento.setTipo(tipo);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setDescripcion(descripcion);

        cuentaRepository.save(cuenta);
        return movimientoRepository.save(movimiento);
    }

    @Transactional
    public void transferir(Long cuentaOrigenId,
                           Long cuentaDestinoId,
                           BigDecimal monto,
                           String descripcion) {

        if (cuentaOrigenId.equals(cuentaDestinoId)) {
            throw new BusinessException("La cuenta origen y destino no pueden ser la misma");
        }

        Cuenta cuentaOrigen = cuentaRepository.findById(cuentaOrigenId)
                .orElseThrow(() -> new BusinessException("Cuenta origen no existe"));

        Cuenta cuentaDestino = cuentaRepository.findById(cuentaDestinoId)
                .orElseThrow(() -> new BusinessException("Cuenta destino no existe"));

        if (cuentaOrigen.getEstado() != EstadoCuenta.ACTIVA) {
            throw new BusinessException("La cuenta origen no está activa");
        }

        if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
            throw new BusinessException("Saldo insuficiente en la cuenta origen");
        }

        // Actualizar saldos
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));

        // Movimiento débito
        Movimiento debito = new Movimiento();
        debito.setCuenta(cuentaOrigen);
        debito.setMonto(monto);
        debito.setTipo(TipoMovimiento.DEBITO);
        debito.setFecha(LocalDateTime.now());
        debito.setDescripcion(descripcion);

        // Movimiento crédito
        Movimiento credito = new Movimiento();
        credito.setCuenta(cuentaDestino);
        credito.setMonto(monto);
        credito.setTipo(TipoMovimiento.CREDITO);
        credito.setFecha(LocalDateTime.now());
        credito.setDescripcion(descripcion);

        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        movimientoRepository.save(debito);
        movimientoRepository.save(credito);
    }

}
