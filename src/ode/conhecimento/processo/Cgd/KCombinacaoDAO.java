package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KCombinacao;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KCombinacaoDAO extends NucleoDAOBase<KCombinacao>{

	 	public void salvar(KCombinacao parKCombicacao);
	    
	    public void excluir(KCombinacao parKCombicacao);
	    
	    public Collection<KCombinacao> recuperarTodos();
}
