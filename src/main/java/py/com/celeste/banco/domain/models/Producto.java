package py.com.celeste.banco.domain.models;

import jakarta.persistence.*;

import py.com.celeste.banco.domain.enums.TipoProducto;

import java.util.Objects;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoProducto tipo;

    @Column(nullable = false, length = 3)
    private String moneda;

    @Column(nullable = false)
    private Boolean activo;

    public Producto() {
    }

    public Producto(Long id, String codigo, String nombre, TipoProducto tipo, String moneda, Boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.moneda = moneda;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", moneda='" + moneda + '\'' +
                ", activo=" + activo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto producto)) return false;
        return Objects.equals(getId(), producto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
