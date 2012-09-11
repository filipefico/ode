package ode.pgds.cdp;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resultado")
public class Resultado {
	
	
	
	Map<String, Integer> repositorios;
	
	public Resultado(Map<String, Integer> repositorios){
		this.repositorios = repositorios;
	}
	
	public Resultado(){
		
	}

	public Map<String, Integer> getRepositorios() {
		return repositorios;
	}

	public void setRepositorios(Map<String, Integer> repositorios) {
		this.repositorios = repositorios;
	}

}
