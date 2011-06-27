package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgd.KRecursoHumanoDAO;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKRecursoHumanoImpl extends
		NucleoAplCadastroBaseImpl<KRecursoHumano> implements AplCadastrarKRecursoHumano {

	@Autowired
	private KRecursoHumanoDAO kRecursoHumanoDAO;
	
	public KRecursoHumanoDAO getkRecursoHumanoDAO() {
		return kRecursoHumanoDAO;
	}

	public void setkRecursoHumanoDAO(KRecursoHumanoDAO kRecursoHumanoDAO) {
		this.kRecursoHumanoDAO = kRecursoHumanoDAO;
	}

	@Override
	protected void copiarValor(KRecursoHumano objetoFonte, KRecursoHumano objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());


	}

	@Override
	public NucleoDAOBase<KRecursoHumano> getNucleoDaoBase() {
		return kRecursoHumanoDAO;
	}

}
