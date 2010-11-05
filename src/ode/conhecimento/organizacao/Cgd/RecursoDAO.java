package ode.controle.controleProcesso.Cgd;

import nucleo.comuns.persistencia.NucleoDAOBase;
import java.util.List;
import ode.controle.controleProcesso.Cdp.Recurso;

public interface RecursoDAO <R extends Recurso> extends NucleoDAOBase<R> {
    
    /** Obter Recursos ativos*/
    public List <R> obterRecursosAtivos();
}
