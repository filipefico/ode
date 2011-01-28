package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KRoteiro;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KRoteiroDAO extends NucleoDAOBase<KRoteiro>{

	 /** Salva o KRoteiro */
    public void salvar (KRoteiro parKRoteiro);
    
    /** Exclui o KRoteiro */
    public void excluir(KRoteiro parKRoteiro);

    /** Obtem todos os KRoteiros */
    public Collection<KRoteiro> recuperarTodos();
    
}
