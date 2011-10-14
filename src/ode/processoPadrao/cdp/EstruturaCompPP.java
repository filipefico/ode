package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class EstruturaCompPP extends ObjetoPersistente {
	private static final long serialVersionUID = -168687019713089202L;
	
	private Set<ElementoCompPP> elementosCompPP;
	private InterfaceCompPP interafeCompPP;

	@OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
	public Set<ElementoCompPP> getElementosCompPP() {
		return elementosCompPP;
	}


	public void setElementosCompPP(Set<ElementoCompPP> elementosCompPP) {
		this.elementosCompPP = elementosCompPP;
	}
	
	public EstruturaCompPP() {
		elementosCompPP = new HashSet<ElementoCompPP>();
	}

	@OneToOne
	public InterfaceCompPP getInterafeCompPP() {
		return interafeCompPP;
	}

	public void setInterafeCompPP(InterfaceCompPP interafeCompPP) {
		this.interafeCompPP = interafeCompPP;
	}

}