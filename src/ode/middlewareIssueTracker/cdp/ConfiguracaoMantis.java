package ode.middlewareIssueTracker.cdp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;



@Entity
public class ConfiguracaoMantis extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private String url;
	private UsuarioMantis usuarioMantisPadrao;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@OneToOne
	public UsuarioMantis getUsuarioMantisPadrao() {
		return usuarioMantisPadrao;
	}
	public void setUsuarioMantisPadrao(UsuarioMantis usuarioMantisPadrao) {
		this.usuarioMantisPadrao = usuarioMantisPadrao;
	}
	
	//static final String MANTIS_URL = "http://localhost/mantisbt/api/soap/mantisconnect.php";
	//static final String MANTIS_USER = "administrator";
	//static final String MANTIS_PWD = "123456";
	//static final String PROJETO = "ODE";	
}


