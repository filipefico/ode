package ode.middlewareIssueTracker.srv;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ode.controleProjeto.cdp.Projeto;
import ode.middlewareIssueTracker.cdp.Issue;


@Path("/")
@Produces(MediaType.APPLICATION_XML)
@WebService
public interface SrvAplIssueTrackerManager {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@WebMethod(operationName="teste")
	public int teste(Issue issue);
	
}
