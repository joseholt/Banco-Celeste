package py.com.celeste.banco.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.celeste.banco.domain.models.Cliente;
import py.com.celeste.banco.dto.request.ClienteRequestDTO;
import py.com.celeste.banco.services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody ClienteRequestDTO cliente) {
        clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

}
