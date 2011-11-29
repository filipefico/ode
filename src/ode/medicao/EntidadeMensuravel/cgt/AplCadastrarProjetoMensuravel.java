package ode.medicao.EntidadeMensuravel.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.EntidadeMensuravel.cdp.ProjetoMensuravel;
import ode.medicao.EntidadeMensuravel.cgd.ProjetoMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarProjetoMensuravel extends AplCRUD<ProjetoMensuravel>{

	@Autowired
	ProjetoMensuravelDAO dao;
	
	@Override
	public DAOBase<ProjetoMensuravel> getNucleoDaoBase() {
		return dao;
	}

}
