package ode.medicao.analiseMedicao.cdp;

import javax.persistence.Entity;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class AcaoCorretiva extends ObjetoPersistente{

	private String nome;

	public AcaoCorretiva(String nome){
		this.setNome(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){
		return this.nome;
	}
	
	
}
