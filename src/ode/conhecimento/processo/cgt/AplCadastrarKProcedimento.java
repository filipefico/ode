package ode.conhecimento.processo.cgt;


import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KProcedimento;
import ode.conhecimento.processo.cgd.KProcedimentoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarKProcedimento")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKProcedimento extends
		AplCRUD<KProcedimento> {

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
