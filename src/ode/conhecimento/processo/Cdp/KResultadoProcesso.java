package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import ode.conhecimento.principal.Cdp.Conhecimento;


/**
 * Representa os conhecimentos sobre Processos do ambiente ODE.
  */
@Entity
public class KResultadoProcesso extends Conhecimento {
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 7674369881997978029L;
		private KProcesso kProcesso;
	    
	    public KResultadoProcesso() {
	    }
	    
	    
	    /**
	     * Obtém a KProcesso.
	     */
	    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KProcesso.class)
	    @JoinColumn(nullable=true)
	    public KProcesso getKProcesso() {
	        return kProcesso;
	    }
	    
	    public void setKProcesso(KProcesso parKProcesso) {
	        this.kProcesso = parKProcesso;
	    }
	    
	    public String toString() {
	        if (kProcesso!=null) {
	            String sigla = kProcesso.getSigla();
	            if (sigla != null && sigla.length()>0)
	                return kProcesso.getSigla() + "." + this.getNome();
	        }
	        return this.getNome();
	    }
}
