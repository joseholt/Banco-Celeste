package py.com.celeste.banco.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.celeste.banco.domain.exceptions.BusinessException;
import py.com.celeste.banco.domain.exceptions.ResourceNotFoundException;
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

    @Transactional
    public Producto save(ProductoRequestDTO dto) {

        if (productoRepository.findByCodigo(dto.getCodigo()).isPresent()) {
            throw new BusinessException("El código del producto ya existe");
        }

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

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Producto no encontrado"));
    }

    @Transactional
    public Producto actualizar(Long id, ProductoRequestDTO dto) {
        Producto existente = buscarPorId(id);

        // Validar duplicado solo si cambia el código (Obs: Qué codigo hermoso!)
        if (!existente.getCodigo().equals(dto.getCodigo()) &&
                productoRepository.findByCodigo(dto.getCodigo()).isPresent()) {
            throw new BusinessException("El código del producto ya existe");
        }

        existente.setCodigo(dto.getCodigo());
        existente.setNombre(dto.getNombre());
        existente.setTipo(dto.getTipo());
        existente.setMoneda(dto.getMoneda());

        return productoRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Producto existente = buscarPorId(id);
        productoRepository.delete(existente);
    }

}
