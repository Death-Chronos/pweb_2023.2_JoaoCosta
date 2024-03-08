package pweb.roupa.dtos;

import pweb.roupa.entities.composicao.Endereco;
import pweb.roupa.entities.enums.Genero;

public record ClienteDTO(String nome, Genero genero, Endereco endereco, String email, String telefone) {

}
