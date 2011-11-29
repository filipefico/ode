package ode.medicao.EntidadeMensuravel.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.EntidadeMensuravel.cdp.RecursoHumanoMensuravel;
import ode.medicao.EntidadeMensuravel.cgd.RecursoHumanoMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarRecursoHumanoMensuravel extends AplCRUD<RecursoHumanoMensuravel>{

	@Autowired
	RecursoHumanoMensuravelDAO dao;
	
	@Override
	public DAOBase<RecursoHumanoMensuravel> getNucleoDaoBase() {
		return dao;
	}

}
