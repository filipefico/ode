package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ode.conhecimento.processo.cdp.KProcesso;

@Entity
public class CompPPProcessoSimples extends CompPP implements Cloneable {

	private static final long serialVersionUID = -1003139827021069271L;

	private Set<CompPPProcessoComplexo> processosComplexos;
	private Set<CompPPMacroatividade> macroAtividades; // trocar esse nome
	private KProcesso tipo;
	private Set<DependenciaMacroAtividades> dependenciaMacroAtividades;

	public CompPPProcessoSimples() {
		this.processosComplexos = new HashSet<CompPPProcessoComplexo>();
		this.macroAtividades = new HashSet<CompPPMacroatividade>();
		this.dependenciaMacroAtividades = new HashSet<DependenciaMacroAtividades>();
	}

	@ManyToMany(targetEntity = CompPPProcessoComplexo.class, fetch = FetchType.EAGER)
	public Set<CompPPProcessoComplexo> getProcessosComplexos() {
		return processosComplexos;
	}

	public void setProcessosComplexos(
			Set<CompPPProcessoComplexo> parProcessosComplexos) {
		this.processosComplexos = parProcessosComplexos;
	}

	@ManyToOne(cascade = { javax.persistence.CascadeType.MERGE,
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REFRESH,
			javax.persistence.CascadeType.DETACH })
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

	public void setMacroAtividades(
			Set<CompPPMacroatividade> compPPMacroAtividade) {
		this.macroAtividades = compPPMacroAtividade;
	}

	public void addCompPPMacroAtividade(
			CompPPMacroatividade compPPMacroAtividade) {
		this.macroAtividades.add(compPPMacroAtividade);
	}

	@OneToMany(targetEntity = DependenciaMacroAtividades.class, fetch = FetchType.LAZY)
	public Set<DependenciaMacroAtividades> getDependenciaMacroAtividades() {
		return dependenciaMacroAtividades;
	}

	public void setDependenciaMacroAtividades(
			Set<DependenciaMacroAtividades> dependenciaMacroAtividades) {
		this.dependenciaMacroAtividades = dependenciaMacroAtividades;
	}

	@Override
	public CompPPProcessoSimples clone() throws CloneNotSupportedException {
		CompPPProcessoSimples copia = (CompPPProcessoSimples) super.clone();
		copia.setMacroAtividades(new HashSet<CompPPMacroatividade>());

		for (CompPPMacroatividade ppMacro : this.getMacroAtividades()) {
			copia.getMacroAtividades().add(
					(CompPPMacroatividade) ppMacro.clone());
		}
		return copia;
	}

}
