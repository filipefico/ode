package ode.conhecimento.processo.Cdp;

import ode.conhecimento.principal.Cdp.Conhecimento;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;



/**
 * Representa os conhecimentos sobre Procedimentos do ambiente ODE.
 */

@Entity
public class KProcedimento extends Conhecimento {
		/**
	 * 
	 */
	private static final long serialVersionUID = -5040042704401696704L;
		private Set<KParadigma> kParadigmas;
	    private Set<KTipoSoftware> kTiposSoftware;
	    
	    /**Construtor. */
	    public KProcedimento() {
	    }
	    
	    
	    /**
	     * Obtém os paradigmas do Conhecimento Procedimento.
	     */
	    @ManyToMany(cascade = javax.persistence.CascadeType.ALL,targetEntity = KTipoSoftware.class, fetch = FetchType.LAZY)
	    public Set<KTipoSoftware> getKTiposSoftware() {
	        return kTiposSoftware;
	    }
	    
	    /** Atribui as tecnologias do Conhecimento Procedimento.*/
	    public void setKTiposSoftware(Set<KTipoSoftware> parTiposSW) {
	        kTiposSoftware = parTiposSW;
	    }
	    
	    
	    /** Obtém as tecnologias do Conhecimento Procedimento.
	     */
	    @ManyToMany(cascade = javax.persistence.CascadeType.ALL,targetEntity = KParadigma.class, fetch = FetchType.LAZY)
	    public Set<KParadigma> getKParadigmas() {
	        return kParadigmas;
	    }
	    
	    /** Atribui os paradigmas do Conhecimento Procedimemto.*/
	    public void setKParadigmas(Set<KParadigma> parParadigmas) {
	        kParadigmas = parParadigmas;
	    }
	    

	    public String obterNomeCanonico() {
	        return "Procedimento";
	    }
}
