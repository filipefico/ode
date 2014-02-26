package ode._controleRecursoHumano.srv;

import java.util.Collection;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ode._controleRecursoHumano.cdp.Equipe;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.controleProjeto.cdp.Projeto;



@Path("/")//caminho onde o serviço REST fica disponibilizado, seguido pelo @Path de cada parametro  
@Produces({"application/xml"})//tipos de retorno que o nosso REST pode produzir  
@WebService//definimos aqui que essa interface é um WebService WSDL

public interface SrvAplDefinirEquipe {

	
	@GET
	@WebMethod(operationName="recuperarTodos")	
	public Collection<Equipe> recuperarTodos();
	
	
	@POST
	@WebMethod(operationName="definirEquipe")
	public void definirEquipe(Set<RecursoHumano> recursosSelecionados, Projeto projeto);
	
	
	@POST
	@Path("/atualizar")
	@WebMethod(operationName="atualizar")
	public void atualizar(Equipe objeto);
	
	
	@GET
	@Path("/porProjeto/{id}")
	@WebMethod(operationName="obterEquipePorProjeto")
	public Equipe obterEquipePorProjeto(@PathParam("id") @WebParam(name="id")long id);
	
}
