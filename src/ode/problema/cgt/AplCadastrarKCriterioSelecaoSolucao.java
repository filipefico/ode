package ode.problema.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.problema.cdp.KCriterioSelecaoSolucao;
import ode.problema.cgd.KCriterioSelecaoSolucaoDAO;
import ode.resolucaoProblema.cdp.ResultadoImplementacaoSolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional (rollbackFor = NucleoExcecao.class)
public class AplCadastrarKCriterioSelecaoSolucao extends AplCRUD<KCriterioSelecaoSolucao>  {
@Autowired
KCriterioSelecaoSolucaoDAO kcriterioselecaosolucaoDAO;

	//protected void antesExcluir(T objeto) throws NucleoRegraNegocioExcecao {
	//lembrar que antes de excluir um criterio é necessário verificar que critérios que tenham sido utilizados  para selecionar soluções  não podem ser excluídos.
	//	}
	
	

	@Override
	public DAOBase<KCriterioSelecaoSolucao> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return kcriterioselecaosolucaoDAO;
	}

	
	@Override
	public Collection<KCriterioSelecaoSolucao> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodos();
	}
	
	
	public void salvarpeso(KCriterioSelecaoSolucao kcriterioselecaosolucao) {
		kcriterioselecaosolucaoDAO.salvar(kcriterioselecaosolucao);
	}
	
	
}
