package ode._controleProcesso.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import ode._controleProcesso.cdp.Atividade;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecurso;

@Entity
public class DefinicaoAtividade extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;

	private Atividade atividade;
	private Set<KRecurso> kRecursos;

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
		
}
