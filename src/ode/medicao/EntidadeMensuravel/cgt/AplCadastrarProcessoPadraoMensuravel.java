package ode.medicao.EntidadeMensuravel.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.EntidadeMensuravel.cdp.ProcessoPadraoMensuravel;
import ode.medicao.EntidadeMensuravel.cgd.ProcessoPadraoMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarProcessoPadraoMensuravel extends AplCRUD<ProcessoPadraoMensuravel>{

	@Autowired
	ProcessoPadraoMensuravelDAO dao;
	
	@Override
	public DAOBase<ProcessoPadraoMensuravel> getNucleoDaoBase() {
		return dao;
	}

}
