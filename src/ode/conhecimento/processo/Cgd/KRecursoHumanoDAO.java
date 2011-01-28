package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KRecursoHumanoDAO extends NucleoDAOBase<KRecursoHumano>{
	
    public void salvar(KRecursoHumano parKRecursoHumano);
    
    public void excluir(KRecursoHumano parKRecursoHumano);
    
    /** obter lista com todos Recursos Humanos*/
    public Collection<KRecursoHumano> recuperarTodos();
}
