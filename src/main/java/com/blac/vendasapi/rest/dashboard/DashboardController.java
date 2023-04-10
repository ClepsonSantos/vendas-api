package com.blac.vendasapi.rest.dashboard;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blac.vendasapi.model.repository.ClienteRepository;
import com.blac.vendasapi.model.repository.ProdutoRepository;
import com.blac.vendasapi.model.repository.VendaRepository;
import com.blac.vendasapi.model.repository.projections.VendaPorMes;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	@Autowired
	private VendaRepository vendas;
	
	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private ProdutoRepository produtos;

	@GetMapping
	public DashboardData getDashboard() {
		long vendasCount = vendas.count();
		long clientesCount = clientes.count();
		long produtosCount = produtos.count();
		
		int anoCorrente = LocalDate.now().getYear();
		List<VendaPorMes> vendasPorMes = vendas.obterSomatoriaVendasPorMes(anoCorrente);
		
		return new DashboardData(produtosCount, clientesCount, vendasCount, vendasPorMes);
	}
}
