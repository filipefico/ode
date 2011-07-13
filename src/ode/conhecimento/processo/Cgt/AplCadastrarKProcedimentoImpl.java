package ode.conhecimento.processo.Cgt;


import nucleo.comuns.excecao.NucleoExcecao;
import ode.conhecimento.processo.Cdp.KProcedimento;
import ode.conhecimento.processo.Cgd.KProcedimentoDAO;
import ode.nucleo.cgd.NucleoDAOBase;
import ode.nucleo.cgt.NucleoAplCadastroBaseImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProcedimentoImpl extends
		NucleoAplCadastroBaseImpl<KProcedimento> implements AplCadastrarKProcedimento {

	@Autowired
	private KProcedimentoDAO kProcedimentoDAO;
	
	public KProcedimentoDAO getkProcedimentoDAO() {
		return kProcedimentoDAO;
	}

	public void setkProcedimentoDAO(KProcedimentoDAO kProcedimentoDAO) {
		this.kProcedimentoDAO = kProcedimentoDAO;
	}
	
	@Override
	public NucleoDAOBase<KProcedimento> getNucleoDaoBase() {
		return kProcedimentoDAO;
	}

}
