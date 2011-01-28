package ode.processoPadrao.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KDiretriz;
import ode.processoPadrao.Cdp.ModeloCicloVidaProcessoPadrao;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface ModeloCicloVidaProcessoPadraoDAO extends NucleoDAOBase<ModeloCicloVidaProcessoPadrao>{

	public void salvar(ModeloCicloVidaProcessoPadrao parModeloCicloVidaProcessoPadrao);
    
    public void excluir(ModeloCicloVidaProcessoPadrao parModeloCicloVidaProcessoPadrao);
    
    public Collection<ModeloCicloVidaProcessoPadrao> recuperarTodos();
    
}
