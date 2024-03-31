package pweb.roupa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pweb.roupa.entities.Pedido;
import pweb.roupa.entities.Produto;
import pweb.roupa.entities.PK.PedidoProdutoPK;
import pweb.roupa.services.PedidoService;
import pweb.roupa.services.ProdutoService;

@Component
public class StringToPedidoProdutoPKConverter implements Converter<String, PedidoProdutoPK> {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public PedidoProdutoPK convert(String source) {
        // Obtém os IDs do pedido e do produto a partir da string source
        String[] ids = source.split(", ");
        Long pedidoId = Long.parseLong(ids[0].substring(ids[0].indexOf("=") + 1));
        Long produtoId = Long.parseLong(ids[1].substring(ids[1].indexOf("=") + 1));

        // Obtém os objetos Pedido e Produto com base nos IDs
        Pedido pedido = pedidoService.findById(pedidoId);
        Produto produto = produtoService.findById(produtoId);

        // Verifica se os objetos Pedido e Produto foram encontrados
        if (pedido != null && produto != null) {
            // Cria e retorna uma nova instância de PedidoProdutoPK
            return new PedidoProdutoPK(pedido, produto);
        } else {
            // Se não conseguir encontrar o Pedido ou o Produto, lança uma exceção ou
            // retorna null, conforme necessário
            throw new IllegalArgumentException("Pedido ou Produto não encontrado");
        }
    }
}
