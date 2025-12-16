package py.com.celeste.banco.controllers;

import org.springframework.web.bind.annotation.*;
import py.com.celeste.banco.domain.models.Producto;
import py.com.celeste.banco.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> findAll() {
        return productoService.findAll();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

}
