package pweb.roupa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pweb.roupa.controllers.exceptions.ResourceNotFoundException;
import pweb.roupa.entities.Cliente;
import pweb.roupa.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository cr;

    public List<Cliente> findAll() {
        return cr.findAll();
    }

    public Cliente findbyId(Long id) {
        Optional<Cliente> optional = cr.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id:" + id));
    }

    public Cliente save(Cliente cliente) {
        return cr.save(cliente);
    }

    public void delete(Long id) {
        if (cr.existsById(id)) {
            cr.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Cliente não encontrado com o id:" + id);
        }

    }

    public void update(Cliente cliente, Long id) {
        if (cr.existsById(id)) {
            cr.save(cliente);
        } else {
            throw new ResourceNotFoundException("Cliente não encontrado com o id:" + id);
        }

    }

}
