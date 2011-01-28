package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KTipoInteracao;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KTipoInteracaoDAO extends NucleoDAOBase<KTipoInteracao> {

	 	public void salvar (KTipoInteracao parKTI);
	    
	    public void excluir (KTipoInteracao parKTI);
	    
	    public Collection<KTipoInteracao> recuperarTodos();	
}
