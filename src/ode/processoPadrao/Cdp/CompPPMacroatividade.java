package ode.processoPadrao.Cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CompPPMacroatividade extends CompPP{

		private static final long serialVersionUID = 1258234501707911433L;
		public static final String nomeClass = "CompPPMacroatividade";
	    private AtividadeProcessoPadrao atividade;
	    private Set<CompPPProcessoSimples> compPPProcessoSimples;
	    private Set<ConectorCompPPMacroAtividade> preConectorCompPPMacroAtividade;
	    private Set<ConectorCompPPMacroAtividade> baseConectorCompPPMacroAtividade;

	    public CompPPMacroatividade(AtividadeProcessoPadrao atividade, Set<CompPPProcessoSimples> compPPProcessoSimples, Set<ConectorCompPPMacroAtividade> preConectorCompPPMacroAtividade, Set<ConectorCompPPMacroAtividade> baseConectorCompPPMacroAtividade) {
	        this.atividade = atividade;
	        this.compPPProcessoSimples = compPPProcessoSimples;
	        this.preConectorCompPPMacroAtividade = preConectorCompPPMacroAtividade;
	        this.baseConectorCompPPMacroAtividade = baseConectorCompPPMacroAtividade;
	    }

	    public CompPPMacroatividade() {
	    }

	    /**
	     *   not-null = "false"
	     */
	    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = AtividadeProcessoPadrao.class,fetch=FetchType.LAZY)
	    public AtividadeProcessoPadrao getAtividade() {
	        return atividade;
	    }

	    public void setAtividade(AtividadeProcessoPadrao atividade) {
	        this.atividade = atividade;
	    }

	    /** 
	     *
	     *@hibernate.set
	     *   cascade = "none"
	     *
	     */
	    @ManyToMany( targetEntity = CompPPProcessoSimples.class,fetch=FetchType.EAGER)
	    public Set<CompPPProcessoSimples> getCompPPProcessoSimples() {
	        return compPPProcessoSimples;
	    }

	    public void setCompPPProcessoSimples(Set<CompPPProcessoSimples> compPPProcessoSimples) {
	        this.compPPProcessoSimples = compPPProcessoSimples;
	    }

	    public void addCompPPProcessoSimples(CompPPProcessoSimples compPPProcessoSimples) {
	        if (this.compPPProcessoSimples == null) {
	            this.compPPProcessoSimples = new HashSet<CompPPProcessoSimples>();
	        }
	        this.compPPProcessoSimples.add(compPPProcessoSimples);
	    }

	    /** Obtém as SubAtividades.
	     *
	     *@hibernate.set
	     *    inverse = "true"
	     */
	    @OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = ConectorCompPPMacroAtividade.class,fetch=FetchType.LAZY)
	    public Set<ConectorCompPPMacroAtividade> getBaseConectorCompPPMacroAtividade() {
	        return baseConectorCompPPMacroAtividade;
	    }

	    public void setBaseConectorCompPPMacroAtividade(Set<ConectorCompPPMacroAtividade> baseConectorCompPPMacroAtividade) {
	        this.baseConectorCompPPMacroAtividade = baseConectorCompPPMacroAtividade;
	    }

	    /** Obtém as pré-Atividades da atividade
	    */ 
	    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = ConectorCompPPMacroAtividade.class,fetch=FetchType.LAZY)
	    public Set<ConectorCompPPMacroAtividade> getPreConectorCompPPMacroAtividade() {
	        return preConectorCompPPMacroAtividade;
	    }

	    public void setPreConectorCompPPMacroAtividade(Set<ConectorCompPPMacroAtividade> preConectorCompPPMacroAtividade) {
	        this.preConectorCompPPMacroAtividade = preConectorCompPPMacroAtividade;
	    }
	     public InterfaceCompPPMacroatividade getInterfaceCompPP() {
	       return (InterfaceCompPPMacroatividade)(super.getInterfaceCompPP());
	     }
	      
	     
}
