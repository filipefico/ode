package ode.conhecimento.processo.Cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgd.KRecursoHumanoDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKRecursoHumanoImpl extends
		AplBaseImpl<KRecursoHumano> implements AplCadastrarKRecursoHumano {

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
