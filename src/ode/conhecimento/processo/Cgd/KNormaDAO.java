package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KNorma;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KNormaDAO extends NucleoDAOBase<KNorma>{

    /** Salva o KNorma*/
    public void salvar(KNorma parKNorma);
    
    /** Exclui o KNorma */
    public void excluir(KNorma parKNorma);
    
    /** Obtem todas as KNorma */
    public Collection<KNorma> recuperarTodos();
}
