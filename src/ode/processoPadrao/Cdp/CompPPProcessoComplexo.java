package ode.processoPadrao.Cdp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.processoPadrao.Cdp.CompPP;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CompPPProcessoComplexo extends CompPP {

	private static final long serialVersionUID = 3645553289873117858L;
	
	public static final String nomeClass = "CompPPProcessoComplexo";
	
    /** Processos Padrão Específicos contidos neste */
    private Set<CompPPProcessoSimples> processosSimples;
    
    /** Processos que foram especializados a partir deste */
    private Set<CompPPProcessoComplexo> processosEspecializados;
    
    /** Processo a partir do qual este foi especializado */
    private CompPPProcessoComplexo processoOrigem;
    
    
    public CompPPProcessoComplexo() {
        this.processosSimples = new HashSet<CompPPProcessoSimples>();
        this.processosEspecializados = new HashSet<CompPPProcessoComplexo>();
    }
    
    
    /** Obtém os Processos especificos deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "none"
     */
    
    @ManyToMany(targetEntity = CompPPProcessoSimples.class,fetch=FetchType.EAGER)
    public Set<CompPPProcessoSimples> getProcessosSimples() {
        return processosSimples;
    }
    
    public void setProcessosSimples(Set<CompPPProcessoSimples> parProcessosSimples) {
        this.processosSimples = parProcessosSimples;
    }



    public void addProcessosSimples(CompPPProcessoSimples parProcessosSimples) {
        this.processosSimples.add(parProcessosSimples);
    }
    public void addProcessosSimples(Set<CompPPProcessoSimples> parProcessosSimples) {
        this.processosSimples.addAll(parProcessosSimples);
    }
    
    
    
    /** Obtém os Processos especializados deste.
     *
     *@hibernate.set
     *    inverse = "true"
     *    cascade = "none"
     *    */
    @OneToMany(targetEntity = CompPPProcessoComplexo.class,fetch = FetchType.LAZY)
    public Set<CompPPProcessoComplexo> getProcessosEspecializados() {
        return processosEspecializados;
    }
    
    public void setProcessosEspecializados(Set<CompPPProcessoComplexo> parProcessosEspecializados) {
        this.processosEspecializados = parProcessosEspecializados;
    }
    
    
    /** Obtém o Processo do qual este foi especializado.
     *
     *   not-null = "false"
     **/
    @ManyToOne(targetEntity = CompPPProcessoComplexo.class)
    public CompPPProcessoComplexo getProcessoOrigem() {
        return processoOrigem;
    }
    
    public void setProcessoOrigem(CompPPProcessoComplexo parProcessoOrigem) {
        this.processoOrigem = parProcessoOrigem;
    }
    
    
    public CompPPProcessoSimples obterProcessoEngenharia(){
        
        List<CompPPProcessoSimples> locProcessosEspecificos = new ArrayList<CompPPProcessoSimples>(this.processosSimples);
        
        for(int i=0; i<locProcessosEspecificos.size(); i++){
            CompPPProcessoSimples locProc = (CompPPProcessoSimples)locProcessosEspecificos.get(i);
            if(locProc.getKProcesso().isEhEngenharia())
                return locProc;
        }
        
        return null;
    }

    public InterfaceCompPPProcessoComplexo getInterfaceCompPP(){
        return (InterfaceCompPPProcessoComplexo)(super.getInterfaceCompPP());
    }
	
    public ArrayList<KProcesso> obterKSubProcessos(){
         ArrayList<CompPPProcessoSimples> locProcessosEspecificos = new ArrayList<CompPPProcessoSimples>(this.processosSimples);
         ArrayList<KProcesso> processos = new ArrayList<KProcesso>();

        for(int i=0; i<locProcessosEspecificos.size(); i++){
            CompPPProcessoSimples locProc = (CompPPProcessoSimples)locProcessosEspecificos.get(i);
            processos.add(locProc.getKProcesso());
        }

        return processos;
    }
}
