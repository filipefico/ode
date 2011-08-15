package ode.conhecimento.processo.Cgt;


import ode.conhecimento.processo.Cdp.KProcedimento;
import ode.conhecimento.processo.Cgd.KProcedimentoDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProcedimentoImpl extends
		AplBaseImpl<KProcedimento> implements AplCadastrarKProcedimento {

	@Autowired
	private KProcedimentoDAO kProcedimentoDAO;
	
	public KProcedimentoDAO getkProcedimentoDAO() {
		return kProcedimentoDAO;
	}

	public void setkProcedimentoDAO(KProcedimentoDAO kProcedimentoDAO) {
		this.kProcedimentoDAO = kProcedimentoDAO;
	}
	
	@Override
	public DAOBase<KProcedimento> getNucleoDaoBase() {
		return kProcedimentoDAO;
	}

}
