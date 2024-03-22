package pweb.roupa.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pweb.roupa.entities.enums.Metodo;

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
}
