package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import nucleo.comuns.persistencia.NucleoDAOBase;

import ode.conhecimento.processo.Cdp.KRecursoHardware;

public interface KRecursoHardwareDAO extends NucleoDAOBase<KRecursoHardware>{

    public void salvar(KRecursoHardware parKRecursoHardware);
    
    public void excluir(KRecursoHardware parKRecursoHardware);
    
    /** obter lista com todos Recursos de Hardware*/
    public Collection<KRecursoHardware> recuperarTodos();
}
