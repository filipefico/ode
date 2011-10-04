package ode.conhecimento.organizacao.Cgt;

import java.util.Collection;

import ode.conhecimento.organizacao.Cdp.KCompetencia;
import ode.conhecimento.organizacao.Cgd.KCompetenciaDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCompetenciaImpl extends AplBaseImpl<KCompetencia> implements AplCadastrarKCompetencia {

	@Autowired
	private KCompetenciaDAO kCompetenciaDAO;

	@Override
	public DAOBase<KCompetencia> getNucleoDaoBase() {
		return kCompetenciaDAO;
	}

	@Override
	public Collection<KCompetencia> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodosComOrdenacao("nome");
	}

}
