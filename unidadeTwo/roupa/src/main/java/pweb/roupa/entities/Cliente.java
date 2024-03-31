package pweb.roupa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pweb.roupa.entities.composicao.Endereco;
import pweb.roupa.entities.enums.Genero;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Genero genero;

    @Embedded
    private Endereco endereco;

    private String email;

    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(value = AccessLevel.NONE)
    private List<Dependente> dependentes = new ArrayList<Dependente>();

    public void addDependente(Dependente dependente) {
        dependentes.add(dependente);
        dependente.setCliente(this);
    }
    public void removeDependente(Dependente dependente) {
        dependentes.remove(dependente);
    }

    public void removeDependenteById(Long id) {
        System.out.println(id);
        boolean found = false;
        //dependentes.removeIf(d -> d.getId() == id);
        Dependente dep = new Dependente();
        for (Dependente dependente : dependentes) {
            if (dependente.getId()== id) {
                System.out.println(dependente.getId());
                found = true;
                dep = dependente;
            }
        }
        if (found) {
            removeDependente(dep);
        }

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    
}
