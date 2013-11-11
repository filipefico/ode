package ode.processoProjeto.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.InterfaceCompPP;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CompPPr extends ObjetoPersistente implements Cloneable{

	private static final long serialVersionUID = -2714001448285931087L;
	
	private CompPPProcessoComplexo padraoBase;
	private String MCV = "Cascata";
	
	// Métodos
	
	public CompPPr() {
		
	}
	
	public void setPadraoBase(CompPPProcessoComplexo padraoBase) {
		this.padraoBase = padraoBase;
	}

	public CompPPProcessoComplexo getPadraoBase() {
		return padraoBase;
	}
	
}
