package ode.middlewareIssueTracker.srv;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;

import ode.controleProjeto.cdp.Projeto;
import ode.middlewareIssueTracker.cdp.Issue;
import ode.middlewareIssueTracker.cgt.AplIssueTrackerManager;


@WebService(endpointInterface = "ode.middlewareIssueTracker.srv.SrvAplIssueTrackerManager")
public class SrvAplIssueTrackerManagerImpl implements SrvAplIssueTrackerManager {

	@Autowired
	AplIssueTrackerManager aplIssueTrackerManager;
	
	@Override
	public int teste(Issue issue) {
		// TODO Auto-generated method stub
		
		//System.out.println("Id da Issue: " + issue.getIdIssue());
		aplIssueTrackerManager.getIssue(issue);
		
		return 0;
	}

}
