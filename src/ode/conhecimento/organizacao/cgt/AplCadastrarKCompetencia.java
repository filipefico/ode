package ode.conhecimento.organizacao.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.organizacao.cdp.KCompetencia;
import ode.conhecimento.organizacao.cgd.KCompetenciaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarKCompetencia")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCompetencia extends AplCRUD<KCompetencia> {

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
