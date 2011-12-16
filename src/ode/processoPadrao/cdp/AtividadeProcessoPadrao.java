package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcedimento;
import ode.conhecimento.processo.cdp.KRecurso;
import ode.conhecimento.processo.cdp.KRecursoHumano;

@Entity
public class AtividadeProcessoPadrao extends ObjetoPersistente {

	private static final long serialVersionUID = -4358851167826004487L;
	private String nome;

	private boolean ehMarco;

	private Set<KRecursoHumano> kRecursoHumano;
	private Set<KArtefato> kInsumos;
	private Set<KArtefato> kProdutos;
	private Set<AtividadeProcessoPadrao> subAtividades;
	private Set<AtividadeProcessoPadrao> preAtividades;

	private KAtividade tipo;

	private Set<KRecurso> recursoRequerido;
	private Set<KProcedimento> procedimentoAdotado;

	public AtividadeProcessoPadrao() {
		this.setKRecursoHumano(new HashSet<KRecursoHumano>());
		this.setKInsumos(new HashSet<KArtefato>());
		this.setKProdutos(new HashSet<KArtefato>());
		this.setSubAtividades(new HashSet<AtividadeProcessoPadrao>());
		this.setPreAtividades(new HashSet<AtividadeProcessoPadrao>());
	}

	/**
	 * Ontém os KRecursoHumano cascade = "none"
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KRecursoHumano> getKRecursoHumano() {
		return kRecursoHumano;
	}

	public void setKRecursoHumano(Set<KRecursoHumano> parKRecursoHumano) {
		this.kRecursoHumano = parKRecursoHumano;
	}

	/**
	 * Ontém os KArtefatos cosumidos
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KArtefato> getKInsumos() {
		return kInsumos;
	}

	public void setKInsumos(Set<KArtefato> parKInsumos) {
		this.kInsumos = parKInsumos;
	}

	/**
	 * Ontém os KArtefatos produzidos
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KArtefato> getKProdutos() {
		return kProdutos;
	}

	public void setKProdutos(Set<KArtefato> parKProdutos) {
		this.kProdutos = parKProdutos;
	}

	/**
	 * Obtém as SubAtividades. inverse = "true"
	 */
	@ManyToMany(cascade = { javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinTable(name = "atividadeProcessoPadrao_subAtividade")
	public Set<AtividadeProcessoPadrao> getSubAtividades() {
		return subAtividades;
	}

	public void setSubAtividades(Set<AtividadeProcessoPadrao> parSubAtividades) {
		this.subAtividades = parSubAtividades;
	}

	/**
	 * Esse método, diferente do método setSubAtividades, adiciona mais
	 * elementos a lista já criada.
	 * 
	 * @param parSubAtividades
	 *            subAtividades que serão adicionadas
	 */
	public void addSubAtividades(Set<AtividadeProcessoPadrao> subAtividades) {
		this.subAtividades.addAll(subAtividades);
	}

	/**
	 * Obtém as pré-Atividades da atividade
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "atividadeProcessoPadrao_preAtividade")
	public Set<AtividadeProcessoPadrao> getPreAtividades() {
		return preAtividades;
	}

	public void setPreAtividades(Set<AtividadeProcessoPadrao> parPreAtividades) {
		this.preAtividades = parPreAtividades;
	}

	/**
	 * Obtém o nome da atividade
	 * 
	 */
	@Column()
	public String getNome() {
		return this.nome;
	}

	public void setNome(String parNome) {
		this.nome = parNome;
	}

	@ManyToOne(cascade = { javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH }, fetch = FetchType.EAGER)
	public KAtividade getTipo() {
		return this.tipo;
	}

	public void setTipo(KAtividade tipo) {
		this.tipo = tipo;
	}

	public boolean ehKInsumo(KArtefato parKAterfato) {
		return this.kInsumos.contains(parKAterfato);
	}

	public boolean ehKProduto(KArtefato parKAterfato) {
		return this.kProdutos.contains(parKAterfato);
	}

	public String toString() {
		return this.nome;

	}

	public boolean isEhMarco() {
		return ehMarco;
	}

	public void setEhMarco(boolean ehMarco) {
		this.ehMarco = ehMarco;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KRecurso> getRecursoRequerido() {
		return recursoRequerido;
	}

	public void setRecursoRequerido(Set<KRecurso> recursoRequerido) {
		this.recursoRequerido = recursoRequerido;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KProcedimento> getProcedimentoAdotado() {
		return procedimentoAdotado;
	}

	public void setProcedimentoAdotado(Set<KProcedimento> procedimentoAdotado) {
		this.procedimentoAdotado = procedimentoAdotado;
	}

}
