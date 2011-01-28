package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.AtividadeProcessoPadrao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class AtividadeProcessoPadraoDAOHibernate extends NucleoDAOBaseHibernate<AtividadeProcessoPadrao> implements AtividadeProcessoPadraoDAO {

		@Override
		protected Class<AtividadeProcessoPadrao> getClasseDominio() {
			// TODO Auto-generated method stub
			return AtividadeProcessoPadrao.class;
		}
	
	  public void salvar(AtividadeProcessoPadrao parAtividadeProcessoPadrao){
	        super.salvar(parAtividadeProcessoPadrao);
	    }
	    
	    public void excluir(AtividadeProcessoPadrao parAtividadeProcessoPadrao){
	        super.excluir(parAtividadeProcessoPadrao);
	    }
	    
	    public Collection<AtividadeProcessoPadrao> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	    
}
