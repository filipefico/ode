package ode.conhecimento.principal.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.principal.Cdp.Conhecimento;
import ode.conhecimento.processo.Cdp.KAtividade;

public class ConhecimentoDAOHibernate extends NucleoDAOBaseHibernate<Conhecimento> implements ConhecimentoDAO{

		@Override
		protected Class<Conhecimento> getClasseDominio() {
			// TODO Auto-generated method stub
			return Conhecimento.class;
		}
		
		 public void salvar(Conhecimento parConhecimento){
		        super.salvar(parConhecimento);
	    }
	    
	    public void excluir(Conhecimento parConhecimento){
	        super.excluir(parConhecimento);
	    }

	    public Collection<Conhecimento> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	    
	    public KAtividade recuperarPorId(Long parId){
	        return (KAtividade) super.recuperarPorId(parId);
	    }
	    
}
