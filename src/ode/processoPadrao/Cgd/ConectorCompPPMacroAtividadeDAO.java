package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ConectorCompPPMacroAtividade;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface ConectorCompPPMacroAtividadeDAO extends NucleoDAOBase<ConectorCompPPMacroAtividade> {

	public void salvar(ConectorCompPPMacroAtividade parConectorCompPPMacroAtividade);

    public void excluir(ConectorCompPPMacroAtividade parConectorCompPPMacroAtividade);
    
    public Collection<ConectorCompPPMacroAtividade> recuperarTodos();
}
