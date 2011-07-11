package ode.controleProjeto.cgd;

import ode.controleProjeto.cdp.Projeto;
import nucleo.comuns.persistencia.NucleoDAOBase;

public interface ProjetoDAO extends NucleoDAOBase<Projeto> {

	public Projeto recuperarPorNome(String nome);
	
}
