package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.InterfaceCompPPMacroatividade;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface InterfaceCompPPMacroatividadeDAO extends NucleoDAOBase<InterfaceCompPPMacroatividade> {

	 public void salvar(InterfaceCompPPMacroatividade parInterfaceCompPPMacroatividade);

	    public void excluir(InterfaceCompPPMacroatividade parInterfaceCompPPMacroatividade);

	    public Collection<InterfaceCompPPMacroatividade> recuperarTodos();

	   // public InterfaceCompPPMacroatividade obterPorId(InterfaceCompPP parInterfaceCompPP);
}
