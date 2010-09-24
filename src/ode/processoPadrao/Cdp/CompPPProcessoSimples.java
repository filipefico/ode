package ode.processoPadrao.Cdp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ode.processoPadrao.Cdp.CompPP;


@Entity
public class CompPPProcessoSimples extends CompPP{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1003139827021069271L;
	public static final String nomeClass = "CompPPProcessoSimples";
    /** Processos Padrão Gerais aos quais este pertence */
    private Set processosComplexos;
    /** Tipo de Interacao do Processo com o processo de engenharia */
   // private KTipoInteracao kTipoInteracao;
    /** KProcesso referente ao Processo Específico */
    //private KProcesso kProcesso;
    /** Modelos de Ciclo de Vida */
    //private Set modelosCicloVida;
    /** Atividades que o compõem */
    //private Set<Katividade> atividades;
    /** Processos que foram especializados a partir deste */
   private Set processosEspecializados;
    /** Processo a partir do qual este foi especializado */
    private CompPPProcessoSimples processoOrigem;

    //private Set<ConectorCompPPMacroAtividade> conectorCompPPMacroAtividade;

    //private Set<CompPPMacroatividade> compPPMacroAtividade;
    
    
    
    public CompPPProcessoSimples() {
       this.processosComplexos = new HashSet();
       // this.modelosCicloVida = new HashSet();
       // this.atividades = new HashSet();
        this.processosEspecializados = new HashSet();
       // this.conectorCompPPMacroAtividade = new HashSet();
       // this.compPPMacroAtividade = new HashSet();
    }
    
    
    /* Obtém os Processos gerais deste.
     *
     *@hibernate.set
     *    cascade = "none"
     *    lazy = "false"
     *    table = "padr_compprocessocomplexo_compprocessosimples"
     *@hibernate.collection-key
     *   column = "idoprocessosimples"
     *@hibernate.collection-many-to-many
     *   column = "idoprocessocomplexo"
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoComplexo"
     *
     */
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL,targetEntity = CompPPProcessoComplexo.class, fetch = FetchType.EAGER)
    public Set getProcessosComplexos() {
        return processosComplexos;
    }
    
    public void setProcessosComplexos(Set parProcessosComplexos) {
        this.processosComplexos = parProcessosComplexos;
    }
    
    public void addProcessosComplexos(CompPPProcessoComplexo parProcessoComplexo) {
        if(this.processosComplexos == null)
            this.processosComplexos = new HashSet();
        this.processosComplexos.add(parProcessoComplexo);
    }
    

    
    
    /* Obtém o Tipo de Interacao do Processo com o processo de engenharia.
     *
     * @hibernate.many-to-one
     *   column = "idoktipointeracao"
     *   not-null = "false"
     *   class = "Ode.Conhecimento.Processo.Cdp.KTipoInteracao"
     *
     */
//    public KTipoInteracao getKTipoInteracao() {
//        return kTipoInteracao;
//    }
//    
//    public void setKTipoInteracao(KTipoInteracao parKTipoInteracao) {
//        this.kTipoInteracao = parKTipoInteracao;
//    }
//    
    
    /** Obtém o KProcesso refernete a este.
     *
     * @hibernate.many-to-one
     *   column = "idokprocesso"
     *   not-null = "true"
     *   class = "Ode.Conhecimento.Processo.Cdp.KProcesso"
     *
     */
//    public KProcesso getKProcesso() {
//        return kProcesso;
//    }
//    
//    public void setKProcesso(KProcesso parKProcesso) {
//        this.kProcesso = parKProcesso;
//    }
    
    
    /** Obtém os Modelos de Ciclo de Vida deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "all"
     *    lazy = "true"
     *@hibernate.collection-key
     *   column = "idoprocessoespecificomcv"
     *@hibernate.collection-one-to-many
     *   class = "Ode.processoPadrao.cdp.ModeloCicloVidaProcessoPadrao"
     *
     */
//    public Set getModelosCicloVida() {
//        return modelosCicloVida;
//    }
//    
//    public void setModelosCicloVida(Set parModelosCicloVida) {
//        this.modelosCicloVida = parModelosCicloVida;
//    }
//    
    
    /** Obtém as Atividades.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "delete"
     *    lazy = "true"
     *@hibernate.collection-key
     *   column = "idoprocessoespecificoatividade"
     *@hibernate.collection-one-to-many
     *   class = "Ode.processoPadrao.cdp.AtividadeProcessoPadrao"
     *
     */
//    public Set getAtividades() {
//        return atividades;
//    }
//    
//    public void setAtividades(Set parAtividades) {
//        this.atividades = parAtividades;
//    }
//    
    /* Obtém os Processos especializados deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "none"
     *    lazy = "true"
     *@hibernate.collection-key
     *   column = "idoprocessoorigem"
     *@hibernate.collection-one-to-many
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoSimples"
     *
     */
    @OneToMany(cascade=javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoSimples.class,fetch = FetchType.EAGER)
    public Set getProcessosEspecializados() {
        return processosEspecializados;
    }
    
    public void setProcessosEspecializados(Set parProcessosEspecializados) {
        this.processosEspecializados = parProcessosEspecializados;
    }
    
    
    /* Obtém o Processo do qual este foi especializado.
     *
     * @hibernate.many-to-one
     *   column = "idoprocessoorigem"
     *   not-null = "false"
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoSimples"
     *
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoSimples.class, fetch = FetchType.EAGER)
    public CompPPProcessoSimples getProcessoOrigem() {
        return processoOrigem;
    }
    
    public void setProcessoOrigem(CompPPProcessoSimples parProcessoOrigem) {
        this.processoOrigem = parProcessoOrigem;
    }

    /** Obtém os Processos especializados deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "none"
     *    lazy = "true"
     *@hibernate.collection-key
     *   column = "idocompppprocsimples"
     *@hibernate.collection-one-to-many
     *   class = "Ode.processoPadrao.cdp.ConectorCompPPMacroAtividade"
     *
     */
//    public Set<ConectorCompPPMacroAtividade> getConectorCompPPMacroAtividade() {
//        return conectorCompPPMacroAtividade;
//    }
//
//    public void setConectorCompPPMacroAtividade(Set<ConectorCompPPMacroAtividade> conectorCompPPMacroAtividade) {
//        this.conectorCompPPMacroAtividade = conectorCompPPMacroAtividade;
//    }

    /**
     *
     *@hibernate.set
     *   lazy = "false"
     *   table = "padr_compppmacroatividade_compppprocessosimples"
     *   cascade = "none"
     *@hibernate.collection-key
     *   column = "idoprocessosimples"
     *@hibernate.collection-many-to-many
     *   column ="idomacroatividade"
     *   class = "Ode.processoPadrao.cdp.CompPPMacroatividade"
     *
     */
//    public Set<CompPPMacroatividade> getCompPPMacroAtividade() {
//        return compPPMacroAtividade;
//    }
//
//    public void setCompPPMacroAtividade(Set<CompPPMacroatividade> compPPMacroAtividade) {
//        this.compPPMacroAtividade = compPPMacroAtividade;
//    }
//
//    public void addCompPPMacroAtividade(CompPPMacroatividade compPPMacroAtividade){
//        this.compPPMacroAtividade.add(compPPMacroAtividade);
//    }

    
    //public InterfaceCompPPProcessoSimples getInterfaceCompPP() {
        //return (InterfaceCompPPProcessoSimples)(super.getInterfaceCompPP());
   // }

}
