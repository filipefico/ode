package ode.entidadeProblema.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;

import ode.controleProjeto.cdp.Projeto;
import ode.entidadeProblema.cdp.ProjetoProblema;


public interface ProjetoProblemaDAO extends DAOBase<ProjetoProblema>{
	Collection<ProjetoProblema> obterPorProjeto (Projeto projeto);	
}

