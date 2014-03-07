package ode.middlewareIssueTracker.cdp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value =  "singleton")
public class Configuracoes {

	static final String MANTIS_URL = "http://localhost/mantisbt/api/soap/mantisconnect.php";
	static final String MANTIS_USER = "administrator";
	static final String MANTIS_PWD = "123456";
	static final String PROJETO = "ODE";
	
	
	public static String getMantisUrl() {
		return MANTIS_URL;
	}
	public static String getMantisUser() {
		return MANTIS_USER;
	}
	public static String getMantisPwd() {
		return MANTIS_PWD;
	}
	public static String getProjeto() {
		return PROJETO;
	}
	
	
}


