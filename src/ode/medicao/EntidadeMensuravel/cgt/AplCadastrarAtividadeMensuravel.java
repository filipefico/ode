package ode.medicao.EntidadeMensuravel.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.EntidadeMensuravel.cdp.AtividadeMensuravel;
import ode.medicao.EntidadeMensuravel.cgd.AtividadeMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarAtividadeMensuravel extends AplCRUD<AtividadeMensuravel>{

	@Autowired
	AtividadeMensuravelDAO dao;
	
	@Override
	public DAOBase<AtividadeMensuravel> getNucleoDaoBase() {
		return dao;
	}

}
