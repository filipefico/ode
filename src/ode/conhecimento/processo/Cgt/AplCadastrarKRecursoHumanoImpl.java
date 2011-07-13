package ode.conhecimento.processo.Cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nucleo.comuns.excecao.NucleoExcecao;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgd.KRecursoHumanoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

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
	public NucleoDAOBase<KRecursoHumano> getNucleoDaoBase() {
		return kRecursoHumanoDAO;
	}

}
