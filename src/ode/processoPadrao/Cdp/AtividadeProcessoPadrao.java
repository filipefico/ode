package ode.processoPadrao.Cdp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import nucleo.comuns.persistencia.ObjetoPersistente;

import org.apache.tools.ant.taskdefs.Length;

import com.sun.xml.internal.ws.api.PropertySet.Property;

import antlr.collections.List;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.conhecimento.processo.Cdp.KMetodo;
import ode.conhecimento.processo.Cdp.KNorma;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cdp.KRoteiro;
import ode.conhecimento.processo.Cdp.KTecnica;

@Entity
public class AtividadeProcessoPadrao extends ObjetoPersistente {

	private static final long serialVersionUID = -4358851167826004487L;
	/** Nome da Atividade */
    private String nome;
    /** Descrição da Atividade */
    private String descricao;
    /** Diz se a atividade é ou não obrigatória */
    private boolean ehObrigatoria;
    /** Recursos Humanos requeridos */
    private Set<KRecursoHumano> kRecursoHumano;
    /** Recursos de Hardware requeridos */
    private Set<KRecursoHardware> kRecursoHardware;
    /** Ferramentas de Software requeridas */
    private Set<KFerramentaSoftware> kFerramentaSoftware;
    /** Artefatos requeridos */
    private Set<KArtefato> kInsumos;
    /** Artefatos produzidos */
    private Set<KArtefato> kProdutos;
    /** Métodos utilizados */
    private Set<KMetodo> kMetodos;
    /** Normas utilizadas */
    private Set<KNorma> kNormas;
    /** Roteiros utilizados */
    private Set<KRoteiro> kRoteiros;
    /** Técnicas utilizadas */
    private Set<KTecnica> kTecnicas;
    /** SuperAtividade */
    private AtividadeProcessoPadrao superAtividade;
    /** SubAtividades */
    private Set<AtividadeProcessoPadrao> subAtividades;
    /** Pré-Atividades */
    private Set<AtividadeProcessoPadrao> preAtividades;
    /** Atividade da qual foi especializada */
    private AtividadeProcessoPadrao atividadeOrigem;
    /** Conhecimento Atividade */
    private KAtividade kAtividade;
    /** Processo Padrão Específico ao qual pertence a Atividade */
    private CompPPProcessoSimples processoPadrao;

    private CompPPMacroatividade compPPMacroatividade;
    
    
    public AtividadeProcessoPadrao() {
        this.setKRecursoHumano(new HashSet<KRecursoHumano>());
        this.setKRecursoHardware(new HashSet<KRecursoHardware>());
        this.setKFerramentaSoftware(new HashSet<KFerramentaSoftware>());
        this.setKInsumos(new HashSet<KArtefato>());
        this.setKProdutos(new HashSet<KArtefato>());
        this.setKMetodos(new HashSet<KMetodo>());
        this.setKNormas(new HashSet<KNorma>());
        this.setKRoteiros(new HashSet<KRoteiro>());
        this.setKTecnicas(new HashSet<KTecnica>());
        this.setSubAtividades(new HashSet<AtividadeProcessoPadrao>());
        this.setPreAtividades(new HashSet<AtividadeProcessoPadrao>());
    }
    
    
    /** Verifica se a Atividade é obrigatória
     *  @hibernate.property
     */
    @Column
    public boolean isEhObrigatoria() {
        return ehObrigatoria;
    }
    
    public void setEhObrigatoria(boolean parEhObrigatoria) {
        this.ehObrigatoria = parEhObrigatoria;
    }
    
    
    /** Ontém os KRecursoHumano
     *   cascade = "none"
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KRecursoHumano.class,fetch=FetchType.EAGER)
    public Set<KRecursoHumano> getKRecursoHumano() {
        return kRecursoHumano;
    }
    
    public void setKRecursoHumano(Set<KRecursoHumano> parKRecursoHumano) {
        this.kRecursoHumano = parKRecursoHumano;
    }
    
    
    /** Ontém os KRecursoHardware
     *   cascade = "none"
     */
    @ManyToMany(targetEntity = KRecursoHardware.class,fetch=FetchType.EAGER)
    public Set<KRecursoHardware> getKRecursoHardware() {
        return kRecursoHardware;
    }
    
    public void setKRecursoHardware(Set<KRecursoHardware> parKRecursoHardware) {
        this.kRecursoHardware = parKRecursoHardware;
    }
    
    
    /** Ontém os KFerramentaSoftware
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KFerramentaSoftware.class,fetch=FetchType.EAGER)
    public Set<KFerramentaSoftware> getKFerramentaSoftware() {
        return kFerramentaSoftware;
    }
    
    public void setKFerramentaSoftware(Set<KFerramentaSoftware> parKFerramentaSoftware) {
        this.kFerramentaSoftware = parKFerramentaSoftware;
    }
    
    
    /** Ontém os KArtefatos cosumidos
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KArtefato.class,fetch=FetchType.EAGER)
    public Set<KArtefato> getKInsumos() {
        return kInsumos;
    }
    
    public void setKInsumos(Set<KArtefato> parKInsumos) {
        this.kInsumos = parKInsumos;
    }
    
    
    /** Ontém os KArtefatos produzidos
      */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KArtefato.class,fetch=FetchType.EAGER)
    public Set<KArtefato> getKProdutos() {
        return kProdutos;
    }
    
    public void setKProdutos(Set<KArtefato> parKProdutos) {
        this.kProdutos = parKProdutos;
    }
    
    
    /** Ontém os KMetodos utilizados
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KMetodo.class,fetch=FetchType.EAGER)
    public Set<KMetodo> getKMetodos() {
        return kMetodos;
    }
    
    public void setKMetodos(Set<KMetodo> parKMetodos) {
        this.kMetodos = parKMetodos;
    }
    
    
    /** Ontém as KNormas utilizadas
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KNorma.class,fetch=FetchType.EAGER)
    public Set<KNorma> getKNormas() {
        return kNormas;
    }
    
    public void setKNormas(Set<KNorma> parKNormas) {
        this.kNormas = parKNormas;
    }
    
    
    /** Ontém os KRoteiros utilizados
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KRoteiro.class,fetch=FetchType.EAGER)
    public Set<KRoteiro> getKRoteiros() {
        return kRoteiros;
    }
    
    public void setKRoteiros(Set<KRoteiro> parKRoteiros) {
        this.kRoteiros = parKRoteiros;
    }
    
    
    /** Ontém as KTecnicas utilizadas
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KTecnica.class,fetch=FetchType.EAGER)
    public Set<KTecnica> getKTecnicas() {
        return kTecnicas;
    }
    
    public void setKTecnicas(Set<KTecnica> parKTecnicas) {
        this.kTecnicas = parKTecnicas;
    }
    
    
    /** Obtém a SuperAtividade.
     *   not-null = "false"
     */
    @ManyToOne(targetEntity = AtividadeProcessoPadrao.class)
    public AtividadeProcessoPadrao getSuperAtividade() {
        return superAtividade;
    }
    
    public void setSuperAtividade(AtividadeProcessoPadrao parSuperAtividade) {
        this.superAtividade = parSuperAtividade;
    }
    
    
    /** Obtém as SubAtividades.
     *    inverse = "true"
     */
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = AtividadeProcessoPadrao.class,fetch=FetchType.LAZY)
    public Set<AtividadeProcessoPadrao> getSubAtividades() {
        return subAtividades;
    }
    
    public void setSubAtividades(Set<AtividadeProcessoPadrao> parSubAtividades) {
        this.subAtividades = parSubAtividades;
    }
    
    
    /** Obtém as pré-Atividades da atividade
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = AtividadeProcessoPadrao.class,fetch=FetchType.LAZY)
    public Set<AtividadeProcessoPadrao> getPreAtividades() {
        return preAtividades;
    }
    
    public void setPreAtividades(Set<AtividadeProcessoPadrao> parPreAtividades) {
        this.preAtividades = parPreAtividades;
    }
    
    
    /** Obtém a Atividade da qual esta foi especializada.
     *   not-null = "false"
     */
    @ManyToOne(targetEntity = AtividadeProcessoPadrao.class)
    public AtividadeProcessoPadrao getAtividadeOrigem() {
        return atividadeOrigem;
    }
    
    public void setAtividadeOrigem(AtividadeProcessoPadrao parAtividadeOrigem) {
        this.atividadeOrigem = parAtividadeOrigem;
    }
    
    
    /** Obtém o nome da atividade
     *  
     */
    @Column(length = 80)
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String parNome){
        this.nome = parNome;
    }
    
    
    /** Obtém o conhecimento atividade
     *   not-null = "true"
    */ 
    @ManyToOne(targetEntity = KAtividade.class)
    @JoinColumn(nullable=false)
    public KAtividade getKAtividade(){
        return this.kAtividade;
    }
    
    public void setKAtividade(KAtividade parKAtividade){
        this.kAtividade = parKAtividade;
    }
    
    
    /** Obtém o Processo Padrão Específico.
     *   not-null = "false"
     */
    @ManyToOne(targetEntity = CompPPProcessoSimples.class)
    public CompPPProcessoSimples getProcessoPadrao(){
        return this.processoPadrao;
    }
    
    public void setProcessoPadrao(CompPPProcessoSimples parPP){
        this.processoPadrao = parPP;
    }
    
    /** Obtém a descrição da atividade
     */
    @Column(length=500)    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *   not-null = "false"
   */     
    @ManyToOne(targetEntity = CompPPMacroatividade.class)
    public CompPPMacroatividade getCompPPMacroatividade() {
        return compPPMacroatividade;
    }

    public void setCompPPMacroatividade(CompPPMacroatividade compPPMacroatividade) {
        this.compPPMacroatividade = compPPMacroatividade;
    }


    
    public boolean ehKInsumo(KArtefato parKAterfato){
        return this.kInsumos.contains(parKAterfato);
    }
    
    
    public boolean ehKProduto(KArtefato parKAterfato){
        return this.kProdutos.contains(parKAterfato);
    }
    
    /** Retorna todas as subatividades diretas da atividade (do set subAtividades) e suas respectivas subAtividades */
    public ArrayList<AtividadeProcessoPadrao> obterTodasSubAtividades(){
        Iterator<AtividadeProcessoPadrao> it = this.subAtividades.iterator();
        ArrayList<AtividadeProcessoPadrao> locSubAtividades = new ArrayList<AtividadeProcessoPadrao>(this.subAtividades);
        while (it.hasNext()){
            AtividadeProcessoPadrao locSubAtividade = (AtividadeProcessoPadrao) it.next();
            locSubAtividades.addAll((Collection<? extends AtividadeProcessoPadrao>) locSubAtividade.obterTodasSubAtividades());
        }
        
        return locSubAtividades;
    }
    
    
    /** Ordena uma lista de atividades a partir de suas pré-atividades*/
    public static ArrayList<AtividadeProcessoPadrao> ordenarAtividades(ArrayList<AtividadeProcessoPadrao> parAtividades) {
        int locTamanho = parAtividades.size();
        boolean[][]  locMatriz = new boolean[locTamanho][locTamanho];
        int[] locVetor = new int[locTamanho];
        
        //percorrer a lista montando a matriz de pre-atividades
        for (int i=0; i<locTamanho; i++){
            AtividadeProcessoPadrao locAtividade = (AtividadeProcessoPadrao)parAtividades.get(i);
            ArrayList<AtividadeProcessoPadrao> locPreAtividades = new ArrayList<AtividadeProcessoPadrao>(locAtividade.getPreAtividades());
            for (int j=0; j<locPreAtividades.size(); j++){
                AtividadeProcessoPadrao locPreAtividade = (AtividadeProcessoPadrao)locPreAtividades.get(j);
                int locPosicao = parAtividades.indexOf(locPreAtividade);
                if (locPosicao >= 0) {
                    locMatriz[i][locPosicao] = true;
                    locVetor[i]++;
                }
            }
        }
        
        ArrayList<AtividadeProcessoPadrao> locAtividadesOrdenadas = new ArrayList<AtividadeProcessoPadrao>(locTamanho);
        // Ordenando a lista recursivamente
        for (int r=0; r<locTamanho; r++) {
            if (locVetor[r] == 0)
                ordenarRecursivamente(locMatriz, locVetor, r, parAtividades, locAtividadesOrdenadas);
        }
        return locAtividadesOrdenadas;
    }
    
    /** Ordena uma lista de atividades recursivamente. */
    private static void ordenarRecursivamente(boolean[][] parMatriz, int[] parVetor, int parIndice, ArrayList<AtividadeProcessoPadrao> parAtividades, ArrayList<AtividadeProcessoPadrao> parAtividadesOrdenadas) {
        parVetor[parIndice]=-1;
        AtividadeProcessoPadrao locAtividade = (AtividadeProcessoPadrao)parAtividades.get(parIndice);
        parAtividadesOrdenadas.add(locAtividade);
        
        for (int i=0; i<parAtividades.size(); i++) {
            if (parMatriz[i][parIndice] == true){
                parVetor[i]--;
                if (parVetor[i] == 0)
                    ordenarRecursivamente(parMatriz, parVetor, i, parAtividades, parAtividadesOrdenadas);
            }
        }
    }
    
    
    
    public String toString(){
        return this.nome;
        
    }
    
}
