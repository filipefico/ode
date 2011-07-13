package ode.controleProjeto.cgd;

import ode.controleProjeto.cdp.Projeto;
import ode.nucleo.cgd.NucleoDAOBase;

public interface ProjetoDAO extends NucleoDAOBase<Projeto> {

	public Projeto recuperarPorNome(String nome);
	
}
