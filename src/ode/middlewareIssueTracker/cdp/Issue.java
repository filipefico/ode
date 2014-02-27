package ode.middlewareIssueTracker.cdp;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import ode._infraestruturaBase.cdp.ObjetoPersistente;


@XmlRootElement
@Entity
public class Issue extends ObjetoPersistente {
	
	private static final long serialVersionUID = 1144168960171946495L;
	
	private long idIssue;

	public long getIdIssue() {
		return idIssue;
	}

	public void setIdIssue(long id) {
		this.idIssue = id;
	}
	
}
