package com.project.teste.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENDERECO")
public class Endereco extends AbstractEntity<Long> {



	@Column(nullable = false, unique = false)
	private String logradouro;	

	@Column(nullable = false, unique = false)
	private String numero;
	
	@Column(nullable = false, unique = false)
	private String complemento;
	
	@Column(nullable = false, unique = false)
	private String bairro;
	
	@Column(nullable = false, unique = false)
	private String cidade;
	
	@Column(nullable = false, unique = false)
	private String estado;
	
	@Column(nullable = false, unique = false)
	private String cep;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id_fk")
	private Usuario usuario;
	
	
	public Endereco(String logradouro, String numero, String complemento, String bairro, String localidade, String uf,
			        String cep, Usuario usuario) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = localidade;
		this.estado = uf;
		this.cep = cep;
		this.usuario = usuario;
	}
	
	
	
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
		
}