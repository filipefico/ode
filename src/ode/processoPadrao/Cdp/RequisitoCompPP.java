package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import ode.nucleo.cdp.ObjetoPersistente;


@Entity
public class RequisitoCompPP extends ObjetoPersistente {

	private static final long serialVersionUID = 988027842699157248L;
		/** Descricao */
	    private String descricao;

	    @Column(length=500)
	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }
}
