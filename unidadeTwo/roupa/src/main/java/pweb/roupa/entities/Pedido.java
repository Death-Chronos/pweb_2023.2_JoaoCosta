package pweb.roupa.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Pedido {

    private Instant data_criacao;

	

    @ManyToOne
	@JoinColumn(name = "cliente") 
	private Cliente cliente;

	@OneToMany(mappedBy = "id.pedido") //Mapeia os OrderItems(com a entidade OrderItemPK, pois ela sim possui relacionamento com Order, por isso o id.order, pois é o id que relaciona OrderItemPK com OrderItem)
	@Setter(value= AccessLevel.NONE)
	private Set<PedidoProduto> items = new HashSet<>(); //Ou seja, mostra todos os OrderItem que essa Order possui
	
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL) //Necessário para OneToOne(Order de id 1 estar relacionado com o Payment de id1, e por ai vai)
	private Pagamento pagamento;
}
