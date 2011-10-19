package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgd.KElementoMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKElementoMensuravel extends AplCRUD<KElementoMensuravel>{

	@Autowired
	KElementoMensuravelDAO dao;
	
	@Override
	public DAOBase<KElementoMensuravel> getNucleoDaoBase() {
		return dao;
	}


}
