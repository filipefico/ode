package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBase;


import ode.conhecimento.processo.Cdp.KOrdemCombinacao;

public interface KOrdemCombinacaoDAO extends NucleoDAOBase<KOrdemCombinacao>{

	public void salvar(KOrdemCombinacao parKOrdemCombinacao);
    
    public void excluir(KOrdemCombinacao parKOrdemCombinacao);
    
    //public List obterPorKMCVOrdenado(KModeloCicloVida parKMVC);
    
    /** Obtem todas as KAtividade */
    public Collection<KOrdemCombinacao> recuperarTodos();
}
