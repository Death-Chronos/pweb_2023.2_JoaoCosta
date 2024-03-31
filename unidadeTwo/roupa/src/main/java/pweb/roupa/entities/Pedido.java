package pweb.roupa.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Instant data_criacao;

	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "id.pedido")
	private List<PedidoProduto> itens = new ArrayList<>();

	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL) // Necessário para OneToOne(Order de id 1 estar
																// relacionado com o Payment de id1, e por ai vai)
	private Pagamento pagamento;

	public void adicionarItem(PedidoProduto item) {
		itens.add(item);
	}

	public void removerItem(PedidoProduto item) {
		itens.remove(item);
	}

	public Double getTotal() {
		Double total = 0.0;
		for (PedidoProduto item : itens) {
			total += item.getSubtotal();
		}
		return total;
	}

	@Override
	public String toString() {
		return "Pedido{" +
				"id=" + id +
				", data_criacao=" + data_criacao +
				", cliente=" + (cliente != null ? cliente.getId() : null) +
				'}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
