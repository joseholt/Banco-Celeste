package py.com.celeste.banco.services;

import org.springframework.stereotype.Service;
import py.com.celeste.banco.domain.exceptions.ResourceNotFoundException;
import py.com.celeste.banco.domain.models.Cliente;
import py.com.celeste.banco.dto.request.ClienteRequestDTO;
import py.com.celeste.banco.repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente guardar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente no encontrado"));
    }

}
