package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KDiretriz;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KDiretrizDAO extends NucleoDAOBase<KDiretriz>{

	/** Salva o KDiretriz */
    public void salvar(KDiretriz parKDiretriz);
    
    /** Exclui o KDiretriz */
    public void excluir(KDiretriz parKDiretriz);
    
    /** Obtem todas as KDiretriz */
    public Collection<KDiretriz> recuperarTodos();
    
	
}
