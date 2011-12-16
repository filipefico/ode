package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ode.conhecimento.processo.cdp.KAtividade;

@Entity
public class CompPPMacroatividade extends CompPP {

	private static final long serialVersionUID = 1258234501707911433L;
	private AtividadeProcessoPadrao atividadeProcessoPadrao;
	private Set<DependenciaMacroAtividades> preDependenciaMacroAtividade;
	private DependenciaMacroAtividades baseDependenciaMacroAtividade;
	private KAtividade tipo;

	public CompPPMacroatividade() {
		this.preDependenciaMacroAtividade = new HashSet<DependenciaMacroAtividades>();
		this.atividadeProcessoPadrao = new AtividadeProcessoPadrao();
	}

	@ManyToOne(cascade = { javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH }, fetch = FetchType.EAGER)
	public KAtividade getTipo() {
		return tipo;
	}

	public void setTipo(KAtividade tipo) {
		this.tipo = tipo;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public AtividadeProcessoPadrao getAtividadeProcessoPadrao() {
		return this.atividadeProcessoPadrao;
	}

	public void setAtividadeProcessoPadrao(
			AtividadeProcessoPadrao atividadeProcessoPadrao) {
		this.atividadeProcessoPadrao = atividadeProcessoPadrao;
	}

	/**
	 * Obtém as pré-Atividades da atividade
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<DependenciaMacroAtividades> getPreDependenciaMacroAtividade() {
		return preDependenciaMacroAtividade;
	}

	public void setPreDependenciaMacroAtividade(
			Set<DependenciaMacroAtividades> preDependenciaMacroAtividade) {
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

	@Override
	public CompPPMacroatividade clone() throws CloneNotSupportedException {
		return (CompPPMacroatividade) super.clone();
	}

}
