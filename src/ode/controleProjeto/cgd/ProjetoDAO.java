package ode.controleProjeto.cgd;

import ode.controleProjeto.cdp.Projeto;
import ode.nucleo.crud.cgd.DAOBase;

public interface ProjetoDAO extends DAOBase<Projeto> {

	public Projeto recuperarPorNome(String nome);
	
}
