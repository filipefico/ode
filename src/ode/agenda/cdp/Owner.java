package ode.agenda.cdp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class Owner extends ObjetoPersistente {
	
	private static final long serialVersionUID = 1L;
	
	private String accessToken;
	private String refreshToken;
	private RecursoHumano recursoHumano;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	@OneToOne(optional=false)
	public RecursoHumano getrecursoHumano() {
		return recursoHumano;
	}
	public void setrecursoHumano(RecursoHumano recursoHumano) {
		this.recursoHumano = recursoHumano;
	}	
	

}
