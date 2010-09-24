package ode.processoPadrao.Cdp;
import java.util.*;

import javax.persistence.Entity;

@Entity
public class InterfaceCompPPProcessoComplexo extends InterfaceCompPP {
		
/*		private Set<ObrigatoriedadeSubprocesso> obrigatoriedadeSubprocesso;

	    public InterfaceCompPPProcessoComplexo(String nome, String objetivo, String descricao, CompPP compPP, Caracterizacao caracterizacao,
	            Set<ObrigatoriedadeSubprocesso> obrigatoriedadeSubprocesso) {
	        super(nome, objetivo, descricao, compPP, caracterizacao);
	        this.obrigatoriedadeSubprocesso = obrigatoriedadeSubprocesso;

	    }

	    public InterfaceCompPPProcessoComplexo() {
	        obrigatoriedadeSubprocesso = new HashSet<ObrigatoriedadeSubprocesso>();
	    }
*/
	    /** Obtém as SubAtividades.
	     *
	     *@hibernate.set
	     *    inverse = "true"
	     *    cascade = "all"
	     *    lazy = "true"
	     *@hibernate.collection-key
	     *   column = "idointerface"
	     *@hibernate.collection-one-to-many
	     *   class = "Ode.processoPadrao.cdp.ObrigatoriedadeSubprocesso"
	     *
	     */
	/*    public Set<ObrigatoriedadeSubprocesso> getObrigatoriedadeSubprocesso() {
	        return obrigatoriedadeSubprocesso;
	    }

	    public void setObrigatoriedadeSubprocesso(Set<ObrigatoriedadeSubprocesso> obrigatoriedadeSubprocesso) {
	        this.obrigatoriedadeSubprocesso = obrigatoriedadeSubprocesso;
	    }

	    public void addObrigatoriedadeSubprocesso(ObrigatoriedadeSubprocesso parObrigatoriedadeSubprocesso) {
	        this.obrigatoriedadeSubprocesso.add(parObrigatoriedadeSubprocesso);
	    }

	    public List<KProcesso> obterKSubProcessos(Boolean ehObrigatorio){
	        List processos = new ArrayList();
	        if(this.obrigatoriedadeSubprocesso != null){
	            List<ObrigatoriedadeSubprocesso> locProcessosEspecificos = new ArrayList(this.obrigatoriedadeSubprocesso);

	            for(ObrigatoriedadeSubprocesso locObrigatoriedade: locProcessosEspecificos){
	                if(ehObrigatorio == locObrigatoriedade.isEhObrigatorio())
	                    processos.add(locObrigatoriedade.getkProcesso());
	            }
	        }
	        return processos;
	    }

	    public List<CompPPProcessoSimples> obterSubComponentes(Boolean ehObrigatorio){
	        List<KProcesso> kprocOpcionais = this.obterKSubProcessos(ehObrigatorio);
	        List<CompPPProcessoSimples> subProcOpcionais = new ArrayList();

	        for(CompPPProcessoSimples subProc:(Set<CompPPProcessoSimples>)((CompPPProcessoComplexo)compPP).getProcessosSimples()){
	            if(kprocOpcionais.contains(subProc.getKProcesso()))
	                subProcOpcionais.add(subProc);
	        }
	        return subProcOpcionais;
	    }
*/

}
