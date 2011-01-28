package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBase;

import ode.conhecimento.processo.Cdp.KSistemaApoio;

	public interface KSistemaApoioDAO extends NucleoDAOBase<KSistemaApoio>{
	
		/** Salva o KDiretriz */
	    public void salvar(KSistemaApoio parKSistemaApoio);
	    
	    /** Exclui o KDiretriz */
	    public void excluir(KSistemaApoio parKSistemaApoio);
	    
	    /** Obtem todas as KDiretriz */
	    public Collection<KSistemaApoio> recuperarTodos();
    
}
