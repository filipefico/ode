package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBase;

import ode.conhecimento.processo.Cdp.KResultadoProcesso;

public interface KResultadoProcessoDAO extends NucleoDAOBase<KResultadoProcesso>{
	
		public void salvar(KResultadoProcesso parKResultadoProcesso);
	    
	    public void excluir(KResultadoProcesso parKResultadoProcesso);
	    
	    public Collection<KResultadoProcesso> recuperarTodos();	
	    
	    //public List obterResultadosProcessos( KProcesso parKProcesso );
}
