package py.com.celeste.banco.services;

import org.springframework.stereotype.Service;
import py.com.celeste.banco.domain.models.Producto;
import py.com.celeste.banco.dto.request.ProductoRequestDTO;
import py.com.celeste.banco.repositories.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto save(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setTipo(dto.getTipo());
        producto.setMoneda(dto.getMoneda());
        producto.setActivo(true);
        return productoRepository.save(producto);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

}
