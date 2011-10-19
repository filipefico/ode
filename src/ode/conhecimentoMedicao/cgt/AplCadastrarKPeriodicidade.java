package ode.conhecimentoMedicao.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KPeriodicidade;
import ode.conhecimentoMedicao.cgd.KPeriodicidadeDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKPeriodicidade extends AplCRUD<KPeriodicidade>{

	@Autowired
	KPeriodicidadeDAO dao;
	
	@Override
	public DAOBase<KPeriodicidade> getNucleoDaoBase() {
		return dao;
	}

}
