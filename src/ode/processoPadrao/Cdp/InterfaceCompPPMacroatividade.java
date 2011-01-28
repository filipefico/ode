package ode.processoPadrao.Cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class InterfaceCompPPMacroatividade extends InterfaceCompPP {

	private static final long serialVersionUID = -6998236867814593682L;
	private Set<ObrigatoriedadeSubatividade> obrigatoriedadeSubatividade;

	    

	    public InterfaceCompPPMacroatividade() {
	    }

	    public InterfaceCompPPMacroatividade(String nome, String objetivo, String descricao, CompPP compPP, 
	            Set<ObrigatoriedadeSubatividade> obrigatoriedadeSubatividade) {
	        super(nome, objetivo, descricao, compPP);
	        this.obrigatoriedadeSubatividade = obrigatoriedadeSubatividade;
	    }

	    /** Obtém as SubAtividades.
	     *
	     *@hibernate.set
	     *    inverse = "true"
	     */
	    @OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = ObrigatoriedadeSubatividade.class,fetch=FetchType.LAZY)
	    public Set<ObrigatoriedadeSubatividade> getObrigatoriedadeSubatividade() {
	        return obrigatoriedadeSubatividade;
	    }

	    public void setObrigatoriedadeSubatividade(Set<ObrigatoriedadeSubatividade> obrigatoriedadeSubatividade) {
	        this.obrigatoriedadeSubatividade = obrigatoriedadeSubatividade;
	    }
	
	
}
