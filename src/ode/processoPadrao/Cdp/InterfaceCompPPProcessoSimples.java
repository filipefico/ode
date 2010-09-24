package ode.processoPadrao.Cdp;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class InterfaceCompPPProcessoSimples extends InterfaceCompPP{

		//private Set<ObrigatoriedadeMacroatividade> obrigatoriedadeMacroatividade;

	    public InterfaceCompPPProcessoSimples(String nome, String objetivo, String descricao, CompPP compPP/*, Caracterizacao caracterizacao, Set<ObrigatoriedadeMacroatividade> obrigatoriedadeMacroatividade*/) {
	        //super(nome, objetivo, descricao, compPP/*, caracterizacao*/);
	        //this.obrigatoriedadeMacroatividade = obrigatoriedadeMacroatividade;
	    }

	    public InterfaceCompPPProcessoSimples() {
	    }

	    /** Obtém as SubAtividades.
	     *
	     *@hibernate.set
	     *    inverse = "true"
	     *    cascade = "all"
	     *    lazy = "true"
	     *@hibernate.collection-key
	     *   column = "idointerface"
	     *@hibernate.collection-one-to-many
	     *   class = "Ode.processoPadrao.cdp.ObrigatoriedadeMacroatividade"
	     *
	     */
/*	    @ManyToOne (cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPP.class)
	    public Set<ObrigatoriedadeMacroatividade> getObrigatoriedadeMacroatividade() {
	        return obrigatoriedadeMacroatividade;
	    }

	    public void setObrigatoriedadeMacroatividade(Set<ObrigatoriedadeMacroatividade> obrigatoriedadeMacroatividade) {
	        this.obrigatoriedadeMacroatividade = obrigatoriedadeMacroatividade;
	    }
*/



}
