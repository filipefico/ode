package ode.entidadeProblema.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.entidadeProblema.cdp.ProjetoProblema;
import ode.entidadeProblema.cgd.ProjetoProblemaDAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AplCadastrarProjetoProblema  extends AplCRUD<ProjetoProblema>{

	@Autowired
	ProjetoProblemaDAO dao;
	
	@Override
	public DAOBase<ProjetoProblema> getNucleoDaoBase() {
		return dao;
	}
	
	public Collection<ProjetoProblema> obterPorProjeto (Projeto projeto){
		return dao.obterPorProjeto(projeto);
	}

}
