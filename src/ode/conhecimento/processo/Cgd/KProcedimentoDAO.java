package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KProcedimento;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface KProcedimentoDAO extends NucleoDAOBase<KProcedimento>{
	
	 /** Salva o KProcedimento */
    public void salvar (KProcedimento parKProcedimento);
    
    /** Exclui o KProcedimento */
    public void excluir(KProcedimento parKProcedimento);

    /** Obtem todos os KProcedimentos */
    public Collection<KProcedimento> recuperarTodos();
}
