package ode.entidadeProblema.cdp;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.problema.cdp.TipoEntidadeProblema;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntidadeProblema<K extends ObjetoPersistente> extends
		ObjetoPersistente {
	private static final long serialVersionUID = -2030143808645021029L;

	public abstract TipoEntidadeProblema recuperaTipo();

}