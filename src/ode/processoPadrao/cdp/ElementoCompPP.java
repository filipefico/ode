package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class ElementoCompPP extends ObjetoPersistente {
	private static final long serialVersionUID = -8768539818906938847L;

	boolean obrigatorio;
	private Conhecimento elementoConhecimento; // pode ser um KArtefato ou um Kprocesso

	public ElementoCompPP() {
		obrigatorio = false;
	}

	public boolean isObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Conhecimento getElementoConhecimento() {
		return elementoConhecimento;
	}

	public void setElementoConhecimento(Conhecimento elemento) {
		this.elementoConhecimento = elemento;
	}
	
	@Override
	public ElementoCompPP clone() throws CloneNotSupportedException {
		ElementoCompPP copia = new ElementoCompPP();

		copia.setObrigatorio(this.isObrigatorio());
		//copia.setElementoConhecimento(this.getElementoConhecimento().clone());
		
		return copia;
	}
	
}
