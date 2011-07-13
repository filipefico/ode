package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.nucleo.cgd.ObjetoPersistente;

@Entity
public class ObrigatoriedadeMacroatividade extends ObjetoPersistente{


		private static final long serialVersionUID = -310305063954240289L;

		private boolean ehObrigatorio;

	    private KAtividade kAtividade;

	    private InterfaceCompPPProcessoSimples interfaceSimples;

	    public ObrigatoriedadeMacroatividade(boolean ehObrigatorio) {
	        this.ehObrigatorio = ehObrigatorio;
	    }

	    public ObrigatoriedadeMacroatividade() {
	    }

	    public ObrigatoriedadeMacroatividade(boolean ehObrigatorio, KAtividade kAtividade, InterfaceCompPPProcessoSimples interfaceSimples) {
	        this.ehObrigatorio = ehObrigatorio;
	        this.kAtividade = kAtividade;
	        this.interfaceSimples = interfaceSimples;
	    }



	    /**
	     *  @hibernate.property
	     */
	    @Column
	    public boolean isEhObrigatorio() {
	        return ehObrigatorio;
	    }

	    public void setEhObrigatorio(boolean ehObrigatorio) {
	        this.ehObrigatorio = ehObrigatorio;
	    }

	    /**
	     *   not-null = "false"
	    */
	    @ManyToOne(targetEntity = KAtividade.class)
	    @JoinColumn(nullable=false)
	    public KAtividade getkAtividade() {
	        return kAtividade;
	    }

	    public void setkAtividade(KAtividade kAtividade) {
	        this.kAtividade = kAtividade;
	    }

	    /**
	     *   not-null = "false"
	     */
	    @ManyToOne(targetEntity = InterfaceCompPPProcessoSimples.class)
	    public InterfaceCompPPProcessoSimples getInterfaceSimples() {
	        return interfaceSimples;
	    }

	    public void setInterfaceSimples(InterfaceCompPPProcessoSimples interfaceSimples) {
	        this.interfaceSimples = interfaceSimples;
	    }
}
