package ode.conhecimentoMedicao.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KUnidadeMedida;
import ode.conhecimentoMedicao.cgd.KUnidadeMedidaDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKUnidadeMedida extends AplCRUD<KUnidadeMedida>{

	@Autowired
	KUnidadeMedidaDAO dao;
	
	@Override
	public DAOBase<KUnidadeMedida> getNucleoDaoBase() {
		return dao;
	}

}
