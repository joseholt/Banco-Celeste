package py.com.celeste.banco.domain.models;

import jakarta.persistence.*;
import py.com.celeste.banco.domain.enums.EstadoCuenta;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroCuenta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo;

    @Column(nullable = false)
    private String moneda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCuenta estado;

    public Cuenta() {
    }

    public Cuenta(Long id, String numeroCuenta, Cliente cliente, BigDecimal saldo, String moneda, EstadoCuenta estado) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.cliente = cliente;
        this.saldo = saldo;
        this.moneda = moneda;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cuenta cuenta)) return false;
        return Objects.equals(getId(), cuenta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", cliente=" + cliente +
                ", moneda='" + moneda + '\'' +
                ", estado=" + estado +
                '}';
    }
}
