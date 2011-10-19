package ode.conhecimentoMedicao.cgt;

import ode.conhecimento.processo.cdp.KProcedimento;
import ode.conhecimentoMedicao.cdp.KProcedimentoMedicao;
import ode.conhecimentoMedicao.cgd.KProcedimentoMedicaoDAO;
import ode.conhecimentoMedicao.cgd.KProcedimentoMedicaoDAOImpl;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplCadastrarKProcedimentoMedicao extends AplCRUD<KProcedimentoMedicao>{

	@Autowired
	KProcedimentoMedicaoDAO dao;
	
	@Override
	public DAOBase<KProcedimentoMedicao> getNucleoDaoBase() {
		return dao;
	}

}
