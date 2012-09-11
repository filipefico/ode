package ode.uml.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CasoUso extends Conhecimento{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Pacote pacote;
	
	private Set<Classe> classes;

	@ManyToOne (fetch = FetchType.LAZY)
	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	@ManyToMany (fetch = FetchType.LAZY)
	public Set<Classe> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classe> classes) {
		this.classes = classes;
	}
	
	
}
