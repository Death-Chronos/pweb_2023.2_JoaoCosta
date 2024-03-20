package pweb.roupa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pweb.roupa.controllers.exceptions.ResourceNotFoundException;
import pweb.roupa.entities.Dependente;
import pweb.roupa.repositories.DependenteRepository;

@Service
public class DependenteService {
    
    @Autowired
    DependenteRepository dp;

    

    
    
}
