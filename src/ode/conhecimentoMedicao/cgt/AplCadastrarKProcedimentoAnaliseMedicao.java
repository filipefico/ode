package ode.conhecimentoMedicao.cgt;

import ode.conhecimentoMedicao.cdp.KProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cgd.KProcedimentoAnaliseMedicaoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplCadastrarKProcedimentoAnaliseMedicao extends AplCRUD<KProcedimentoAnaliseMedicao>{

	@Autowired
	KProcedimentoAnaliseMedicaoDAO dao;
	
	@Override
	public DAOBase<KProcedimentoAnaliseMedicao> getNucleoDaoBase() {
		return dao;
	}

}
