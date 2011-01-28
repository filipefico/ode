package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KCombinacao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KCombinacaoDAOHibernate extends NucleoDAOBaseHibernate<KCombinacao> implements KCombinacaoDAO{

		@Override
		protected Class<KCombinacao> getClasseDominio() {
			// TODO Auto-generated method stub
			return KCombinacao.class;
		}
	
	   public void salvar(KCombinacao parKCombinacao){
	        super.salvar(parKCombinacao);
	    }
	    
	    public void excluir(KCombinacao parKCombinacao){
	        super.excluir(parKCombinacao);
	    }
	    
	    public Collection<KCombinacao> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	
}
