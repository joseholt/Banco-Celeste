package py.com.celeste.banco.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import py.com.celeste.banco.domain.enums.TipoProducto;

public class ProductoRequestDTO {

    @NotBlank(message = "El código no puede estar vacío")
    @Size(max = 20, message = "El código no puede tener más de 20 caracteres")
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El tipo de producto es obligatorio")
    private TipoProducto tipo;

    @NotBlank(message = "La moneda no puede estar vacía")
    @Size(min = 3, max = 3, message = "La moneda debe tener exactamente 3 caracteres")
    private String moneda;

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
}
