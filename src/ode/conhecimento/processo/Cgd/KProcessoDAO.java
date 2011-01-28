package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KProcesso;

import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KProcessoDAO extends NucleoDAOBase<KProcesso> {
	
	 	public void salvar(KProcesso parKProcesso);
	    
	    public void excluir(KProcesso parKProcesso);
	    
	    public Collection<KProcesso> recuperarTodos();	
	    
	    //public List obterProcessosEngenharia();
	    
	   // public List obterProcessosNaoEngenharia();
}
