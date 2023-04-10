package com.blac.vendasapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blac.vendasapi.model.Venda;
import com.blac.vendasapi.model.repository.projections.VendaPorMes;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Query(nativeQuery = true,
			value = 
			"SELECT  " + 
			"	EXTRACT(MONTH FROM v.data_cadastro) AS mes, " + 
			"	SUM(v.total) AS valor " + 
			"FROM venda AS v " + 
			"WHERE EXTRACT(YEAR FROM v.data_cadastro) = :ano " + 
			"GROUP BY EXTRACT(MONTH FROM v.data_cadastro) " + 
			"ORDER BY EXTRACT(MONTH FROM v.data_cadastro)"
	)
	public List<VendaPorMes> obterSomatoriaVendasPorMes(@Param("ano") Integer ano);
	
}
