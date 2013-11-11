package ode.processoProjeto.cgt;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cgd.CompPPDAO;
import ode.processoProjeto.cdp.CompPPr;
import ode.processoProjeto.cgd.CompPPrDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplDefinirProcessoProjeto")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplDefinirProcessoProjeto {
	
	@Autowired
	private CompPPrDAO compPPrDAO;
	
	@Autowired
	private CompPPDAO compPPDAO;
	
	public void salvarCompPPr(CompPPr compPPr){
		compPPrDAO.salvar(compPPr);
	}

	public Collection<CompPP> recuperarTodosCompPPComOrdenacao(String orderBy) {
		return compPPDAO.recuperarTodosComOrdenacao(orderBy);
	}
	
	public Collection<CompPPr> recuperarTodosCompPPrComOrdenacao(String orderBy) {
		return compPPrDAO.recuperarTodosComOrdenacao(orderBy);
	}
	
	public Collection<CompPPr> recuperarTodosCompPPr() {
		return compPPrDAO.recuperarTodos();
	}
}

