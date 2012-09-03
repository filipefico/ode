package ode.medicao.execucaoMedicao.cdp;

import javax.persistence.Entity;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class ContextMedicao extends ObjetoPersistente{

	private String descricao;

	public ContextMedicao(String value) {
		this.descricao = value;
	}
	
	public ContextMedicao() {}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString(){
		return getDescricao();
	}
	
}
