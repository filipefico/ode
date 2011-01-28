package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KTipoInteracao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KTipoInteracaoDAOHibernate extends NucleoDAOBaseHibernate<KTipoInteracao> implements KTipoInteracaoDAO{

	@Override
	protected Class<KTipoInteracao> getClasseDominio() {
		// TODO Auto-generated method stub
		return KTipoInteracao.class;
	}
	
    public void salvar(KTipoInteracao parKTipoInteracao){
        super.salvar(parKTipoInteracao);
    }
    
    public void excluir(KTipoInteracao parKTipoInteracao){
        super.excluir(parKTipoInteracao);
    }
    
    public Collection<KTipoInteracao> recuperarTodos(){
        return super.recuperarTodos();
    }
	
}
