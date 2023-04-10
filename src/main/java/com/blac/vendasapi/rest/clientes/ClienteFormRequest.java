package com.blac.vendasapi.rest.clientes;

import java.time.LocalDate;

import com.blac.vendasapi.model.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteFormRequest {

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String cpf;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cadastro;

	public ClienteFormRequest() {
	}
	
	public ClienteFormRequest(Long id, LocalDate nascimento, String cpf, String nome, String endereco, String telefone,
			String email, LocalDate cadastro) {
		super();
		this.id = id;
		this.dataNascimento = nascimento;
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.cadastro = cadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDateNascimento(LocalDate nascimento) {
		this.dataNascimento = nascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getCadastro() {
		return cadastro;
	}

	public void setCadastro(LocalDate cadastro) {
		this.cadastro = cadastro;
	}
	public Cliente toModel() {
		return new Cliente(id, dataNascimento, cpf, nome, endereco, telefone, email, cadastro);
	}
	
	public static ClienteFormRequest fromModel(Cliente cliente) {
		return new ClienteFormRequest(
				cliente.getId(), 
				cliente.getNascimento(),
				cliente.getCpf(),
				cliente.getNome(),
				cliente.getEndereco(),
				cliente.getTelefone(),
				cliente.getEmail(),
				cliente.getDataCadastro()
		);
	}
}
