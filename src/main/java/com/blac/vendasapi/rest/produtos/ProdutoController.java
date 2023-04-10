package com.blac.vendasapi.rest.produtos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blac.vendasapi.model.Produto;
import com.blac.vendasapi.model.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	private List<ProdutoFormRequest> getLista() {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return produtoRepository.findAll().stream().map(ProdutoFormRequest::fromProduto).collect(Collectors.toList());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoFormRequest> getById(@PathVariable("id") Long id){
		Optional<Produto> produtoExistente = produtoRepository.findById(id);
		
		if(!produtoExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		ProdutoFormRequest produto = produtoExistente.map(ProdutoFormRequest::fromProduto).get();
		
		return ResponseEntity.ok(produto);
	}
	
	@SuppressWarnings("static-access")
	@PostMapping
	public ProdutoFormRequest salvar(@RequestBody ProdutoFormRequest produto) {
		
		Produto entidadeProduto = produto.toModel();
		
		produtoRepository.save(entidadeProduto);
		
		System.out.println("produto saved: "+entidadeProduto);
		
		return produto.fromProduto(entidadeProduto);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produto) {
		Optional<Produto> produtoExistente = produtoRepository.findById(id); // alt+shift+l = criar variavel
		
		if(!produtoExistente.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		Produto entidade = produto.toModel();
		entidade.setId(id);
		produtoRepository.save(entidade);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		Optional<Produto> produtoExistente = produtoRepository.findById(id);
		
		if(!produtoExistente.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		
		produtoRepository.delete(produtoExistente.get());
		
		return ResponseEntity.noContent().build();
	}
	
}
