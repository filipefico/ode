package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ode.conhecimento.processo.cdp.KProcesso;

@Entity
public class CompPPProcessoSimples extends CompPP {

	private static final long serialVersionUID = -1003139827021069271L;

	private Set<CompPPProcessoComplexo> processosComplexos;
	private Set<CompPPMacroatividade> compPPMacroAtividade; // trocar esse nome
	private KProcesso tipo;
	private Set<DependenciaMacroAtividades> dependenciaMacroAtividades;
	

	public CompPPProcessoSimples() {
		this.processosComplexos = new HashSet<CompPPProcessoComplexo>();
		this.compPPMacroAtividade = new HashSet<CompPPMacroatividade>();
		this.dependenciaMacroAtividades = new HashSet<DependenciaMacroAtividades>();
	}
	
	@ManyToMany(targetEntity = CompPPProcessoComplexo.class, fetch = FetchType.EAGER)
    public Set<CompPPProcessoComplexo> getProcessosComplexos() {
        return processosComplexos;
    }
    
    public void setProcessosComplexos(Set<CompPPProcessoComplexo> parProcessosComplexos) {
        this.processosComplexos = parProcessosComplexos;
    }
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KProcesso.class)
	@JoinColumn(nullable = false)
	public KProcesso getTipo() {
		return tipo;
	}

	public void setTipo(KProcesso parKProcesso) {
		this.tipo = parKProcesso;
	}

	@ManyToMany(targetEntity = CompPPMacroatividade.class, fetch = FetchType.EAGER)
	public Set<CompPPMacroatividade> getCompPPMacroAtividade() {
		return compPPMacroAtividade;
	}

	public void setCompPPMacroAtividade(
			Set<CompPPMacroatividade> compPPMacroAtividade) {
		this.compPPMacroAtividade = compPPMacroAtividade;
	}

	public void addCompPPMacroAtividade(
			CompPPMacroatividade compPPMacroAtividade) {
		this.compPPMacroAtividade.add(compPPMacroAtividade);
	}

	@OneToMany(targetEntity = DependenciaMacroAtividades.class, fetch = FetchType.LAZY)
	public Set<DependenciaMacroAtividades> getDependenciaMacroAtividades() {
		return dependenciaMacroAtividades;
	}

	public void setDependenciaMacroAtividades(
			Set<DependenciaMacroAtividades> dependenciaMacroAtividades) {
		this.dependenciaMacroAtividades = dependenciaMacroAtividades;
	}

}
