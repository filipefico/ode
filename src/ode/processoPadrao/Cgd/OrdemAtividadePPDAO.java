package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.OrdemAtividadePP;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface OrdemAtividadePPDAO extends NucleoDAOBase<OrdemAtividadePP>{

	 	public void salvar(OrdemAtividadePP parOrdemAtividade);
	    
	    public void excluir(OrdemAtividadePP parOrdemAtividade);
	    
	    public Collection<OrdemAtividadePP> recuperarTodos();
	    
	    //public List obterPorCombinacaoOrdenado(CombinacaoPP parCombinacao);
	    
	    //Verifica se existe uma ordemAtividade para a atividade na combinacao
	    //public boolean existeOrdemAtividade(CombinacaoPP parCombinacao, AtividadeProcessoPadrao parAtividade);
	
}
