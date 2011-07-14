package ode.controleProcesso.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode.controleProcesso.cdp.RecursoHumano;
import ode.controleProcesso.cgd.RecursoHumanoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadadastrarRecursoHumanoImpl extends
		NucleoAplCadastroBaseImpl<RecursoHumano> implements
		AplCadastrarRecursoHumano {

	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;
	
	@Override
	public NucleoDAOBase<RecursoHumano> getNucleoDaoBase() {
		return recursoHumanoDAO;
	}

}
