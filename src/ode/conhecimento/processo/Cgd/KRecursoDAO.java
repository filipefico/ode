package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRecurso;

import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KRecursoDAO extends NucleoDAOBase<KRecurso> {

	/** Salva o KDiretriz */
    public void salvar(KRecurso parKRecurso);
    
    /** Exclui o KDiretriz */
    public void excluir(KRecurso parKRecurso);
    
    /** Obtem todas as KDiretriz */
    public Collection<KRecurso> recuperarTodos();
	
	//public KRecurso obterPorId (String parId);
}
