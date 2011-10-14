package ode.processoPadrao.cdp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class ElementoCompPP extends ObjetoPersistente {
	private static final long serialVersionUID = -8768539818906938847L;

	EstruturaCompPP estruturaCompPP;
	boolean obrigatorio;
	Conhecimento elemento; // pode ser um KArtefato ou um KProcesso

	public ElementoCompPP() {
		obrigatorio = false;
	}

	@OneToOne
	public EstruturaCompPP getEstruturaCompPP() {
		return estruturaCompPP;
	}

	public void setEstruturaCompPP(EstruturaCompPP estruturaCompPP) {
		this.estruturaCompPP = estruturaCompPP;
	}

	public boolean isObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	@OneToOne
	public Conhecimento getElemento() {
		return elemento;
	}

	public void setElemento(Conhecimento elemento) {
		this.elemento = elemento;
	}
}
