package ode.conhecimento.processo.Cdp;


import javax.persistence.Column;
import javax.persistence.Entity;

import nucleo.comuns.persistencia.ObjetoPersistente;

@Entity
public class KDominioAplicacao extends ObjetoPersistente {

	private static final long serialVersionUID = 7215443991537131684L;

	private String nome;

	private String descricao;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome +" : " +descricao;
	}

	
	@Column(nullable = false, length = 60)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(nullable = false, length = 300)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	}
