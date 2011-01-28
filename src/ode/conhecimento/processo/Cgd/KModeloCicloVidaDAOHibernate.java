package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KModeloCicloVida;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KModeloCicloVidaDAOHibernate extends NucleoDAOBaseHibernate<KModeloCicloVida> implements KModeloCicloVidaDAO{

	@Override
	protected Class<KModeloCicloVida> getClasseDominio() {
		// TODO Auto-generated method stub
		return KModeloCicloVida.class;
	}
	
    public void salvar(KModeloCicloVida parKMCV){
        super.salvar(parKMCV);
    }
    
    public void excluir(KModeloCicloVida parKMCV){
        super.excluir(parKMCV);
    }
    
    public Collection<KModeloCicloVida> recuperarTodos(){
        return super.recuperarTodos();
    }
   
}
