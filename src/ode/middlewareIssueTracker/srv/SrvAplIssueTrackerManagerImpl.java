package ode.middlewareIssueTracker.srv;


import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import ode.middlewareIssueTracker.cdp.Issue;
import ode.middlewareIssueTracker.cgt.AplIssueTrackerManager;


@WebService(endpointInterface = "ode.middlewareIssueTracker.srv.SrvAplIssueTrackerManager")
public class SrvAplIssueTrackerManagerImpl implements SrvAplIssueTrackerManager {

	@Autowired
	AplIssueTrackerManager aplIssueTrackerManager;
	
	@Override
	public int inserirIssue(Issue issue) {
		// TODO Auto-generated method stub		
				
		aplIssueTrackerManager.inserirIssue(issue);
		
		return 0;
	}

	@Override
	public int atualizarIssue(Issue issue) {
		// TODO Auto-generated method stub
		
		System.out.println(issue);		
		
		/*for(HistoryIssue historyIssue : issue.getHistoryIssues()){
			System.out.println("Historico");
			System.out.println("Field Name: " + historyIssue.getFieldName());
			System.out.println("Old Value: " + historyIssue.getOldValue());
			System.out.println("New Value: " + historyIssue.getNewValue());
		}*/
		
		return 0;
	}

}
