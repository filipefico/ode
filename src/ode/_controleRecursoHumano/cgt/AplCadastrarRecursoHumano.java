package ode._controleRecursoHumano.cgt;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarRecursoHumano extends
		AplCRUD<RecursoHumano> {

	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;
	
	@Override
	public DAOBase<RecursoHumano> getNucleoDaoBase() {
		return recursoHumanoDAO;
	}
	
	@Override
	public Collection<RecursoHumano> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodosComOrdenacao("nome");
	}

}
