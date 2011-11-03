package ode._controleProcesso.cgd;

import java.util.List;

import ode._controleProcesso.cdp.Atividade;
import ode._infraestruturaBase.cgd.DAOBase;

public interface AtividadeDAO extends DAOBase<Atividade>{

	public List<Atividade> recuperarAtividadesPorProcessoProjetoEspecifico(Long idProcesso);
}
