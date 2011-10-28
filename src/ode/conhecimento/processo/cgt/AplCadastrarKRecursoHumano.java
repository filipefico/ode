package ode.conhecimento.processo.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimento.processo.cgd.KRecursoHumanoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarKRecursoHumano")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKRecursoHumano extends
		AplCRUD<KRecursoHumano> {

	@Autowired
	private KRecursoHumanoDAO kRecursoHumanoDAO;
	
	public KRecursoHumanoDAO getkRecursoHumanoDAO() {
		return kRecursoHumanoDAO;
	}

	public void setkRecursoHumanoDAO(KRecursoHumanoDAO kRecursoHumanoDAO) {
		this.kRecursoHumanoDAO = kRecursoHumanoDAO;
	}

	@Override
	public DAOBase<KRecursoHumano> getNucleoDaoBase() {
		return kRecursoHumanoDAO;
	}
	
	@Override
	public Collection<KRecursoHumano> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodosComOrdenacao("nome");
	}

}
