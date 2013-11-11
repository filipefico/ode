package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ode.conhecimento.processo.cdp.KProcesso;

@Entity
public class CompPPProcessoSimples extends CompPP implements Cloneable {

	private static final long serialVersionUID = -1003139827021069271L;

	private Set<CompPPProcessoComplexo> processosComplexos;
	private Set<CompPPMacroatividade> macroAtividades; 
	private Set<DependenciaMacroAtividades> dependenciaMacroAtividades;
	//private AtividadeProcessoPadrao atividadeProcessoPadrao;
	private KProcesso tipo;
	private boolean ehEngenharia;

	public CompPPProcessoSimples() {
		this.processosComplexos = new HashSet<CompPPProcessoComplexo>();
		this.macroAtividades = new HashSet<CompPPMacroatividade>();
		this.dependenciaMacroAtividades = new HashSet<DependenciaMacroAtividades>();
		//this.atividadeProcessoPadrao = new AtividadeProcessoPadrao();
		this.ehEngenharia = false;
	}

	public void setEngenharia(boolean bool){
		this.ehEngenharia = bool;
	}
	
	public boolean getEngenharia(){
		return this.ehEngenharia;
	}
	
	@ManyToMany(targetEntity = CompPPProcessoComplexo.class, fetch = FetchType.EAGER)
	public Set<CompPPProcessoComplexo> getProcessosComplexos() {
		return processosComplexos;
	}

/*	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER)
	public AtividadeProcessoPadrao getAtividadeProcessoPadrao() {
		return this.atividadeProcessoPadrao;
	}
	
	public void setAtividadeProcessoPadrao(AtividadeProcessoPadrao atividadeProcessoPadrao) {
		this.atividadeProcessoPadrao = atividadeProcessoPadrao;
	}*/
	
	public void setProcessosComplexos(Set<CompPPProcessoComplexo> parProcessosComplexos) {
		this.processosComplexos = parProcessosComplexos;
	}

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,	javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REFRESH, javax.persistence.CascadeType.DETACH })
	public KProcesso getTipo() {
		return tipo;
	}

	public void setTipo(KProcesso parKProcesso) {
		this.tipo = parKProcesso;
	}

	@ManyToMany(targetEntity = CompPPMacroatividade.class, fetch = FetchType.EAGER)
	public Set<CompPPMacroatividade> getMacroAtividades() {
		return macroAtividades;
	}

	public void setMacroAtividades(Set<CompPPMacroatividade> compPPMacroAtividade) {
		this.macroAtividades = compPPMacroAtividade;
	}

	public void addCompPPMacroAtividade(CompPPMacroatividade compPPMacroAtividade) {
		this.macroAtividades.add(compPPMacroAtividade);
	}

	@OneToMany(targetEntity = DependenciaMacroAtividades.class, fetch = FetchType.LAZY)
	public Set<DependenciaMacroAtividades> getDependenciaMacroAtividades() {
		return dependenciaMacroAtividades;
	}

	public void setDependenciaMacroAtividades(Set<DependenciaMacroAtividades> dependenciaMacroAtividades) {
		this.dependenciaMacroAtividades = dependenciaMacroAtividades;
	}

	@Override
	public CompPPProcessoSimples clone() throws CloneNotSupportedException {
		CompPPProcessoSimples copia = (CompPPProcessoSimples) super.clone();
		
		Set<CompPPProcessoComplexo> processosComplexosCopia = new HashSet<CompPPProcessoComplexo>();
		Set<CompPPMacroatividade> macroAtividadesCopia = new HashSet<CompPPMacroatividade>(); 
		Set<DependenciaMacroAtividades> dependenciaMacroAtividadesCopia = new HashSet<DependenciaMacroAtividades>();
		
		for(CompPPProcessoComplexo complexo : this.getProcessosComplexos()){
			processosComplexosCopia.add(complexo.clone());
		}
		
		for(CompPPMacroatividade macroatividade : this.getMacroAtividades()){
			macroAtividadesCopia.add(macroatividade.clone());
		}
		
		for(DependenciaMacroAtividades macroDependencia : this.getDependenciaMacroAtividades()){
			dependenciaMacroAtividadesCopia.add(macroDependencia);
		}
		
		//copia.setTipo(this.getTipo().clone());
		copia.setEngenharia(this.getEngenharia());
		copia.setProcessosComplexos(processosComplexosCopia);
		copia.setMacroAtividades(macroAtividadesCopia);
		copia.setDependenciaMacroAtividades(dependenciaMacroAtividadesCopia);
		//copia.setAtividadeProcessoPadrao(this.getAtividadeProcessoPadrao().clone());
		
		return copia;
		
		/*CompPPProcessoSimples copia = (CompPPProcessoSimples) super.clone();
		copia.setMacroAtividades(new HashSet<CompPPMacroatividade>());
		
		for (CompPPMacroatividade ppMacro : this.getMacroAtividades()) {
			copia.getMacroAtividades().add(ppMacro.clone());
		}
		*/
		

		
		/*copia.setAtividadeProcessoPadrao(new AtividadeProcessoPadrao());
		this.getAtividadeProcessoPadrao().CopiaAtvPara(copia.getAtividadeProcessoPadrao());
		*/
	}

}
