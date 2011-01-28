package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KNorma;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KNormaDAOHibernate extends NucleoDAOBaseHibernate<KNorma> implements KNormaDAO{

	@Override
	protected Class<KNorma> getClasseDominio() {
		// TODO Auto-generated method stub
		return KNorma.class;
	}
	
    public void salvar(KNorma parKNorma){
        super.salvar(parKNorma);
    }
    
    public void excluir(KNorma parKNorma){
        super.excluir(parKNorma);
    }
    
    public Collection<KNorma> recuperarTodos(){
        return super.recuperarTodos();
    }

}
