package ode._controleRecursoHumano.srv;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode.conhecimento.processo.cdp.KRecursoHumano;


@Path("/")//caminho onde o serviço REST fica disponibilizado, seguido pelo @Path de cada parametro  
@Produces({"application/xml"})//tipos de retorno que o nosso REST pode produzir  
@WebService//definimos aqui que essa interface é um WebService WSDL  

public interface SrvAplCadastrarRecursoHumano {
	
    @GET//tipo do metodo REST  
    @WebMethod(operationName="recuperarTodos")//nome do metodo no WSDL  
    public Collection<RecursoHumano> recuperarTodos();  
      
    @GET  
    @Path("{id}")//aqui passamos uma ID para obter um Cliente especifico  
    @WebMethod(operationName="recuperarPorId")  
    //o ID do cliente é mapeado pelo PathParam para o REST e recebe o nome dado pelo WebParam para o WSDL.  
    public RecursoHumano recuperarPorId(@PathParam("id") @WebParam(name="id")long id);

    @DELETE
    @Path("/excluir")
    @WebMethod(operationName="excluir")
	void excluir(RecursoHumano rh);

    @GET
    @Path("/cargo/{kRH}")
    @WebMethod(operationName="recuperarPorCargo")
	Collection<RecursoHumano> recuperarPorCargo(@PathParam("kRH") @WebParam(name="kRH")KRecursoHumano kRH);

    
    @GET
    @Path("/quantidade")
    @WebMethod(operationName="recuperarQuantidadeTotal")
	int recuperarQuantidadeTotal();

    @GET
    @Path("/ativos")
    @WebMethod(operationName="recuperarRecursosHumanosAtivos")
	Collection<RecursoHumano> recuperarRecursosHumanosAtivos();

    @POST
    @WebMethod(operationName="salvar")
	void salvar(RecursoHumano rh);
	
	

}
