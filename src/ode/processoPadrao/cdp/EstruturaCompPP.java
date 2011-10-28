package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class EstruturaCompPP extends ObjetoPersistente {
	private static final long serialVersionUID = -168687019713089202L;

	private Set<ElementoCompPP> elementosCompPP;
	
	public EstruturaCompPP() {
		elementosCompPP = new HashSet<ElementoCompPP>();
	}
	
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},fetch=FetchType.EAGER)
	public Set<ElementoCompPP> getElementosCompPP() {
		return elementosCompPP;
	}


	public void setElementosCompPP(Set<ElementoCompPP> elementosCompPP) {
		this.elementosCompPP = elementosCompPP;
	}
	

}