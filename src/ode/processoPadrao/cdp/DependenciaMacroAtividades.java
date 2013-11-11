package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class DependenciaMacroAtividades extends ObjetoPersistente {

	private static final long serialVersionUID = 9083416892812348199L;
	private Set<CompPPMacroatividade> preAtividade;
	private CompPPMacroatividade atividadeBase;
	private CompPPProcessoSimples compPPProcessoSimples;

	public DependenciaMacroAtividades() {
	}

	public DependenciaMacroAtividades(Set<CompPPMacroatividade> preAtividade, CompPPMacroatividade atividadeBase, CompPPProcessoSimples compPPProcessoSimples) {
		this.preAtividade = preAtividade;
		this.atividadeBase = atividadeBase;
		this.compPPProcessoSimples = compPPProcessoSimples;
	}

	@ManyToOne(targetEntity = CompPPMacroatividade.class)
	public CompPPMacroatividade getAtividadeBase() {
		return atividadeBase;
	}

	public void setAtividadeBase(CompPPMacroatividade atividadeBase) {
		this.atividadeBase = atividadeBase;
	}

	@ManyToOne(targetEntity = CompPPProcessoSimples.class)
	public CompPPProcessoSimples getCompPPProcessoSimples() {
		return compPPProcessoSimples;
	}

	public void setCompPPProcessoSimples(CompPPProcessoSimples compPPProcessoSimples) {
		this.compPPProcessoSimples = compPPProcessoSimples;
	}

	/**
	 * Obtém as pre-atividades.
	 * 
	 * @hibernate.set cascade = "none"
	 */
	@ManyToMany(targetEntity = CompPPMacroatividade.class, fetch = FetchType.EAGER)
	public Set<CompPPMacroatividade> getPreAtividade() {
		return preAtividade;
	}

	public void setPreAtividade(Set<CompPPMacroatividade> preAtividade) {
		this.preAtividade = preAtividade;
	}
	
	@Override
    public DependenciaMacroAtividades clone() throws CloneNotSupportedException{
		DependenciaMacroAtividades copia = new DependenciaMacroAtividades();
		
		Set<CompPPMacroatividade> preAtividadeCopia = new HashSet<CompPPMacroatividade>();
		
		for(CompPPMacroatividade macroatividade : this.getPreAtividade()){
			preAtividadeCopia.add(macroatividade.clone());
		}
		
		copia.setPreAtividade(preAtividadeCopia);
		copia.setAtividadeBase(this.getAtividadeBase());
		copia.setCompPPProcessoSimples(this.getCompPPProcessoSimples());
    	
    	return copia;
    }

	public void salvar(DependenciaMacroAtividades dependencia) {
		// TODO Auto-generated method stub
		
	}

}
