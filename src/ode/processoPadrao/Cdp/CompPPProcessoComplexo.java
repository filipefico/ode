package ode.processoPadrao.Cdp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode.processoPadrao.Cdp.CompPP;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CompPPProcessoComplexo extends CompPP {
	public static final String nomeClass = "CompPPProcessoComplexo";
    /** Processos Padrão Específicos contidos neste */
    private Set processosSimples;
    
    /** Processos que foram especializados a partir deste */
    private Set processosEspecializados;
    /** Processo a partir do qual este foi especializado */
    private CompPPProcessoComplexo processoOrigem;
    
    
    public CompPPProcessoComplexo() {
        this.processosSimples = new HashSet();
        this.processosEspecializados = new HashSet();
    }
    
    
    /* Obtém os Processos especificos deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "none"
     *    lazy = "false"
     *    table = "padr_compprocessocomplexo_compprocessosimples"
     *@hibernate.collection-key
     *   column = "idoprocessocomplexo"
     *@hibernate.collection-many-to-many
     *   column = "idoprocessosimples"
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoSimples"
     *
     */
    
    @ManyToMany(cascade=javax.persistence.CascadeType.ALL,targetEntity = CompPPProcessoSimples.class,fetch=FetchType.EAGER)
    public Set getProcessosSimples() {
        return processosSimples;
    }
    
    public void setProcessosSimples(Set parProcessosSimples) {
        this.processosSimples = parProcessosSimples;
    }



    public void addProcessosSimples(CompPPProcessoSimples parProcessosSimples) {
        this.processosSimples.add(parProcessosSimples);
    }
    public void addProcessosSimples(Set parProcessosSimples) {
        this.processosSimples.addAll(parProcessosSimples);
    }
    
    
    
    /* Obtém os Processos especializados deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "none"
     *    lazy = "true"
     *@hibernate.collection-key
     *   column = "idoprocessoorigem"
     *@hibernate.collection-one-to-many
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoComplexo"
     *
     */
    @OneToMany(cascade=javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoComplexo.class,fetch = FetchType.EAGER)
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
     *   class = "Ode.processoPadrao.cdp.CompPPProcessoComplexo"
     *
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL,targetEntity = CompPPProcessoComplexo.class, fetch = FetchType.EAGER)
    public CompPPProcessoComplexo getProcessoOrigem() {
        return processoOrigem;
    }
    
    public void setProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem) {
        this.processoOrigem = parProcessoOrigem;
    }
    
    
//    public CompPPProcessoSimples obterProcessoEngenharia(){
//        
//        List locProcessosEspecificos = new ArrayList(this.processosSimples);
//        
//        for(int i=0; i<locProcessosEspecificos.size(); i++){
//            CompPPProcessoSimples locProc = (CompPPProcessoSimples)locProcessosEspecificos.get(i);
//            if(locProc.getKProcesso().isEhEngenharia())
//                return locProc;
//        }
//        
//        return null;
//    }

    //public InterfaceCompPPProcessoComplexo getInterfaceCompPP(){
      //  return (InterfaceCompPPProcessoComplexo)(super.getInterfaceCompPP());
    //}
	
//    public List obterKSubProcessos(){
//         List locProcessosEspecificos = new ArrayList(this.processosSimples);
//         List processos = new ArrayList();
//
//        for(int i=0; i<locProcessosEspecificos.size(); i++){
//            CompPPProcessoSimples locProc = (CompPPProcessoSimples)locProcessosEspecificos.get(i);
//            processos.add(locProc.getKProcesso());
//        }
//
//        return processos;
//    }
}
