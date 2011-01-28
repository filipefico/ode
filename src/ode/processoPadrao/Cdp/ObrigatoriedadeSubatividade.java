package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.Cdp.KAtividade;
import nucleo.comuns.persistencia.ObjetoPersistente;

@Entity
public class ObrigatoriedadeSubatividade extends ObjetoPersistente {

	private static final long serialVersionUID = -3095395456994650852L;

	private boolean ehObrigatorio;

	    private InterfaceCompPPMacroatividade interfaceMacroatividade;

	    private KAtividade kAtividade;
	    
	    public ObrigatoriedadeSubatividade(boolean ehObrigatorio) {
	        this.ehObrigatorio = ehObrigatorio;
	    }

	    public ObrigatoriedadeSubatividade() {
	    }

	    public ObrigatoriedadeSubatividade(boolean ehObrigatorio, InterfaceCompPPMacroatividade interfaceMacroatividade, KAtividade kAtividade) {
	        this.ehObrigatorio = ehObrigatorio;
	        this.interfaceMacroatividade = interfaceMacroatividade;
	        this.kAtividade = kAtividade;
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
	    @ManyToOne( targetEntity = InterfaceCompPPMacroatividade.class)
	    public InterfaceCompPPMacroatividade getInterfaceMacroatividade() {
	        return interfaceMacroatividade;
	    }

	    public void setInterfaceMacroatividade(InterfaceCompPPMacroatividade interfaceMacroatividade) {
	        this.interfaceMacroatividade = interfaceMacroatividade;
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
		

}
