package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KAtividade;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KAtividadeDAOHibernate extends NucleoDAOBaseHibernate<KAtividade> implements KAtividadeDAO{
	
		@Override
		protected Class<KAtividade> getClasseDominio() {
			// TODO Auto-generated method stub
			return KAtividade.class;
		}
	
		public void salvar(KAtividade parKAtividade){
	        super.salvar(parKAtividade);
	    }
	    
	    public void excluir(KAtividade parKAtividade){
	        super.excluir(parKAtividade);
	    }
	    
	    public Collection<KAtividade> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	    
	    public KAtividade recuperarPorId(Long parId){
	        return (KAtividade) super.recuperarPorId(parId);
	    }
	   
	   
}
