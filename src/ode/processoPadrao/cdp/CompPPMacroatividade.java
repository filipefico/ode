package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;

@Entity
public class CompPPMacroatividade extends CompPP{

		private static final long serialVersionUID = 1258234501707911433L;
		private AtividadeProcessoPadrao atividade;
	    private Set<DependenciaMacroAtividades> preDependenciaMacroAtividade;
	    private DependenciaMacroAtividades baseDependenciaMacroAtividade;
	    private KAtividade tipo;

	    @ManyToOne(cascade = {javax.persistence.CascadeType.PERSIST,javax.persistence.CascadeType.MERGE},fetch=FetchType.EAGER)
	    public KAtividade getTipo() {
			return tipo;
		}



		public void setTipo(KAtividade tipo) {
			this.tipo = tipo;
		}



		public CompPPMacroatividade() {
	    	this.preDependenciaMacroAtividade = new HashSet<DependenciaMacroAtividades>();
	    }
	   

	   
	    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = AtividadeProcessoPadrao.class,fetch=FetchType.LAZY)
	    public AtividadeProcessoPadrao getAtividade() {
	        return atividade;
	    }

	    public void setAtividade(AtividadeProcessoPadrao atividade) {
	        this.atividade = atividade;
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
