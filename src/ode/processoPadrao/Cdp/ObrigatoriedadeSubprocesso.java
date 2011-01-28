package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.Cdp.KProcesso;
import nucleo.comuns.persistencia.ObjetoPersistente;

@Entity
public class ObrigatoriedadeSubprocesso extends ObjetoPersistente{

	private static final long serialVersionUID = 656556237738413390L;

	private boolean ehObrigatorio;

	    private InterfaceCompPPProcessoComplexo interfaceCompPPProcessoComplexo;

	    private KProcesso kProcesso;

	    public ObrigatoriedadeSubprocesso(boolean ehObrigatorio, InterfaceCompPPProcessoComplexo interfaceCompPPProcessoComplexo, KProcesso kProcesso) {
	        this.ehObrigatorio = ehObrigatorio;
	        this.interfaceCompPPProcessoComplexo = interfaceCompPPProcessoComplexo;
	        this.kProcesso = kProcesso;
	    }

	    public ObrigatoriedadeSubprocesso() {
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
	    @ManyToOne(targetEntity = InterfaceCompPPProcessoComplexo.class)
	    public InterfaceCompPPProcessoComplexo getInterfaceCompPPProcessoComplexo() {
	        return interfaceCompPPProcessoComplexo;
	    }

	    public void setInterfaceCompPPProcessoComplexo(InterfaceCompPPProcessoComplexo interfaceCompPPProcessoComplexo) {
	        this.interfaceCompPPProcessoComplexo = interfaceCompPPProcessoComplexo;
	    }

	    /**
	     *   not-null = "false"
	     */
	    @ManyToOne(targetEntity = KProcesso.class)
	    @JoinColumn(nullable=false)
	    public KProcesso getkProcesso() {
	        return kProcesso;
	    }

	    public void setkProcesso(KProcesso kProcesso) {
	        this.kProcesso = kProcesso;
	    }
		

}
