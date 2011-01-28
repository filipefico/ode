package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ObrigatoriedadeMacroatividade;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface ObrigatoriedadeMacroatividadeDAO extends NucleoDAOBase<ObrigatoriedadeMacroatividade>{

	  	public void salvar(ObrigatoriedadeMacroatividade parAtividadeProcessoPadrao);

	    public void excluir(ObrigatoriedadeMacroatividade parAtividadeProcessoPadrao);

	    public Collection<ObrigatoriedadeMacroatividade> recuperarTodos();
}
