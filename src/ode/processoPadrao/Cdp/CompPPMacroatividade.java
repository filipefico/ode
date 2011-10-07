package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CompPPMacroatividade extends CompPP{

		private static final long serialVersionUID = 1258234501707911433L;
		private AtividadeProcessoPadrao atividade;
	    private Set<CompPPProcessoSimples> compPPProcessoSimples;
	    private Set<DependenciaMacroAtividades> preDependenciaMacroAtividade;
	    private DependenciaMacroAtividades baseDependenciaMacroAtividade;

	    public CompPPMacroatividade() {
	    	this.compPPProcessoSimples = new HashSet<CompPPProcessoSimples>();
	    	this.preDependenciaMacroAtividade = new HashSet<DependenciaMacroAtividades>();
	    }
	   

	   
	    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = AtividadeProcessoPadrao.class,fetch=FetchType.LAZY)
	    public AtividadeProcessoPadrao getAtividade() {
	        return atividade;
	    }

	    public void setAtividade(AtividadeProcessoPadrao atividade) {
	        this.atividade = atividade;
	    }


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

    
	    
	    /** Obtém as pré-Atividades da atividade
	    */ 
	    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = DependenciaMacroAtividades.class,fetch=FetchType.LAZY)
	    public Set<DependenciaMacroAtividades> getPreDependenciaMacroAtividade() {
	        return preDependenciaMacroAtividade;
	    }

	    public void setPreDependenciaMacroAtividade(Set<DependenciaMacroAtividades> preDependenciaMacroAtividade) {
	        this.preDependenciaMacroAtividade = preDependenciaMacroAtividade;
	    }

	    @Column
		public DependenciaMacroAtividades getBaseDependenciaMacroAtividade() {
			return baseDependenciaMacroAtividade;
		}

		public void setBaseDependenciaMacroAtividade(
				DependenciaMacroAtividades baseDependenciaMacroAtividade) {
			this.baseDependenciaMacroAtividade = baseDependenciaMacroAtividade;
		}
	    	      
	     
}
