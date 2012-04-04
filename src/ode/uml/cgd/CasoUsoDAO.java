package ode.uml.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.CasoUso;

public interface CasoUsoDAO extends DAOBase<CasoUso> {
	public Collection<CasoUso> obterPorProjeto (Projeto projeto);
	
	public CasoUso obterPorNomeEProjeto(String nome, Projeto p);
}
