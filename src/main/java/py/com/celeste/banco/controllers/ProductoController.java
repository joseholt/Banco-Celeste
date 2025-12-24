package py.com.celeste.banco.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.celeste.banco.domain.models.Producto;
import py.com.celeste.banco.dto.request.ProductoRequestDTO;
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
    public ResponseEntity<List<Producto>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> guardar(@Valid @RequestBody ProductoRequestDTO producto) {
        productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
