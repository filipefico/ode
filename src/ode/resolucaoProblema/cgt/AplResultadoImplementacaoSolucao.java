package ode.resolucaoProblema.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.resolucaoProblema.cdp.ResultadoImplementacaoSolucao;
import ode.resolucaoProblema.cgd.ResultadoImplementacaoSolucaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import ode._infraestruturaBase.excecao.NucleoExcecao;



@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplResultadoImplementacaoSolucao {
	
	
	@Autowired
	private ResultadoImplementacaoSolucaoDAO resultadoImplementacaoSolucaoDAO;
	
	
	public DAOBase<ResultadoImplementacaoSolucao> getNucleoDaoBase() {
		return resultadoImplementacaoSolucaoDAO;
	}

	public Collection<ResultadoImplementacaoSolucao> recuperarTodos() {
		return getNucleoDaoBase().recuperarTodos();
	}
	
	public void salvarResultadoImplementacaoSolucao(ResultadoImplementacaoSolucao resultadoImplementacaoSolucao) {
		resultadoImplementacaoSolucaoDAO.salvar(resultadoImplementacaoSolucao);
	}
	
	
}
