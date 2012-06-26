package ode.entidadeProblema.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode.controleProjeto.cdp.Projeto;
import ode.problema.cdp.TipoEntidadeProblema;
@Entity
public class ProjetoProblema extends EntidadeProblema<Projeto>{

	


	private static final long serialVersionUID = -6945273807604246375L;
	private final TipoEntidadeProblema tipo = TipoEntidadeProblema.PROJETO;
	private Projeto projeto;
	
	@Override
	public TipoEntidadeProblema recuperaTipo() {
		return tipo;
	}

	@ManyToOne
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public String toString() {
		return this.projeto.getNome();
	}
}