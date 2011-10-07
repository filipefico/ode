package ode.conhecimento.processo.cdp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KAtividade extends Conhecimento {

	private static final long serialVersionUID = 8243738924069900987L;

	/** Conhecimento Atividade base do Conhecimento Atividade. */
    private KAtividade kAtividadeBase = null;
    
    /** Processo Padrão que pertence o Conhecimento Atividade. */
   // private KProcessoPadrao kProcessoPadrao = null;
    
    /** Indica a obigatoriedade da atividade em um processo padrão. */
    private boolean obrigatoriedade = false;
    
    
    
    /** Indica quais os kprocedimentos de uma atividade*/
    private Set<KProcedimento> kProcedimentos;
    
    /** Indica quais os krecursos de uma atividade*/
    private Set<KRecurso> kRecursos;
    
    /** Indica quais os kartefatos (insumo)  de uma atividade*/
    private Set<KArtefato> insumos;
    
    /** Indica quais os kartefatos (produto)  de uma atividade*/
    private Set<KArtefato> produtos;
    
    /** Indica quais as katividades sao preatividades*/
    private Set<KAtividade> preKAtividades;
    
    /** Indica quais as katividades sao subatividades*/
    private Set<KAtividade> subKAtividades = new HashSet<KAtividade>();
    
    /** Indica o KProcesso que a KAtividade pertence */
    private KProcesso kProcesso;
    
    /** Incida as competencias de uma Atividade*/
   // private Set competencias;
    
    
    /**Construtor. */
    public KAtividade() {
    }
    
    
    
    /** Obtém o Conhecimento Atividade base.### conferir cardinalidades
     *
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KAtividade.class,fetch=FetchType.LAZY)
    @JoinColumn(nullable=true)    
    public KAtividade getKAtividadeBase() {
        return kAtividadeBase;
    }
    
    /** Atribui o Conhecimento Atividade base. */
    public void setKAtividadeBase(KAtividade parKAtividade) {
        kAtividadeBase = parKAtividade;
    }
    
    
    /** Obtém o Processo Padrão.### conferir cardinalidades
     *
     * @hibernate.many-to-one
     *   column = "idkprocessopadrao"
     *   not-null = "false"
     *   class = "Ode.Conhecimento.Processo.Cdp.KProcessoPadrao"
     *
     *
    public KProcessoPadrao getKProcessoPadrao() {
        return kProcessoPadrao;
    }
    
    /** Atribui o Processo Padrão. *
    public void setKProcessoPadrao(KProcessoPadrao parKProcessoPadrao) {
        kProcessoPadrao = parKProcessoPadrao;
    }
    
    /
    /** Obtém a obrigatoriedade da atividade. */
    @Column
    public boolean isObrigatoriedade() {
        return obrigatoriedade;
    }
    
    /** Atribui um valor para a obrigatoriedade da atividade em um processo padrão. */
    public void setObrigatoriedade(boolean parObrigatoriedade) {
        obrigatoriedade = parObrigatoriedade;
    }
    
    //////////////// ATIVIDADES ///////////////////
    

    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KAtividade.class,fetch=FetchType.LAZY)
    public Set<KAtividade> getPreAtividades() {
        return preKAtividades;
    }
    
    /** Atribui as pré-atividades do Conhecimento Atividade. */
    public void setPreAtividades(Set<KAtividade> parPreAtividades) {
        preKAtividades = parPreAtividades;
    }
    

    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KAtividade.class,fetch=FetchType.LAZY)
    public Set<KAtividade> getSubAtividades() {
        return subKAtividades;
    }
    
    /** Atribui as sub-atividades do Conhecimento Atividade. */
    public void setSubAtividades(Set<KAtividade> parSubAtividades) {
        subKAtividades = parSubAtividades;
    }
    
    
    /** Obtém o Processo do Conhecimento Atividade.
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KProcesso.class)
    public KProcesso getKProcesso(){
        return kProcesso;
    }
    
    public void setKProcesso(KProcesso parKProcessos){
        kProcesso = parKProcessos;
    }
    
    

   
    /** Obtém as competencias do Conhecimento Atividade.
     *
     *@hibernate.set
     *   table="conh_proc_katividade_kcompetencia"
     *    lazy="true"
     *@hibernate.collection-key
     *   column = "idkatividade"
     *@hibernate.collection-many-to-many
     *   column ="idkcompetencia"
     *   class="Ode.Conhecimento.organizacao.cdp.KCompetencia"
     
    public Set getCompetencias(){
        return competencias;
    }
    
    public void setCompetencias(Set parCompetencias){
        competencias = parCompetencias;
    }
    */
    
    /** Verica se um Conhecimento Atividade é Macro. 
    
    public boolean isMacroAtividade(){
        return subKAtividades.isEmpty();
    }
    */
        
    /** Adiciona as Pré-Atividades da Atividade. */
    public void pubAdicionarPreAtividades(ArrayList<KAtividade> parPreAtividades) {
        preKAtividades.addAll((Collection<? extends KAtividade>) parPreAtividades);
    }
    
    /** Remove as Pré-Atividades da Atividade. */
    public void pubRemoverPreAtividades(ArrayList<KAtividade> parPreAtividades) {
        preKAtividades.removeAll((Collection<KAtividade>) parPreAtividades);
    }
    
    
    ////////////////////// RECURSOS //////////////////////////
    
    /** Obtém os recursos do Conhecimento Atividade.
     *
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KRecurso.class,fetch=FetchType.LAZY)
    public Set<KRecurso> getRecursos() {
        return kRecursos;
    }
    
    /** Atribui os recursos do Conhecimento Atividade. */
    public void setRecursos(Set<KRecurso> parRecursos) {
        kRecursos = parRecursos;
    }
    
    /** Obtém os recursos humanos do Conhecimento Atividade. */
    public ArrayList<KRecursoHumano> lPubObterRecursosHumanos() {
        ArrayList<KRecurso> locKRecursos = new ArrayList<KRecurso>(kRecursos);
        ArrayList<KRecursoHumano> locKRecursosHumanos = new ArrayList<KRecursoHumano>();
        for (int i=0; i<kRecursos.size(); i++){
            if (locKRecursos.get(i) instanceof KRecursoHumano)
                locKRecursosHumanos.add((KRecursoHumano) locKRecursos.get(i));
        }
        
        return locKRecursosHumanos;
    }
    
    /** Obtém os recursos de hardware do Conhecimento Atividade. */
    public ArrayList<KRecursoHardware> lPubObterRecursosHardware() {
        ArrayList<KRecurso> locKRecursos = new ArrayList<KRecurso>(kRecursos);
        ArrayList<KRecursoHardware> locKRecursosHardware = new ArrayList<KRecursoHardware>();
        for (int i=0; i<kRecursos.size(); i++){
            if (locKRecursos.get(i) instanceof KRecursoHardware)
                locKRecursosHardware.add((KRecursoHardware) locKRecursos.get(i));
        }
        
        return locKRecursosHardware;
    }
    
    /** Obtém as ferramentas de software do Conhecimento Atividade. */
    public ArrayList<KFerramentaSoftware> lPubObterFerramentasSoftware() {
    	ArrayList<KRecurso> locKRecursos = new ArrayList<KRecurso>(kRecursos);
    	ArrayList<KFerramentaSoftware> locKFerramentas = new ArrayList<KFerramentaSoftware>();
        for (int i=0; i<kRecursos.size(); i++){
            if (locKRecursos.get(i) instanceof KFerramentaSoftware)
                locKFerramentas.add((KFerramentaSoftware) locKRecursos.get(i));
        }
        
        return locKFerramentas;
    }
    
    /** Adiciona os Conhecimentos Recursos da Atividade. */
    public void pubAdicionarKRecursos(ArrayList<KRecurso> parKRecursos) {
        kRecursos.addAll((Collection<? extends KRecurso>) parKRecursos);
    }
    
    /** Remove os Conhecimentos Recursos da Atividade. */
    public void pubRemoverKRecursos(ArrayList<KRecurso> parKRecursos) {
        kRecursos.removeAll(parKRecursos);
    }
    
    
    //////////////////// PROCEDIMENTOS ///////////////////////
    
    /** Obtém os procedimentos do Conhecimento Atividade. */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KProcedimento.class,fetch=FetchType.LAZY)
    public Set<KProcedimento> getProcedimentos() {
        return kProcedimentos;
    }
    
    /** Atribui os procedimentos do Conhecimento Atividade. */
    public void setProcedimentos(Set<KProcedimento> parProcedimentos) {
        kProcedimentos = parProcedimentos;
    }
    
    /** Obtém os métodos do Conhecimento Atividade. */
    public ArrayList<KMetodo> lPubObterMetodos() {
    	ArrayList<KProcedimento> locKProcedimentos = new ArrayList<KProcedimento>(kProcedimentos);
    	ArrayList<KMetodo> locKMetodos = new ArrayList<KMetodo>();
        for (int i=0; i<kProcedimentos.size(); i++){
            if (locKProcedimentos.get(i) instanceof KMetodo)
                locKMetodos.add((KMetodo) locKProcedimentos.get(i));
        }
        
        return locKMetodos;
    }
    
    /** Obtém as normas do Conhecimento Atividade. */
    public ArrayList<KNorma> lPubObterNormas() {
    	ArrayList<KProcedimento> locKProcedimentos = new ArrayList<KProcedimento>(kProcedimentos);
    	ArrayList<KNorma> locKNormas = new ArrayList<KNorma>();
        for (int i=0; i<kProcedimentos.size(); i++){
            if (locKProcedimentos.get(i) instanceof KNorma)
                locKNormas.add((KNorma) locKProcedimentos.get(i));
        }
        
        return locKNormas;
    }
    
    /** Obtém os roteiros do Conhecimento Atividade. */
    public ArrayList<KRoteiro> lPubObterRoteiros() {
    	ArrayList<KProcedimento> locKProcedimentos = new ArrayList<KProcedimento>(kProcedimentos);
    	ArrayList<KRoteiro> locKRoteiros = new ArrayList<KRoteiro>();
        for (int i=0; i<kProcedimentos.size(); i++){
            if (locKProcedimentos.get(i) instanceof KRoteiro)
                locKRoteiros.add((KRoteiro) locKProcedimentos.get(i));
        }
        
        return locKRoteiros;
    }
    
    /** Obtém as técnicas do Conhecimento Atividade. */
    public ArrayList<KTecnica> lPubObterTecnicas() {
    	ArrayList<KProcedimento> locKProcedimentos = new ArrayList<KProcedimento>(kProcedimentos);
    	ArrayList<KTecnica> locKTecnicas = new ArrayList<KTecnica>();
        for (int i=0; i<kProcedimentos.size(); i++){
            if (locKProcedimentos.get(i) instanceof KTecnica)
                locKTecnicas.add((KTecnica) locKProcedimentos.get(i));
        }
        
        return locKTecnicas;
    }
    
    /** Adiciona os Conhecimentos Procedimentos da Atividade. */
    public void pubAdicionarKProcedimentos(ArrayList<KProcedimento> parKProcedimentos) {
        kProcedimentos.addAll((Collection<? extends KProcedimento>) parKProcedimentos);
    }
    
    /** Remove os Conhecimentos Procedimentos da Atividade. */
    public void pubRemoverKProcedimentos(ArrayList<KProcedimento> parKProcedimentos) {
        kProcedimentos.removeAll(parKProcedimentos);
    }
    
    
    /////////////////// ARTEFATOS - PRODUTOS ///////////////////
    /** Obtém os produtos do Conhecimento Atividade.    */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KArtefato.class,fetch=FetchType.LAZY)
    public Set<KArtefato> getProdutos() {
        return produtos;
    }
    
    /** Atribui os produtos do Conhecimento Atividade. */
    public void setProdutos(Set<KArtefato> parProdutos) {
        produtos = parProdutos;
    }
    
    /** Adiciona os Conhecimentos Produtos da Atividade. */
    public void pubAdicionarKProdutos(ArrayList<KArtefato> parProdutos) {
        produtos.addAll((Collection<? extends KArtefato>) parProdutos);
    }
    
    /** Remove os Conhecimentos Produtos da Atividade. */
    public void pubRemoverKProdutos(ArrayList<KArtefato> parProdutos) {
        produtos.removeAll(parProdutos);
    }
    
    /** Verifica se um KArtefato é produto desta atividade. */
    public boolean isKProduto(KArtefato parKArtefato) {
        return produtos.contains(parKArtefato);
    }
    
    
    /////////////////// ARTEFATOS - INSUMOS ///////////////////
    /** Obtém os insumos do Conhecimento Atividade.    */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = KArtefato.class,fetch=FetchType.LAZY)
    public Set<KArtefato> getInsumos() {
        return insumos;
    }
    
    /** Atribui os insumos do Conhecimento Atividade. */
    public void setInsumos(Set<KArtefato> parKInsumos) {
        insumos = parKInsumos;
    }
    
    /** Adiciona os Conhecimentos Insumos da Atividade. */
    public void pubAdicionarKInsumos(ArrayList<KArtefato> parKInsumos) {
        insumos.addAll((Collection<? extends KArtefato>) parKInsumos);
    }
    
    /** Remove os Conhecimentos Insumos da Atividade. */
    public void pubRemoverKInsumos(ArrayList<KArtefato> parKInsumos) {
        insumos.removeAll((Collection<?>) parKInsumos);
    }
    
    /** Verifica se um KArtefato é insumo desta atividade. */
    public boolean isKInsumo(KArtefato parKArtefato) {
        return insumos.contains(parKArtefato);
    }
	
}
