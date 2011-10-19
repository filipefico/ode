package ode.conhecimentoMedicao.cgt;

import ode.conhecimentoMedicao.cdp.KMetodoAnalitico;
import ode.conhecimentoMedicao.cgd.KMetodoAnaliticoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplCadastrarKMetodoAnalitico extends AplCRUD<KMetodoAnalitico>{

	@Autowired
	KMetodoAnaliticoDAO dao;
	
	@Override
	public DAOBase<KMetodoAnalitico> getNucleoDaoBase() {
		return dao;
	}

}
