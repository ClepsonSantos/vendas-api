package com.blac.vendasapi.rest.vendas;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blac.vendasapi.model.Venda;
import com.blac.vendasapi.model.repository.ItemVendaRepository;
import com.blac.vendasapi.model.repository.VendaRepository;
import com.blac.vendasapi.service.RelatorioVendaService;
import com.blac.vendasapi.util.DateUtils;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("*")
public class VendasController {
	
	@Autowired
	private VendaRepository vendaResporitory;

	@Autowired
	private ItemVendaRepository itemVendaResporitory;
	
	@Autowired
	private RelatorioVendaService relatorioVendaService;
	
	@PostMapping
	@Transactional
	public void realizarVenda(@RequestBody Venda venda) {
		vendaResporitory.save(venda);
		venda.getItens().forEach(iv -> iv.setVenda(venda));
		itemVendaResporitory.saveAll(venda.getItens());
	}
	
	@GetMapping("/relatorio-vendas")
	public ResponseEntity<byte[]> relatorioVendas(
			@RequestParam(value = "id", required = false, defaultValue = "") Long id,
			@RequestParam(value = "inicio", required = false, defaultValue = "") String inicio,
			@RequestParam(value = "fim", required = false, defaultValue = "") String fim			
	) {
		
		Date dataInicio = DateUtils.fromString(inicio);
		Date dataFim = DateUtils.fromString(fim, true);
		
		if(id != null && id == 0) {
			id = null;
		}
		
		if(dataInicio == null){
			dataInicio = DateUtils.DATA_INICIO_PADRAO;
		}
		
		if(dataFim == null){
			dataFim = DateUtils.hoje(true); 
		}

		byte[] relatorioGerado = relatorioVendaService.gerarRelatorio(id, dataInicio, dataFim);
		
		HttpHeaders headers = new HttpHeaders();
		String fileName = "relatorio-vendas.pdf";
		
		// inline; filename="relatorio-vendas.pdf"
		headers.setContentDispositionFormData("inline; filename=\""+fileName+"\"", fileName);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(relatorioGerado, headers, HttpStatus.OK);
		
		return responseEntity;
	}
	
}
