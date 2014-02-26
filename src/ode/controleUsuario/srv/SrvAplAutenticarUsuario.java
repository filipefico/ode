package ode.controleUsuario.srv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;



/**
 * Testando o gerador de doc
 * @author thiag_000
 *
 */
@Path("/")
@Produces("application/json")
@WebService
public interface SrvAplAutenticarUsuario {
	
	@GET
	@Path("/{user}/{senha}/{remember}")
	@WebMethod(operationName="efetuarLogin")
	public Long efetuarLogin(@PathParam("user") @WebParam(name="user")String nomeUsuario, @PathParam("senha") @WebParam(name="senha")String senha, @PathParam("remember") @WebParam(name="remember")boolean rememberme) throws NucleoRegraNegocioExcecao;
	

}
