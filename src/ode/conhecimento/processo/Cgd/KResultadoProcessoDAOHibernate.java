package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KResultadoProcesso;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KResultadoProcessoDAOHibernate extends NucleoDAOBaseHibernate<KResultadoProcesso>{

	@Override
	protected Class<KResultadoProcesso> getClasseDominio() {
		return KResultadoProcesso.class;
	}
	
	public void salvar(KResultadoProcesso parKResultadoProcesso){
        super.salvar(parKResultadoProcesso);
    }
    
    public void excluir(KResultadoProcesso parKResultadoProcesso){
        super.excluir(parKResultadoProcesso);
    }
    
    public Collection<KResultadoProcesso> recuperarTodos(){
        return super.recuperarTodos();
    }
    
  /*  public List obterResultadosProcessos( KProcesso parKProcesso ){
        return getSession().createQuery("from KResultadoProcesso as krproc where krproc.kprocesso.id = '" + parKProcesso.getId() + "'").list();
    }*/
    
	
}
