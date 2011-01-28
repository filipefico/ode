package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.processoPadrao.Cdp.ObrigatoriedadeMacroatividade;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class ObrigatoriedadeMacroatividadeDAOHibernate extends NucleoDAOBaseHibernate<ObrigatoriedadeMacroatividade> implements ObrigatoriedadeMacroatividadeDAO{

	@Override
	protected Class<ObrigatoriedadeMacroatividade> getClasseDominio() {
		// TODO Auto-generated method stub
		return ObrigatoriedadeMacroatividade.class;
	}

	public void salvar(ObrigatoriedadeMacroatividade parObrigatoriedadeMacroatividade){
        super.salvar(parObrigatoriedadeMacroatividade);
    }

    public void excluir(ObrigatoriedadeMacroatividade parObrigatoriedadeMacroatividade){
        super.excluir(parObrigatoriedadeMacroatividade);
    }
	
    public Collection<ObrigatoriedadeMacroatividade> recuperarTodos(){
        return super.recuperarTodos();
    }
}
