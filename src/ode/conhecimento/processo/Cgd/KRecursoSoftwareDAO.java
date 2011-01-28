package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRecursoSoftware;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KRecursoSoftwareDAO extends NucleoDAOBase<KRecursoSoftware>{
	
	 public void salvar(KRecursoSoftware parKRecursoSoftware);
	    
	 public void excluir(KRecursoSoftware parKRecursoSoftware);
	 
	 public Collection<KRecursoSoftware> recuperarTodos();
}
