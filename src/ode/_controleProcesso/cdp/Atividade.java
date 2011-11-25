package ode._controleProcesso.cdp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class Atividade extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private EstadoAtividade estado;
	
	private ProcessoProjetoEspecifico processoProjetoEspecifico;

	@ManyToOne
	public ProcessoProjetoEspecifico getProcessoProjetoEspecifico() {
		return processoProjetoEspecifico;
	}
	public void setProcessoProjetoEspecifico(ProcessoProjetoEspecifico processoProjetoEspecifico) {
		this.processoProjetoEspecifico = processoProjetoEspecifico;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Enumerated(EnumType.STRING)
	public EstadoAtividade getEstado() {
		return estado;
	}
	public void setEstado(EstadoAtividade estado) {
		this.estado = estado;
	}
	
	
}
