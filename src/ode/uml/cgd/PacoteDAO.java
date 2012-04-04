package ode.uml.cgd;

import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.Pacote;

public interface PacoteDAO extends DAOBase<Pacote> {
	
	public List<Pacote> obterPacotesPorProjeto (Projeto p);
	
	public Pacote recuperarPorNomeEProjeto(String nome, Projeto projeto);
	
}
