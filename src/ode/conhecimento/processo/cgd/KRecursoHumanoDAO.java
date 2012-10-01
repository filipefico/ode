package ode.conhecimento.processo.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.processo.cdp.KRecursoHumano;

public interface KRecursoHumanoDAO extends DAOBase<KRecursoHumano>{
	
	public KRecursoHumano recuperarPorParteNome(String nome);

}
