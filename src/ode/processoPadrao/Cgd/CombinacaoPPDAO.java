package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.CombinacaoPP;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface CombinacaoPPDAO extends NucleoDAOBase<CombinacaoPP>{

		public void salvar(CombinacaoPP parCombinacao);
	    
	    public void excluir(CombinacaoPP parCombinacao);
	    
	    public Collection<CombinacaoPP> recuperarTodos();
	    
	   // public List obterPorMCVOrdenado(ModeloCicloVidaProcessoPadrao parMCV);
	    
	    //Dada uma lista de Atividades e um MCV, retorna as combinações que contêm tais atividades neste MCV
	   // public List obterPorMCVeAtividades(ModeloCicloVidaProcessoPadrao parMCV, List parAtividades);
	    
}
