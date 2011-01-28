package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ConectorCompPPMacroAtividade;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class ConectorCompPPMacroAtividadeDAOHibernate extends NucleoDAOBaseHibernate<ConectorCompPPMacroAtividade> implements ConectorCompPPMacroAtividadeDAO{

	@Override
	protected Class<ConectorCompPPMacroAtividade> getClasseDominio() {
		// TODO Auto-generated method stub
		return ConectorCompPPMacroAtividade.class;
	}

	public void salvar(ConectorCompPPMacroAtividade parConectorCompPPMacroAtividade){
        super.salvar(parConectorCompPPMacroAtividade);
    }
    
    public void excluir(ConectorCompPPMacroAtividade parConectorCompPPMacroAtividade){
        super.excluir(parConectorCompPPMacroAtividade);
    }    
	
    public Collection<ConectorCompPPMacroAtividade> recuperarTodos(){
        return super.recuperarTodos();
    }
}
