package ode.uml.cdp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;
import ode.controleProjeto.cdp.Projeto;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pacote extends Conhecimento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Projeto projeto;

	@ManyToOne (fetch = FetchType.LAZY)
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}
