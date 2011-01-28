package ode.processoPadrao.Cdp;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import ode.conhecimento.processo.Cdp.KProcesso;

@Entity
public class InterfaceCompPPProcessoComplexo extends InterfaceCompPP {
		
		private static final long serialVersionUID = 1511633233374334028L;
		private Set<ObrigatoriedadeSubprocesso> obrigatoriedadeSubprocesso;

	    public InterfaceCompPPProcessoComplexo(String nome, String objetivo, String descricao, CompPP compPP,
	            Set<ObrigatoriedadeSubprocesso> obrigatoriedadeSubprocesso) {
	        super(nome, objetivo, descricao, compPP);
	        this.obrigatoriedadeSubprocesso = obrigatoriedadeSubprocesso;

	    }

	    public InterfaceCompPPProcessoComplexo() {
	        obrigatoriedadeSubprocesso = new HashSet<ObrigatoriedadeSubprocesso>();
	    }

	    /** Obtém as SubAtividades.
	     *
	     *@hibernate.set
	     *    inverse = "true"
	     */
	    @OneToMany(cascade = javax.persistence.CascadeType.ALL, targetEntity = ObrigatoriedadeSubprocesso.class,fetch=FetchType.LAZY)
	    public Set<ObrigatoriedadeSubprocesso> getObrigatoriedadeSubprocesso() {
	        return obrigatoriedadeSubprocesso;
	    }

	    public void setObrigatoriedadeSubprocesso(Set<ObrigatoriedadeSubprocesso> obrigatoriedadeSubprocesso) {
	        this.obrigatoriedadeSubprocesso = obrigatoriedadeSubprocesso;
	    }

	    public void addObrigatoriedadeSubprocesso(ObrigatoriedadeSubprocesso parObrigatoriedadeSubprocesso) {
	        this.obrigatoriedadeSubprocesso.add(parObrigatoriedadeSubprocesso);
	    }

	   public ArrayList<KProcesso> obterKSubProcessos(Boolean ehObrigatorio){
	        ArrayList<KProcesso> processos = new ArrayList<KProcesso>();
	        if(this.obrigatoriedadeSubprocesso != null){
	            ArrayList<ObrigatoriedadeSubprocesso> locProcessosEspecificos = new ArrayList<ObrigatoriedadeSubprocesso>(this.obrigatoriedadeSubprocesso);

	            for(ObrigatoriedadeSubprocesso locObrigatoriedade: locProcessosEspecificos){
	                if(ehObrigatorio == locObrigatoriedade.isEhObrigatorio())
	                    processos.add(locObrigatoriedade.getkProcesso());
	            }
	        }
	        return processos;
	    }

	    public ArrayList<CompPPProcessoSimples> obterSubComponentes(Boolean ehObrigatorio){
	        ArrayList<KProcesso> kprocOpcionais = this.obterKSubProcessos(ehObrigatorio);
	        ArrayList<CompPPProcessoSimples> subProcOpcionais = new ArrayList<CompPPProcessoSimples>();

	        for(CompPPProcessoSimples subProc:(Set<CompPPProcessoSimples>)((CompPPProcessoComplexo)compPP).getProcessosSimples()){
	            if(kprocOpcionais.contains(subProc.getKProcesso()))
	                subProcOpcionais.add(subProc);
	        }
	        return subProcOpcionais;
	    }


}
