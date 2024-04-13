package pweb.roupa.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pweb.roupa.entities.Pedido;
import pweb.roupa.services.PedidoService;


@Controller
@RequestMapping("/faturamento")
public class FaturamentoController {

    @Autowired
    PedidoService pedidoService;


// Exiba, em uma tabela com 12 linhas, o faturamento de cada mês nos últimos 12 meses, a contar de uma data informada pelo usuário. Ao final do relatório deve ser exibido, em outra tabela, o valor do faturamento total da loja, o valor total do imposto pago e a soma dos valores (faturamento + imposto fixo em 13,75% sobre o faturamento).

    @GetMapping("")
    public String pegarData() {
        return "dataFaturamento";
    }

    @GetMapping("/relatorio")
    public ModelAndView faturamento(@RequestParam LocalDate data) {
        List<Pedido> pedidos = pedidoService.findAll();

        Instant dataPedida = data.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant dataPlusAno = data.atStartOfDay(ZoneId.systemDefault()).plusMonths(12).toInstant();

        pedidos = pedidos.stream().
        filter(x -> x.getData_criacao().isAfter(dataPedida)).
        filter(x -> x.getData_criacao().isBefore(dataPlusAno)).
        collect(Collectors.toList());
        //Pegando apenas os pedidos que estão até 12 meses a frente da data pedida

        Map<Integer,Double> faturamentoPorMes = new LinkedHashMap<>();

        for(int i = 0; i < 12; i++){
            faturamentoPorMes.put(i+1, 0.0);
        }
        //Criando o map de faturamento e atribuindo os valores

        double impostoTotal = 0.0;
        double faturamentoTotal = 0.0;
        double impostoPorMes = 13.75/100;
        //Setando valores
        for(int i = 0; i < pedidos.size(); i++){

            int valorMes = LocalDate.ofInstant(pedidos.get(i).getData_criacao(), ZoneId.systemDefault()).
            getMonthValue();
            //definindo o valor do mês
            double valorPedido = pedidos.get(i).getTotal() - pedidos.get(i).getTotal() * impostoPorMes;
            //definindo valor do pedido

            impostoTotal += valorPedido * impostoPorMes;
            //definindo valor dos impostos
            faturamentoTotal += pedidos.get(i).getTotal();
            //definindo valor do faturamento
            

            faturamentoPorMes.put(valorMes,faturamentoPorMes.get(valorMes) + valorPedido);
            //mudando o valor do map
 
        }
        double somaTotal = faturamentoTotal + impostoTotal;
        //definindo a soma total
        
        ModelAndView mv = new ModelAndView("faturamento");

        mv.addObject("faturamentoMensal", faturamentoPorMes);
        mv.addObject("impostoTotal", impostoTotal);
        mv.addObject("faturamentoTotal", faturamentoTotal);
        mv.addObject("total", somaTotal);

        return mv;
    }
    
    
    
}
