package edu.ufersa.pweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ufersa.pweb.models.Pessoa;

public interface PessoaRepository extends JpaRepository <Pessoa, Long> {
    
}
