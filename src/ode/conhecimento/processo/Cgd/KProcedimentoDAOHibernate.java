package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;
import ode.conhecimento.processo.Cdp.KProcedimento;


public class KProcedimentoDAOHibernate extends NucleoDAOBaseHibernate<KProcedimento> implements KProcedimentoDAO{

		@Override
		protected Class<KProcedimento> getClasseDominio() {
			// TODO Auto-generated method stub
			return KProcedimento.class;
		}
	
	 	public void salvar(KProcedimento parKProcedimento){
	        super.salvar(parKProcedimento);
	    }
	    
	    public void excluir(KProcedimento parKProcedimento){
	        super.excluir(parKProcedimento);
	    }
	 
	    public Collection<KProcedimento> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	
}
