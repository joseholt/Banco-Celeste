package py.com.celeste.banco.services;

import org.springframework.stereotype.Service;
import py.com.celeste.banco.domain.models.Producto;
import py.com.celeste.banco.repositories.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto save(Producto p) {
        return productoRepository.save(p);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

}
