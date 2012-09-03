package ode.controleCaracteristica.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class PossivelValor extends ObjetoPersistente {
	
    private static final long serialVersionUID = 1L;
	
	protected String nome;
	private String descricao;

	@Column(length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String parNome) {
		nome = parNome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}