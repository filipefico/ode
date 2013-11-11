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

import org.jcodings.util.Hash;

import ode._controleProcesso.cdp.Atividade;
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
	private String descricao;

	private boolean ehMarco;

	private Set<KArtefato> insumos;
	private Set<KArtefato> produtos;
	private Set<AtividadeProcessoPadrao> subAtividades;
	private Set<AtividadeProcessoPadrao> preAtividades;

	private KAtividade tipo;

	// private Set<KRecurso> recursoRequerido;
	private Set<KRecursoHumano> recursoHumano;
	private Set<KRecursoHardware> recursoHardware;
	private Set<KFerramentaSoftware> ferramentaSoftware;


	// private Set<KProcedimento> procedimentoAdotado;
	private Set<KNorma> procedimentoNorma;
	private Set<KRoteiro> procedimentoRoteiro;
	private Set<KMetodo> procedimentoMetodo;
	private Set<KTecnica> procedimentoTecnica;

	public AtividadeProcessoPadrao() {
		this.setNome(new String("sem nome"));
		this.setDescricao(new String("sem descrição"));
		this.setInsumos(new HashSet<KArtefato>());
		this.setProdutos(new HashSet<KArtefato>());
		this.setSubAtividades(new HashSet<AtividadeProcessoPadrao>());
		this.setPreAtividades(new HashSet<AtividadeProcessoPadrao>());
		this.setEhMarco(false);

		this.setRecursoHumano(new HashSet<KRecursoHumano>());
		this.setRecursoHardware(new HashSet<KRecursoHardware>());
		this.setFerramentaSoftware(new HashSet<KFerramentaSoftware>());

		procedimentoNorma = new HashSet<KNorma>();
		procedimentoRoteiro = new HashSet<KRoteiro>();
		procedimentoMetodo = new HashSet<KMetodo>();
		procedimentoTecnica = new HashSet<KTecnica>();

	}

	public void CopiaAtvPara (AtividadeProcessoPadrao Atv){
		
		Atv.setInsumos            (getcopiaArtefatos          (this.getInsumos()));	
		Atv.setProdutos           (getcopiaArtefatos          (this.getProdutos()));
		Atv.setSubAtividades      (getcopiaSubOUPreAtividades (this.getSubAtividades()));
		Atv.setPreAtividades      (getcopiaSubOUPreAtividades (this.getPreAtividades()));
		Atv.setRecursoHumano      (getcopiaRecursoHumano      (this.getRecursoHumano()));
		Atv.setRecursoHardware    (getcopiaRecursoHardware    (this.getRecursoHardware()));
		Atv.setFerramentaSoftware (getcopiaFerramentaSoftware (this.getFerramentaSoftware()));
		Atv.setProcedimentoNorma  (getcopiaProcedimentoNorma  (this.getProcedimentoNorma()));
		Atv.setProcedimentoRoteiro(getcopiaProcedimentoRoteiro(this.getProcedimentoRoteiro()));
		Atv.setProcedimentoMetodo (getcopiaProcedimentoMetodo (this.getProcedimentoMetodo()));
		Atv.setProcedimentoTecnica(getcopiaProcedimentoTecnica(this.getProcedimentoTecnica()));
	}
	
	
	public Set<KArtefato> getcopiaArtefatos(Set<KArtefato> listaSet){
		Set<KArtefato> listaNova = new HashSet<KArtefato>();
		
		listaNova.addAll(listaSet);
		
		/*for(KArtefato kArtefato : listaSet){
			try {
				listaNova.add(kArtefato.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		return listaNova;
	}
	
	Set<AtividadeProcessoPadrao> getcopiaSubOUPreAtividades(Set<AtividadeProcessoPadrao> listaSet){
		Set<AtividadeProcessoPadrao> listaNova = new HashSet<AtividadeProcessoPadrao>();

		listaNova.addAll(listaSet);
		
		/*for(AtividadeProcessoPadrao Atv : listaSet){
			try {
				listaNova.add(Atv.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
	}
	
	public Set<KRecursoHumano> getcopiaRecursoHumano(Set<KRecursoHumano> listaSet){
		Set<KRecursoHumano> listaNova = new HashSet<KRecursoHumano>();

		listaNova.addAll(listaSet);
		
/*		for(KRecursoHumano kRecursoHumano : listaSet){
			try {
				listaNova.add(kRecursoHumano.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
	}
	
	public Set<KFerramentaSoftware> getcopiaFerramentaSoftware(Set<KFerramentaSoftware> listaSet){
		Set<KFerramentaSoftware> listaNova = new HashSet<KFerramentaSoftware>();
		
		listaNova.addAll(listaSet);
		
		/*for(KFerramentaSoftware kFerramentaSoftware : listaSet){
			try {
				listaNova.add(kFerramentaSoftware.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
	}
	
	public Set<KRecursoHardware> getcopiaRecursoHardware(Set<KRecursoHardware> listaSet){
		Set<KRecursoHardware> listaNova = new HashSet<KRecursoHardware>();
		
		listaNova.addAll(listaSet);
		
/*		for(KRecursoHardware kRecursoHardware : listaSet){
			try {
				listaNova.add(kRecursoHardware.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
	}
	
	public Set<KNorma> getcopiaProcedimentoNorma(Set<KNorma> listaSet){
		Set<KNorma> listaNova = new HashSet<KNorma>();
		
		listaNova.addAll(listaSet);
		
/*		for(KNorma kNorma : listaSet){
			try {
				listaNova.add(kNorma.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
	}
	
	public Set<KRoteiro> getcopiaProcedimentoRoteiro(Set<KRoteiro> listaSet){
		Set<KRoteiro> listaNova = new HashSet<KRoteiro>();		
		
		listaNova.addAll(listaSet);
		
/*		for(KRoteiro kRoteiro : listaSet){
			try {
				listaNova.add(kRoteiro.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
	}
	
	public Set<KMetodo> getcopiaProcedimentoMetodo(Set<KMetodo> listaSet){
		Set<KMetodo> listaNova = new HashSet<KMetodo>();
		
		listaNova.addAll(listaSet);
		
/*		for(KMetodo kMetodo : listaSet){
			try {
				listaNova.add(kMetodo.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
	}
	
	public Set<KTecnica> getcopiaProcedimentoTecnica(Set<KTecnica> listaSet){
		Set<KTecnica> listaNova = new HashSet<KTecnica>();
		
		listaNova.addAll(listaSet);
		
/*		for(KTecnica kTecnica : listaSet){
			try {
				listaNova.add(kTecnica.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		return listaNova;
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
	public Set<KFerramentaSoftware> getFerramentaSoftware() {
		return ferramentaSoftware;
	}

	public void setFerramentaSoftware(Set<KFerramentaSoftware> recursoSoftware) {
		this.ferramentaSoftware = recursoSoftware;
	}

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
	@ManyToMany(cascade = { javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER)
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

	public String getDescricao() {
		return this.descricao;
	}
	
	public void setNome(String parNome) {
		this.nome = parNome;
	}

	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	@ManyToOne(cascade = { javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER)
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
	
	/*
	// Contrutor de Cópia:
    @Override
    public AtividadeProcessoPadrao clone() throws CloneNotSupportedException{
    	AtividadeProcessoPadrao copia = new AtividadeProcessoPadrao();
    	
    	Set<KArtefato> insumosCopia = new HashSet<KArtefato>();
    	Set<KArtefato> produtosCopia = new HashSet<KArtefato>();
    	Set<AtividadeProcessoPadrao> subAtividadesCopia = new HashSet<AtividadeProcessoPadrao>();
    	Set<AtividadeProcessoPadrao> preAtividadesCopia = new HashSet<AtividadeProcessoPadrao>();
    	Set<KNorma> procedimentoNormaCopia = new HashSet<KNorma>();
    	Set<KRoteiro> procedimentoRoteiroCopia = new HashSet<KRoteiro>();
    	Set<KMetodo> procedimentoMetodoCopia = new HashSet<KMetodo>();
    	Set<KTecnica> procedimentoTecnicaCopia = new HashSet<KTecnica>();
    	Set<KRecursoHumano> recursoHumanoCopia = new HashSet<KRecursoHumano>();
    	Set<KRecursoHardware> recursoHardwareCopia = new HashSet<KRecursoHardware>();
    	Set<KFerramentaSoftware> ferramentaSoftwareCopia = new HashSet<KFerramentaSoftware>();
    	
    	for(KArtefato kArtefato : this.getInsumos()){
    		insumosCopia.add(kArtefato.clone());
    	}
    	
    	for(KArtefato kArtefato : this.getProdutos()){
    		produtosCopia.add(kArtefato.clone());
    	}
    	
    	for(AtividadeProcessoPadrao Atv : this.getSubAtividades()){
    		subAtividadesCopia.add(Atv.clone());
    	}
    	
    	for(AtividadeProcessoPadrao Atv : this.getPreAtividades()){
    		preAtividadesCopia.add(Atv.clone());
    	}
    	
    	for(KNorma kNorma : this.getProcedimentoNorma()){
    		procedimentoNormaCopia.add(kNorma.clone());
    	}
    	
    	for(KRoteiro kRoteiro : this.getProcedimentoRoteiro()){
    		procedimentoRoteiroCopia.add(kRoteiro.clone());
    	}
    	
    	for(KMetodo kMetodo : this.getProcedimentoMetodo()){
    		procedimentoMetodoCopia.add(kMetodo.clone());
    	}
    	
    	for(KTecnica kTecnica : this.getProcedimentoTecnica()){
    		procedimentoTecnicaCopia.add(kTecnica.clone());
    	}
    	
    	for(KRecursoHumano kRecursoHumano : this.getRecursoHumano()){
    		recursoHumanoCopia.add(kRecursoHumano.clone());
    	}
    	
    	for(KRecursoHardware kRecursoHardware : this.getRecursoHardware()){
    		recursoHardwareCopia.add(kRecursoHardware.clone());
    	}
    	
    	for(KFerramentaSoftware kFerramentaSoftware : this.getFerramentaSoftware()){
    		ferramentaSoftwareCopia.add(kFerramentaSoftware.clone());
    	}
    	
    	copia.setInsumos(insumosCopia);
    	copia.setProdutos(produtosCopia);
    	copia.setSubAtividades(subAtividadesCopia);
    	copia.setPreAtividades(preAtividadesCopia);
    	copia.setProcedimentoNorma(procedimentoNormaCopia);
    	copia.setProcedimentoRoteiro(procedimentoRoteiroCopia);
    	copia.setProcedimentoMetodo(procedimentoMetodoCopia);
    	copia.setProcedimentoTecnica(procedimentoTecnicaCopia);

    	copia.setRecursoHumano(recursoHumanoCopia);
    	copia.setRecursoHardware(recursoHardwareCopia);
    	copia.setFerramentaSoftware(ferramentaSoftwareCopia);
    	
    	//copia.setNome(new String(this.getNome()));
    	copia.setEhMarco(this.isEhMarco());
    	
    	if(this.getTipo() != null){
    		copia.setTipo(this.getTipo().clone());
    	}
    	
    	return copia;
    }*/

}
