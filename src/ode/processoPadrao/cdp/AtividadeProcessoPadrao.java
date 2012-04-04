package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.management.loading.PrivateMLet;
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
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KMetodo;
import ode.conhecimento.processo.cdp.KNorma;
import ode.conhecimento.processo.cdp.KRecursoHardware;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cdp.KRoteiro;
import ode.conhecimento.processo.cdp.KTecnica;

@Entity
public class AtividadeProcessoPadrao extends ObjetoPersistente {

	private static final long serialVersionUID = -4358851167826004487L;
	private String nome;

	private boolean ehMarco;

	private Set<KArtefato> insumos;
	private Set<KArtefato> produtos;
	private Set<AtividadeProcessoPadrao> subAtividades;
	private Set<AtividadeProcessoPadrao> preAtividades;

	private KAtividade tipo;

	// private Set<KRecurso> recursoRequerido;
	private Set<KRecursoHumano> recursoHumano;

	// private Set<KProcedimento> procedimentoAdotado;
	private Set<KNorma> procedimentoNorma;
	private Set<KRoteiro> procedimentoRoteiro;
	private Set<KMetodo> procedimentoMetodo;
	private Set<KTecnica> procedimentoTecnica;

	public AtividadeProcessoPadrao() {
		this.setInsumos(new HashSet<KArtefato>());
		this.setProdutos(new HashSet<KArtefato>());
		this.setSubAtividades(new HashSet<AtividadeProcessoPadrao>());
		this.setPreAtividades(new HashSet<AtividadeProcessoPadrao>());

		this.setRecursoHumano(new HashSet<KRecursoHumano>());
		this.setRecursoHardware(new HashSet<KRecursoHardware>());
		this.setRecursoSoftware(new HashSet<KFerramentaSoftware>());

		procedimentoNorma = new HashSet<KNorma>();
		procedimentoRoteiro = new HashSet<KRoteiro>();
		procedimentoMetodo = new HashSet<KMetodo>();
		procedimentoTecnica = new HashSet<KTecnica>();

	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KRecursoHumano> getRecursoHumano() {
		return recursoHumano;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KRecursoHardware> getRecursoHardware() {
		return recursoHardware;
	}

	public void setRecursoHardware(Set<KRecursoHardware> recursoHardware) {
		this.recursoHardware = recursoHardware;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KFerramentaSoftware> getRecursoSoftware() {
		return recursoSoftware;
	}

	public void setRecursoSoftware(Set<KFerramentaSoftware> recursoSoftware) {
		this.recursoSoftware = recursoSoftware;
	}

	private Set<KRecursoHardware> recursoHardware;
	private Set<KFerramentaSoftware> recursoSoftware;

	public void setRecursoHumano(Set<KRecursoHumano> parKRecursoHumano) {
		this.recursoHumano = parKRecursoHumano;
	}

	/**
	 * Ontém os KArtefatos cosumidos
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AtividadeProcessoPadrao_Insumos")
	public Set<KArtefato> getInsumos() {
		return insumos;
	}

	public void setInsumos(Set<KArtefato> parKInsumos) {
		this.insumos = parKInsumos;
	}

	/**
	 * Ontém os KArtefatos produzidos
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AtividadeProcessoPadrao_Produtos")
	public Set<KArtefato> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<KArtefato> parKProdutos) {
		this.produtos = parKProdutos;
	}

	/**
	 * Obtém as SubAtividades
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
		return this.insumos.contains(parKAterfato);
	}

	public boolean ehKProduto(KArtefato parKAterfato) {
		return this.produtos.contains(parKAterfato);
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
	public Set<KNorma> getProcedimentoNorma() {
		return procedimentoNorma;
	}

	public void setProcedimentoNorma(Set<KNorma> procedimentoNorma) {
		this.procedimentoNorma = procedimentoNorma;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KRoteiro> getProcedimentoRoteiro() {
		return procedimentoRoteiro;
	}

	public void setProcedimentoRoteiro(Set<KRoteiro> procedimentoRoteiro) {
		this.procedimentoRoteiro = procedimentoRoteiro;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KMetodo> getProcedimentoMetodo() {
		return procedimentoMetodo;
	}

	public void setProcedimentoMetodo(Set<KMetodo> procedimentoMetodo) {
		this.procedimentoMetodo = procedimentoMetodo;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<KTecnica> getProcedimentoTecnica() {
		return procedimentoTecnica;
	}

	public void setProcedimentoTecnica(Set<KTecnica> procedimentoTecnica) {
		this.procedimentoTecnica = procedimentoTecnica;
	}

}
