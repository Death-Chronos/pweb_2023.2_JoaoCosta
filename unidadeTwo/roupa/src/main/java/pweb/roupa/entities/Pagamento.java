package pweb.roupa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import pweb.roupa.entities.enums.Metodo;

@Entity
@Data
@NoArgsConstructor
public class Pagamento {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Metodo metodoPagamento;

	@OneToOne
	@MapsId
	private Pedido pedido;

	public Pagamento(Long id, Metodo metodoPagamento, Pedido pedido) {
		this.id = id;
		this.metodoPagamento = metodoPagamento;
		this.pedido = pedido;
	}

	
}
