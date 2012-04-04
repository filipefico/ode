package ode.conhecimento.processo.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.processo.cdp.KProcesso;

public interface KProcessoDAO extends DAOBase<KProcesso> {

	Collection recuperarTodosNaoEngenharia();

	Collection recuperarTodosEngenharia();

}
