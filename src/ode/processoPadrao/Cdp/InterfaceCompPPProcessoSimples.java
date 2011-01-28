package ode.processoPadrao.Cdp;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class InterfaceCompPPProcessoSimples extends InterfaceCompPP{

		private static final long serialVersionUID = -1611617498224080342L;
		private Set<ObrigatoriedadeMacroatividade> obrigatoriedadeMacroatividade;

	    public InterfaceCompPPProcessoSimples(String nome, String objetivo, String descricao, CompPP compPP, Set<ObrigatoriedadeMacroatividade> obrigatoriedadeMacroatividade) {
	        super(nome, objetivo, descricao, compPP);
	        this.obrigatoriedadeMacroatividade = obrigatoriedadeMacroatividade;
	    }

	    public InterfaceCompPPProcessoSimples() {
	    }

	    /** Obtém as SubAtividades.
	     *
	     *@hibernate.set
	     *    inverse = "true"
	     */
	    @ManyToOne (cascade = javax.persistence.CascadeType.ALL, targetEntity = ObrigatoriedadeMacroatividade.class,fetch = FetchType.LAZY)
	    public Set<ObrigatoriedadeMacroatividade> getObrigatoriedadeMacroatividade() {
	        return obrigatoriedadeMacroatividade;
	    }

	    public void setObrigatoriedadeMacroatividade(Set<ObrigatoriedadeMacroatividade> obrigatoriedadeMacroatividade) {
	        this.obrigatoriedadeMacroatividade = obrigatoriedadeMacroatividade;
	    }




}
