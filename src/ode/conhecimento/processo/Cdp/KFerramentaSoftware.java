package ode.conhecimento.processo.Cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;


@Entity
public class KFerramentaSoftware  extends KRecurso{	
	
	private static final long serialVersionUID = 1L;
	
	/** Tipo do Conhecimento Ferramenta Software. */
    //private String tipo;
    
    /** lista(set) de procedimentos de ferramenta de software */
    private Set<KProcedimento> procedimentos;
    
    /** Construtor. */
    public KFerramentaSoftware() {
    }
    

    public String obterNomeCanonico() {
        return "Ferramenta de Software";
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getNome();
	}
	
    
    /*obter lista de procedimentos
    */
	@ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KProcedimento.class,fetch=FetchType.EAGER)
    public Set<KProcedimento> getProcedimentos() {
        return this.procedimentos;
        
    }
    
    /** atribuir lista de procedimentos */
    public void setProcedimentos(Set<KProcedimento> parProcedimentos) {
        this.procedimentos = parProcedimentos;
        
    }
    
}
