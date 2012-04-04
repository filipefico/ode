package ode.uml.cgt;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.Pacote;
import ode.uml.cgd.PacoteDAO;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarPacote extends AplCRUD<Pacote>{
	
	@Autowired
	private PacoteDAO pacoteDAO;

	public Collection<Pacote> recuperarTodos() {
		return pacoteDAO.recuperarTodos();
	}
	
	public List<Pacote> obterPacotesPorProjeto (Projeto p){
		return pacoteDAO.obterPacotesPorProjeto(p);
	}

	public DAOBase<Pacote> getNucleoDaoBase() {
		return pacoteDAO ;
	}
}
