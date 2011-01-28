package ode.processoPadrao.Cdp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KModeloCicloVida;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cdp.KTipoInteracao;
import ode.processoPadrao.Cdp.CompPP;


@Entity
public class CompPPProcessoSimples extends CompPP{

	private static final long serialVersionUID = -1003139827021069271L;
	
	public static final String nomeClass = "CompPPProcessoSimples";
    /** Processos Padrão Gerais aos quais este pertence */
    private Set<CompPPProcessoComplexo> processosComplexos;
    /** Tipo de Interacao do Processo com o processo de engenharia */
    private KTipoInteracao kTipoInteracao; 
    /** KProcesso referente ao Processo Específico */
    private KProcesso kProcesso; 
    /** Modelos de Ciclo de Vida */
    private Set<ModeloCicloVidaProcessoPadrao> modelosCicloVida; 
    /** Atividades que o compõem */
    private Set<AtividadeProcessoPadrao> atividades; 
    /** Processos que foram especializados a partir deste */
    private Set<CompPPProcessoSimples> processosEspecializados;
    /** Processo a partir do qual este foi especializado */
    private CompPPProcessoSimples processoOrigem;

    private Set<ConectorCompPPMacroAtividade> conectorCompPPMacroAtividade;

    private Set<CompPPMacroatividade> compPPMacroAtividade;
    
    
    
    public CompPPProcessoSimples() {
       this.processosComplexos = new HashSet<CompPPProcessoComplexo>();
       // this.modelosCicloVida = new HashSet();
       // this.atividades = new HashSet();
        this.processosEspecializados = new HashSet<CompPPProcessoSimples>();
       // this.conectorCompPPMacroAtividade = new HashSet();
       // this.compPPMacroAtividade = new HashSet();
    }
    
    
    /** Obtém os Processos gerais deste.
     *
     *@hibernate.set
     *    cascade = "none"
     */
    @ManyToMany(targetEntity = CompPPProcessoComplexo.class, fetch = FetchType.EAGER)
    public Set<CompPPProcessoComplexo> getProcessosComplexos() {
        return processosComplexos;
    }
    
    public void setProcessosComplexos(Set<CompPPProcessoComplexo> parProcessosComplexos) {
        this.processosComplexos = parProcessosComplexos;
    }
    
    public void addProcessosComplexos(CompPPProcessoComplexo parProcessoComplexo) {
        if(this.processosComplexos == null)
            this.processosComplexos = new HashSet<CompPPProcessoComplexo>(); 
        this.processosComplexos.add(parProcessoComplexo);
    }
    

    
    
    /** Obtém o Tipo de Interacao do Processo com o processo de engenharia.
     *
     *   not-null = "false"
    */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL,targetEntity = KTipoInteracao.class)
    public KTipoInteracao getKTipoInteracao() {
        return kTipoInteracao;
    }
    
    public void setKTipoInteracao(KTipoInteracao parKTipoInteracao) {
        this.kTipoInteracao = parKTipoInteracao;
    }
    
    
    /** Obtém o KProcesso refernete a este.
     *   not-null = "true"
    */ 
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL,targetEntity = KProcesso.class)
    @JoinColumn(nullable=false)
    public KProcesso getKProcesso() {
        return kProcesso;
    }
    
    public void setKProcesso(KProcesso parKProcesso) {
        this.kProcesso = parKProcesso;
    }
    
    
    /** Obtém os Modelos de Ciclo de Vida deste.
     *
     *@hibernate.set
     *    inverse = "true"
   */
    @OneToMany(cascade = javax.persistence.CascadeType.ALL,targetEntity = ModeloCicloVidaProcessoPadrao.class,fetch=FetchType.LAZY)
    public Set<ModeloCicloVidaProcessoPadrao> getModelosCicloVida() {
        return modelosCicloVida;
    }
    
    public void setModelosCicloVida(Set<ModeloCicloVidaProcessoPadrao> parModelosCicloVida) {
        this.modelosCicloVida = parModelosCicloVida;
    }
    
    
    /** Obtém as Atividades.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "delete"
     */
    @OneToMany(cascade = javax.persistence.CascadeType.ALL,targetEntity = AtividadeProcessoPadrao.class,fetch=FetchType.LAZY)
    public Set<AtividadeProcessoPadrao> getAtividades() {
        return atividades;
    }
    
    public void setAtividades(Set<AtividadeProcessoPadrao> parAtividades) {
        this.atividades = parAtividades;
    }
    
    /** Obtém os Processos especializados deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "none"
     */
    @OneToMany(cascade=javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoSimples.class,fetch = FetchType.LAZY)
    public Set<CompPPProcessoSimples> getProcessosEspecializados() {
        return processosEspecializados;
    }
    
    public void setProcessosEspecializados(Set<CompPPProcessoSimples> parProcessosEspecializados) {
        this.processosEspecializados = parProcessosEspecializados;
    }
    
    
    /** Obtém o Processo do qual este foi especializado.
     *   not-null = "false"
     */
    @ManyToOne(targetEntity = CompPPProcessoSimples.class)
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
     */
    @OneToMany(targetEntity = ConectorCompPPMacroAtividade.class, fetch = FetchType.LAZY)
    public Set<ConectorCompPPMacroAtividade> getConectorCompPPMacroAtividade() {
        return conectorCompPPMacroAtividade;
    }

    public void setConectorCompPPMacroAtividade(Set<ConectorCompPPMacroAtividade> conectorCompPPMacroAtividade) {
        this.conectorCompPPMacroAtividade = conectorCompPPMacroAtividade;
    }

    /**
     *
     *@hibernate.set
     *   cascade = "none"
     */
    @ManyToMany(targetEntity = CompPPMacroatividade.class,fetch = FetchType.EAGER)
    public Set<CompPPMacroatividade> getCompPPMacroAtividade() {
        return compPPMacroAtividade;
    }

    public void setCompPPMacroAtividade(Set<CompPPMacroatividade> compPPMacroAtividade) {
        this.compPPMacroAtividade = compPPMacroAtividade;
    }

    public void addCompPPMacroAtividade(CompPPMacroatividade compPPMacroAtividade){
        this.compPPMacroAtividade.add(compPPMacroAtividade);
    }

    
    public InterfaceCompPPProcessoSimples getInterfaceCompPP() {
        return (InterfaceCompPPProcessoSimples)(super.getInterfaceCompPP());
    }

}
