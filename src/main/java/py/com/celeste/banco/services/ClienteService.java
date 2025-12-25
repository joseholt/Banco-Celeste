package py.com.celeste.banco.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.com.celeste.banco.domain.exceptions.BusinessException;
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

    @Transactional
    public Cliente guardar(ClienteRequestDTO dto) {
        if (clienteRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessException("El email ya está en uso");
        }
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

    @Transactional
    public Cliente actualizar(Long id, ClienteRequestDTO dto) {
        Cliente existente = buscarPorId(id);

        existente.setNombre(dto.getNombre());
        existente.setEmail(dto.getEmail());

        return clienteRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        Cliente existente = buscarPorId(id);
        clienteRepository.delete(existente);
    }

}
