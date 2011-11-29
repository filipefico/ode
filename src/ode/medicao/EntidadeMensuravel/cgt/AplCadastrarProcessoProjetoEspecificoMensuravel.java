package ode.medicao.EntidadeMensuravel.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.medicao.EntidadeMensuravel.cdp.ProcessoProjetoEspecificoMensuravel;
import ode.medicao.EntidadeMensuravel.cgd.ProcessoProjetoEspecificoMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarProcessoProjetoEspecificoMensuravel extends AplCRUD<ProcessoProjetoEspecificoMensuravel>{

	@Autowired
	ProcessoProjetoEspecificoMensuravelDAO dao;
	
	@Override
	public DAOBase<ProcessoProjetoEspecificoMensuravel> getNucleoDaoBase() {
		return dao;
	}

}
