package com.blac.vendasapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blac.vendasapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
