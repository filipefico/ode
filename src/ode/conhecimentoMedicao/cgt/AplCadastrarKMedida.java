package ode.conhecimentoMedicao.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgd.KMedidaDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKMedida extends AplCRUD<KMedida>{

	@Autowired
	KMedidaDAO dao;
	
	@Override
	public DAOBase<KMedida> getNucleoDaoBase() {
		return dao;
	}

}
