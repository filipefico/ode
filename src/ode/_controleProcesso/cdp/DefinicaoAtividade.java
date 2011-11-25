package ode._controleProcesso.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecurso;

@Entity
public class DefinicaoAtividade extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;

	private Atividade atividade;
	private Set<KRecurso> kRecursos;
	private Set<Atividade> subAtividades;

	@OneToOne
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KRecurso> getkRecursos() {
		return kRecursos;
	}
	public void setkRecursos(Set<KRecurso> kRecursos) {
		this.kRecursos = kRecursos;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	public Set<Atividade> getSubAtividades(){
		return this.subAtividades;
	}
	public void setSubAtividades(Set<Atividade> atividades) {
		this.subAtividades = atividades;
	}
		
}
