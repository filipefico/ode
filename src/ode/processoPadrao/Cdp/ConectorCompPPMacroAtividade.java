package ode.processoPadrao.Cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode.nucleo.cdp.ObjetoPersistente;


@Entity
public class ConectorCompPPMacroAtividade extends ObjetoPersistente{

		private static final long serialVersionUID = 9083416892812348199L;
		private Set<CompPPMacroatividade> preAtividade;
	    private CompPPMacroatividade atividadeBase;
	    private CompPPProcessoSimples compPPProcessoSimples;

	    public ConectorCompPPMacroAtividade() {
	    }

	    public ConectorCompPPMacroAtividade(Set<CompPPMacroatividade> preAtividade, CompPPMacroatividade atividadeBase, CompPPProcessoSimples compPPProcessoSimples) {
	        this.preAtividade = preAtividade;
	        this.atividadeBase = atividadeBase;
	        this.compPPProcessoSimples = compPPProcessoSimples;
	    }

	    /** Obtém a atividade base.
	     *   not-null = "false"
	     */
	    @ManyToOne(targetEntity = CompPPMacroatividade.class)
	    public CompPPMacroatividade getAtividadeBase() {
	        return atividadeBase;
	    }

	    public void setAtividadeBase(CompPPMacroatividade atividadeBase) {
	        this.atividadeBase = atividadeBase;
	    }

	    /** Obtém a atividade base.
	     *   not-null = "false"
	     */
	    @ManyToOne(targetEntity = CompPPProcessoSimples.class)
	    public CompPPProcessoSimples getCompPPProcessoSimples() {
	        return compPPProcessoSimples;
	    }

	    public void setCompPPProcessoSimples(CompPPProcessoSimples compPPProcessoSimples) {
	        this.compPPProcessoSimples = compPPProcessoSimples;
	    }

	    /** Obtém as pre-atividades.
	     *
	     *@hibernate.set
	     *    cascade = "none"
	     */
	    @ManyToMany( targetEntity = CompPPMacroatividade.class,fetch=FetchType.EAGER)
	    public Set<CompPPMacroatividade> getPreAtividade() {
	        return preAtividade;
	    }

	    public void setPreAtividade(Set<CompPPMacroatividade> preAtividade) {
	        this.preAtividade = preAtividade;
	    }

	    
}
