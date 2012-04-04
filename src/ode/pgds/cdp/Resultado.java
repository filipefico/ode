package ode.pgds.cdp;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resultado")
public class Resultado {
	
	List<String> repositorios;
	
	public Resultado(List<String> repositorios){
		this.repositorios = repositorios;
	}
	
	public Resultado(){
		
	}

	public List<String> getRepositorios() {
		return repositorios;
	}

	public void setRepositorios(List<String> repositorios) {
		this.repositorios = repositorios;
	}

}
