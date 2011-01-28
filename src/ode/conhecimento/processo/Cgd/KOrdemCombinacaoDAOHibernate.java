package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KOrdemCombinacao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KOrdemCombinacaoDAOHibernate extends NucleoDAOBaseHibernate<KOrdemCombinacao> implements KOrdemCombinacaoDAO{

	 
	@Override
	protected Class<KOrdemCombinacao> getClasseDominio() {
		// TODO Auto-generated method stub
		return KOrdemCombinacao.class;
	}
	
    public void salvar(KOrdemCombinacao parKOrdemCombinacao){
        super.salvar(parKOrdemCombinacao);
    }
    
    public void excluir(KOrdemCombinacao parKOrdemCombinacao){
        super.excluir(parKOrdemCombinacao);
    }
    
    public Collection<KOrdemCombinacao> recuperarTodos(){
        return super.recuperarTodos();
    }
    
}
