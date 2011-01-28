package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ObrigatoriedadeSubatividade;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface ObrigatoriedadeSubatividadeDAO extends NucleoDAOBase<ObrigatoriedadeSubatividade>{

	 	public void salvar(ObrigatoriedadeSubatividade parAtividadeProcessoPadrao);

	    public void excluir(ObrigatoriedadeSubatividade parAtividadeProcessoPadrao);
	    
	    public Collection<ObrigatoriedadeSubatividade> recuperarTodos();
}
