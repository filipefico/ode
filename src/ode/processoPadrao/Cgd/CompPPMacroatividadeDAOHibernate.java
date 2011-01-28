package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.CompPPMacroatividade;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class CompPPMacroatividadeDAOHibernate extends NucleoDAOBaseHibernate<CompPPMacroatividade> implements CompPPMacroatividadeDAO{

	@Override
	protected Class<CompPPMacroatividade> getClasseDominio() {
		// TODO Auto-generated method stub
		return CompPPMacroatividade.class;
	}

	  public void salvar(CompPPMacroatividade compPPMacroatividade) {
	        super.salvar(compPPMacroatividade);
	    }

	    public void excluir(CompPPMacroatividade compPPMacroatividade) {
	        super.excluir(compPPMacroatividade);
	    }
	    
    public Collection<CompPPMacroatividade> recuperarTodos(){
        return super.recuperarTodos();
    }
}
