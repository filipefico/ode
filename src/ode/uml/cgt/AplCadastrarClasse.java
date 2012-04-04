package ode.uml.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;
import ode.uml.cgd.ClasseDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarClasse extends AplCRUD<Classe>{
	
	@Autowired
	private ClasseDAO classeDAO;

	public Collection<Classe> recuperarTodos() {
		return classeDAO.recuperarTodos();
	}

	public DAOBase<Classe> getNucleoDaoBase() {
		return classeDAO ;
	}
	
	public Collection<Classe> obterPorProjeto(Projeto p){
		return classeDAO.obterPorProjeto(p);
	}
	
	public Collection<Classe> obterPorPacote (Pacote pacote){
		return classeDAO.obterPorPacote(pacote);
	}
}
