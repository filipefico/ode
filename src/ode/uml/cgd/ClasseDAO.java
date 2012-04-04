package ode.uml.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

public interface ClasseDAO extends DAOBase<Classe> {
	public Collection<Classe> obterPorProjeto(Projeto p);
	
	public Collection<Classe> obterPorPacote (Pacote pacote);
	
	public Classe recuperarPorNomeEProjeto (String nome, Projeto p);
}
