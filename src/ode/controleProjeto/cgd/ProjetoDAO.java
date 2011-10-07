package ode.controleProjeto.cgd;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleProjeto.cdp.Projeto;

public interface ProjetoDAO extends DAOBase<Projeto> {

	public Projeto recuperarPorNome(String nome);
	
}
