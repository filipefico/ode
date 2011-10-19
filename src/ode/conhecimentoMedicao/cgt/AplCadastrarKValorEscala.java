package ode.conhecimentoMedicao.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode.conhecimentoMedicao.cgd.KValorEscalaDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKValorEscala extends AplCRUD<KValorEscala>{

	@Autowired
	KValorEscalaDAO dao;
	
	@Override
	public DAOBase<KValorEscala> getNucleoDaoBase() {
		return dao;
	}

}
