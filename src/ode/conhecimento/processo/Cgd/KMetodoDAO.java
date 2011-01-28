package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KMetodo;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KMetodoDAO extends NucleoDAOBase<KMetodo> {
	
	 /** Salva o KMetodo */
    public void salvar (KMetodo parKMetodo);
    
    /** Exclui o KMetodo */
    public void excluir(KMetodo parKMetodo);

    /** Obtem todos os KMetodo */
    public Collection<KMetodo> recuperarTodos();

}
