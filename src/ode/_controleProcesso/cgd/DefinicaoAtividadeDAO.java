package ode._controleProcesso.cgd;

import ode._controleProcesso.cdp.DefinicaoAtividade;
import ode._infraestruturaBase.cgd.DAOBase;

public interface DefinicaoAtividadeDAO extends DAOBase<DefinicaoAtividade> {

	DefinicaoAtividade recuperarPorAtividade(Long idAtividade);

}
