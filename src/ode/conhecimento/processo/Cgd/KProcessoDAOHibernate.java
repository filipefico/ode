package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KProcesso;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KProcessoDAOHibernate extends NucleoDAOBaseHibernate<KProcesso> implements KProcessoDAO{
	
		@Override
		protected Class<KProcesso> getClasseDominio() {
			// TODO Auto-generated method stub
			return KProcesso.class;
		}
	
		public void salvar(KProcesso parKProcesso){
	        super.salvar(parKProcesso);
	    }
	    
	    public void excluir(KProcesso parKProcesso) {
	        super.excluir(parKProcesso);
	    }
	    
	    public Collection<KProcesso> recuperarTodos(){
	        return super.recuperarTodos();
	    }
	    
	   /* public List obterProcessosEngenharia(){
	        return getSession().createQuery("from KProcesso as kproc where kproc.ehEngenharia = true").list();
	    }
	    
	    public List obterProcessosNaoEngenharia(){
	        return getSession().createQuery("from KProcesso as kproc where kproc.ehEngenharia = false").list();
	    }*/
}
