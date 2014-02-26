package ode.controleProjeto.srv;

import java.io.InputStream;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ode.controleProjeto.cdp.Projeto;


@Path("/")//caminho onde o serviço REST fica disponibilizado, seguido pelo @Path de cada parametro  
@Produces(MediaType.APPLICATION_XML)//tipos de retorno que o nosso REST pode produzir  
@WebService//definimos aqui que essa interface é um WebService WSDL 

public interface SrvAplCadastrarProjeto {  
  
    @GET//tipo do metodo REST    
    @WebMethod(operationName="recuperarTodos")//nome do metodo no WSDL  
    public Collection<Projeto> recuperarTodos();  
      
    @GET  
    @Path("{id}")//aqui passamos uma ID para obter um Projeto especifico  
    @WebMethod(operationName="recuperarPorId")  
    //o ID do cliente é mapeado pelo PathParam para o REST e recebe o nome dado pelo WebParam para o WSDL.  
    public Projeto recuperarPorId(@PathParam("id") @WebParam(name="id")long id);

    @GET
    @Path("/quantidade")
    @WebMethod(operationName="recuperarQuantidadeTotal")
	public int recuperarQuantidadeTotal();

    @DELETE
    @Path("/excluir")
    @WebMethod(operationName="excluir")
	public void excluir(Projeto p);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @WebMethod(operationName="salvar")
	public int salvar(Projeto p);  
}