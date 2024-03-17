package pweb.roupa.dtos;

import java.time.Instant;
import pweb.roupa.entities.enums.Tecido;

public record ProdutoDTO(String nome, String marca, Tecido tipoTecido, Double precoCompra, Double precoVenda,
        Instant dataCadastro) {

}
