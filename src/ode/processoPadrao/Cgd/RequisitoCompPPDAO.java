package ode.processoPadrao.Cgd;

import ode.processoPadrao.Cdp.RequisitoCompPP;
import nucleo.comuns.persistencia.NucleoDAOBase;
import java.util.*;

public interface RequisitoCompPPDAO extends NucleoDAOBase<RequisitoCompPP> {

	public void salvar(RequisitoCompPP requisitoCompPP);

    public void excluir(RequisitoCompPP requisitoCompPP);

    //public List obterTodos();

	
}
