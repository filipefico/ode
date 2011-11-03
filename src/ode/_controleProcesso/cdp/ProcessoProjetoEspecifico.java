package ode._controleProcesso.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class ProcessoProjetoEspecifico extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;

	private ProcessoProjetoGeral processoProjetoGeral;
	
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	public ProcessoProjetoGeral getProcessoProjetoGeral() {
		return processoProjetoGeral;
	}
	public void setProcessoProjetoGeral(ProcessoProjetoGeral processoProjetoGeral) {
		this.processoProjetoGeral = processoProjetoGeral;
	}
		
}
