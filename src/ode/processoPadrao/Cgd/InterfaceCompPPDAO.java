package ode.processoPadrao.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.InterfaceCompPP;
import java.util.*;

public interface InterfaceCompPPDAO extends NucleoDAOBase<InterfaceCompPP> {

	public void salvar(InterfaceCompPP parProcessoPadrao);

    public void excluir(InterfaceCompPP parProcessoPadrao);

    //public Collection<InterfaceCompPP> recuperarTodos();
}
