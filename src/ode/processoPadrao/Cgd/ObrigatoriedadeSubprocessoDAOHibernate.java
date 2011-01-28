package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ObrigatoriedadeSubprocesso;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class ObrigatoriedadeSubprocessoDAOHibernate extends NucleoDAOBaseHibernate<ObrigatoriedadeSubprocesso> implements ObrigatoriedadeSubprocessoDAO{

	@Override
	protected Class<ObrigatoriedadeSubprocesso> getClasseDominio() {
		// TODO Auto-generated method stub
		return ObrigatoriedadeSubprocesso.class;
	}

	 public void salvar(ObrigatoriedadeSubprocesso parObrigatoriedadeSubprocesso){
	        super.salvar(parObrigatoriedadeSubprocesso);
	    }

	    public void excluir(ObrigatoriedadeSubprocesso parObrigatoriedadeSubprocesso){
	        super.excluir(parObrigatoriedadeSubprocesso);
	    }
	    
    public Collection<ObrigatoriedadeSubprocesso> recuperarTodos(){
        return super.recuperarTodos();
    }
    
}
