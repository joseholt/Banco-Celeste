package py.com.celeste.banco.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.celeste.banco.domain.enums.EstadoCuenta;
import py.com.celeste.banco.domain.exceptions.BusinessException;
import py.com.celeste.banco.domain.models.Cliente;
import py.com.celeste.banco.domain.models.Cuenta;
import py.com.celeste.banco.repositories.CuentaRepository;

import java.math.BigDecimal;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteService clienteService;

    public CuentaService(CuentaRepository cuentaRepository, ClienteService clienteService) {
        this.cuentaRepository = cuentaRepository;
        this.clienteService = clienteService;
    }

    @Transactional
    public Cuenta abrirCuenta(Long clienteId, String numeroCuenta, String moneda) {

        Cliente cliente = clienteService.buscarPorId(clienteId);

        if (cuentaRepository.existsByNumeroCuenta(numeroCuenta)) {
            throw new BusinessException("El número de cuenta ya existe");
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(numeroCuenta);
        cuenta.setCliente(cliente);
        cuenta.setSaldo(BigDecimal.ZERO);
        cuenta.setMoneda(moneda);
        cuenta.setEstado(EstadoCuenta.ACTIVA);

        return cuentaRepository.save(cuenta);
    }

}
