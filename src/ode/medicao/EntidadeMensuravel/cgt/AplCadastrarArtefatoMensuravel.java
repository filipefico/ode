package ode.medicao.EntidadeMensuravel.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.EntidadeMensuravel.cdp.ArtefatoMensuravel;
import ode.medicao.EntidadeMensuravel.cgd.ArtefatoMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarArtefatoMensuravel extends AplCRUD<ArtefatoMensuravel>{

	@Autowired
	ArtefatoMensuravelDAO dao;
	
	@Override
	public DAOBase<ArtefatoMensuravel> getNucleoDaoBase() {
		return dao;
	}

}
