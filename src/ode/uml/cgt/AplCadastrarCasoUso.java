package ode.uml.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.CasoUso;
import ode.uml.cgd.CasoUsoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarCasoUso extends AplCRUD<CasoUso>{
	
	@Autowired
	private CasoUsoDAO casoUsoDAO;

	public Collection<CasoUso> recuperarTodos() {
		return casoUsoDAO.recuperarTodos();
	}

	public DAOBase<CasoUso> getNucleoDaoBase() {
		return casoUsoDAO ;
	}
	
	public Collection<CasoUso> obterPorProjeto (Projeto projeto){
		return casoUsoDAO.obterPorProjeto (projeto);
	}
}