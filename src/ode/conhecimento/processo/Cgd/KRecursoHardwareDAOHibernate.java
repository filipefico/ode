package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRecursoHardware;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;


public class KRecursoHardwareDAOHibernate extends NucleoDAOBaseHibernate<KRecursoHardware> implements KRecursoHardwareDAO{

	@Override
	protected Class<KRecursoHardware> getClasseDominio() {
		// TODO Auto-generated method stub
		return KRecursoHardware.class;
	}

	public void salvar(KRecursoHardware parKRecursoHardware){
        super.salvar(parKRecursoHardware);
    }
    
    public void excluir(KRecursoHardware parKRecursoHardware){
        super.excluir(parKRecursoHardware);
    }
    
    /** obter lista com todos Recursos de Hardware*/
    public Collection<KRecursoHardware> recuperarTodos(){
        return super.recuperarTodos();
    }
	
}
