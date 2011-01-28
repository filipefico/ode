package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBase;

import ode.conhecimento.processo.Cdp.KTecnica;

public interface KTecnicaDAO extends NucleoDAOBase<KTecnica> {

	/** Salva a KTecnica */
    public void salvar (KTecnica parKTecnica);
    
    /** Exclui a KTecnica */
    public void excluir(KTecnica parKTecnica);

    /** Obtem todos as KTecnica */
    public Collection<KTecnica> recuperarTodos();
}
