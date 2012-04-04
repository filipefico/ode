package ode.pgds.teste;

import java.util.Arrays;
import java.util.List;

import ode.pgds.cdp.Resultado;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

public class WebServiceTeste {
	
	public void testeChamadaReqODE() {

		try {
			RestTemplate restTemplate = getTemplate();
			String url = "http://localhost:8080/pgds/remoting/ws/repositorios/";
			Resultado result = restTemplate.getForObject(url, Resultado.class);

			//recupero a lista de repositorios
			List<String> repos = result.getRepositorios();
			
			System.out.println("URl´s");
			for (String string : repos) {
				System.out.println(string);
			}
			
			url = "http://localhost:8080/pgds/remoting/ws/consultar/";
			//recupero o modelo semantico de um repositorio
			String modeloSemantico = restTemplate.postForObject(url, repos.get(0),
					String.class);
			System.out.println("msemantico =" +modeloSemantico);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static RestTemplate getTemplate() {

		RestTemplate restTemplate = new RestTemplate();

		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		Class[] vetor = { Resultado.class };
		marshaller.setClassesToBeBound(vetor);
		// conversor de objetos
		MarshallingHttpMessageConverter marshallingHttpConverter = new MarshallingHttpMessageConverter(
				marshaller);
		marshallingHttpConverter.setSupportedMediaTypes(Arrays
				.asList(MediaType.APPLICATION_XML));
		restTemplate.getMessageConverters().add(marshallingHttpConverter);

		// conversor de string
		StringHttpMessageConverter sCOnverter = new StringHttpMessageConverter();
		sCOnverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN));
		restTemplate.getMessageConverters().add(sCOnverter);

		return restTemplate;

	}
	

	// @Test
	public void doTEste() {

		try {
			RestTemplate restTemplate = getTemplate();
			String url = "http://localhost:8080/pgds/remoting/ws/lista2/";
			Resultado result = restTemplate.getForObject(url, Resultado.class);

			
			List<String> repos = result.getRepositorios();
			

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// @Test
	public void doTEste2() {

		// Send the request as GET
		try {

			RestTemplate restTemplate = getTemplate();
			String url = "http://localhost:8080/pgds/remoting/ws/lista3/";
			String result = restTemplate.getForObject(url, String.class);

			System.out.println("String = " + result);

		} catch (Exception e) {

		}
	}

	// @Test
	public void enviarConsulta() {

		try {
			RestTemplate restTemplate = getTemplate();
			String url = "http://localhost:8080/pgds/remoting/ws/consultar/";
			String parametro = "select * fwom ";
			String result = restTemplate.postForObject(url, parametro,
					String.class);

			System.out.println("String retorno= " + result);

		} catch (Exception e) {

		}

	}
	
	public static void main(String[] args) {
		WebServiceTeste w = new WebServiceTeste();
		w.testeChamadaReqODE();
	}

	

}
