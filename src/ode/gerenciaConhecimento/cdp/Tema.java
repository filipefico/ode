package ode.gerenciaConhecimento.cdp;

import javax.persistence.Entity;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class Tema extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String descricao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
