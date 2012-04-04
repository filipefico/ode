package ode.gerenciaRequisitos.cgd;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cgd.DAOBase;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

public interface RequisitoDAO extends DAOBase<Requisito>{

	public Collection<Requisito> obterPorProjeto (Projeto p);
	
	public Collection<Requisito> obterPorCasoUso (CasoUso casoUso);
	
	public Collection<Requisito> obterPorClasse (Classe classe);
	
	public Collection<Requisito> obterPorPacote (Pacote pacote);
	
	public Collection<Requisito> obterPorInteressado (RecursoHumano recursoHumano);
	
	public Collection<Requisito> obterPorResponsavel (RecursoHumano recursoHumano);
	
	public Requisito obterPorIdentificadorEProjeto (String identificador, Projeto projeto);
	
}