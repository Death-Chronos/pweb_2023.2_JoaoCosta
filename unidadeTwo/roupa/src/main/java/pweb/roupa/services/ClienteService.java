package pweb.roupa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pweb.roupa.controllers.exceptions.ResourceNotFoundException;
import pweb.roupa.entities.Cliente;
import pweb.roupa.entities.Dependente;
import pweb.roupa.repositories.ClienteRepository;
import pweb.roupa.repositories.DependenteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository cr;

    @Autowired
    DependenteRepository dr;

    public List<Cliente> findAll() {
        return cr.findAll();
    }

    public Cliente findById(Long id) {
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

    public Dependente findDependenteById(Long id) {
        return dr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependente não encontrado com o id:" + id));
    }

    public boolean dependenteExistsById(Long id) {
        return dr.existsById(id);
    }

    public boolean existsById(Long id) {
        return cr.existsById(id);
    }

    public void salvarDependente(Dependente dp, Long id) {
        Cliente cliente = findById(id);
        dp.setCliente(cliente);
        cliente.addDependente(dp);
        save(cliente);
    }

    public void deletarDependente(Long id) {
        Dependente dp = dr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dependente não encontrado com o id:" + id));
        ;
        Cliente cliente = dp.getCliente();
        cliente.removeDependente(dp);
        save(cliente);
        ;
    }

    public Long atualizarDependente(Long id, Dependente dp) {
        if(dependenteExistsById(id)){
            System.out.println(findDependenteById(id).getId());
            Cliente cliente = findById(findDependenteById(id).getId());
            cliente.removeDependenteById(id);
            cliente.addDependente(dp);
            cr.save(cliente);
            return cliente.getId();
        }else{
            throw new ResourceNotFoundException("Dependente não encontrado com o id:" + id);
        }
    }

}
