package pweb.roupa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pweb.roupa.entities.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Long> {
    
}
