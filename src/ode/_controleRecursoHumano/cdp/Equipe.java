package ode._controleRecursoHumano.cdp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.controleProjeto.cdp.Projeto;

@Entity
public class Equipe extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private Projeto projeto;

	@OneToOne (cascade = {CascadeType.REMOVE})
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}
