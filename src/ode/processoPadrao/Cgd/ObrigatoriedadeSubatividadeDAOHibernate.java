package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ObrigatoriedadeSubatividade;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class ObrigatoriedadeSubatividadeDAOHibernate extends NucleoDAOBaseHibernate<ObrigatoriedadeSubatividade> implements ObrigatoriedadeSubatividadeDAO {

	@Override
	protected Class<ObrigatoriedadeSubatividade> getClasseDominio() {
		// TODO Auto-generated method stub
		return ObrigatoriedadeSubatividade.class;
	}

	public void salvar(ObrigatoriedadeSubatividade parObrigatoriedadeSubatividade){
        super.salvar(parObrigatoriedadeSubatividade);
    }

    public void excluir(ObrigatoriedadeSubatividade parObrigatoriedadeSubatividade){
        super.excluir(parObrigatoriedadeSubatividade);
    }
	
    public Collection<ObrigatoriedadeSubatividade> recuperarTodos(){
        return super.recuperarTodos();
    }
}
