package ode._controleProcesso.cgd;

import java.util.List;

import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._infraestruturaBase.cgd.DAOBase;

public interface ProcessoProjetoEspecificoDAO extends DAOBase<ProcessoProjetoEspecifico> {

	List<ProcessoProjetoEspecifico> recuperarPorProjeto(Long idProjeto);

}
