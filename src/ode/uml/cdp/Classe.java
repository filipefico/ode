package ode.uml.cdp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Classe extends Conhecimento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pacote pacote;

	@ManyToOne (fetch = FetchType.LAZY)
	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}
}
