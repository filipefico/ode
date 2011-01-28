package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ObrigatoriedadeSubprocesso;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface ObrigatoriedadeSubprocessoDAO extends NucleoDAOBase<ObrigatoriedadeSubprocesso>{

		public void salvar(ObrigatoriedadeSubprocesso parAtividadeProcessoPadrao);

	    public void excluir(ObrigatoriedadeSubprocesso parAtividadeProcessoPadrao);
	    
	    public Collection<ObrigatoriedadeSubprocesso> recuperarTodos();

}
