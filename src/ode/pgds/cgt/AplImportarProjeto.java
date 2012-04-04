package ode.pgds.cgt;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.util.NucleoContexto;
import ode.conhecimento.requisito.cdp.TipoRequisito;
import ode.conhecimento.requisito.cgd.TipoRequisitoDAO;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.gerenciaRequisitos.cdp.Prioridade;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.gerenciaRequisitos.cgd.PrioridadeDAO;
import ode.gerenciaRequisitos.cgd.RequisitoDAO;
import ode.pgds.cdp.Resultado;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;
import ode.uml.cgd.CasoUsoDAO;
import ode.uml.cgd.ClasseDAO;
import ode.uml.cgd.PacoteDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;

@Service
@Transactional
public class AplImportarProjeto {
	
	@Autowired
	PacoteDAO pacoteDao;
	
	@Autowired
	ClasseDAO classeDao;
	
	@Autowired
	CasoUsoDAO casoUsoDao;
	
	@Autowired
	TipoRequisitoDAO tipoRequisitoDao;
	
	@Autowired
	PrioridadeDAO prioridadeDao;
	
	@Autowired
	RequisitoDAO requisitoDao;
	
	@Autowired
	ProjetoDAO projetoDao;
	
	Model modelo;
	List<Prioridade> prioridades = new ArrayList<Prioridade>();
	List<TipoRequisito> tiposRequisito = new ArrayList<TipoRequisito>();
	Projeto projeto;
	Pacote pacoteDefault;
	
	@SuppressWarnings("rawtypes")
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

	public List<String> recuperarRepositorios(String enderecoBase){
		List<String> lista = new ArrayList<String>();
		
		RestTemplate restTemplate = getTemplate();
		String url = enderecoBase.concat("/remoting/ws/repositorios/");
		Resultado result = null;
		try{
			result = restTemplate.getForObject(url, Resultado.class);
		}catch(Exception e){
			e.printStackTrace();
			return lista;
		}

		lista.addAll(result.getRepositorios());
		
		return lista;
	}
	
	public static Model createModelFromString(String str) {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		ByteArrayInputStream bais = null;
		if (str != null && !str.trim().equals("")) {
			
			try {
				bais = new ByteArrayInputStream(str.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}
			model.read(bais, "");
		}
		return model;
	}
	
	
	public static ResultSet ExecuteQueryByModel(String queryString, Model m) {
		/*
		 * com.hp.hpl.jena.query.Query query = QueryFactory.create(queryString);
		 * // Execute the query and obtain results
		 * com.hp.hpl.jena.query.QueryExecution qe =
		 * QueryExecutionFactory.create( query, Syntax.syntaxARQ, m);
		 * 
		 * com.hp.hpl.jena.query.ResultSet results = qe.execSelect();
		 */
		QueryExecution qe = QueryExecutionFactory.create(queryString,
				Syntax.syntaxARQ, m);
		ResultSet results = qe.execSelect();

		return results;
	}
	
	public Model recuperaModeloSemantico(String repositorio, String enderecoBase){
		RestTemplate restTemplate = getTemplate();		
		String url = enderecoBase.concat("/remoting/ws/consultar/");
		//recupero o modelo semantico de um repositorio
		String modeloSemantico = restTemplate.postForObject(url, repositorio,
				String.class);
		
		return createModelFromString(modeloSemantico);
	}
	
	public static List<String> converteRetornoSparql(ResultSet resultado) {

		List<String> lista = new ArrayList<String>();
		if (resultado == null)
			return lista;

		while (resultado.hasNext()) {
			com.hp.hpl.jena.query.QuerySolution soln = resultado.nextSolution();

			String caminhoFull = soln.toString();

			lista.add(caminhoFull);

		}

		return lista;
	}
	
	public Requisito stringToRequisito (String string){
		Requisito requisito = new Requisito();
		int indexInicio, indexFim;
		
		/* Adiciona Identificador */
		indexInicio = string.indexOf("#") + 1;
		indexFim = string.indexOf(">");
		String identificador = string.substring(indexInicio, indexFim);
		requisito.setIdentificador(identificador);
		
		/* Adiciona Descrição */
		indexInicio = string.indexOf("\"", string.indexOf("?descricao"));
		indexFim = string.indexOf("^^", indexInicio);
		String descricao = string.substring(indexInicio + 1, indexFim - 1);
		requisito.setDescricao(descricao);
		
		/* Adiciona Prioridade */
		indexInicio = string.indexOf("\"", string.indexOf("?prioridade"));
		indexFim = string.indexOf("^^", indexInicio);
		String prioridade = string.substring(indexInicio + 1, indexFim - 1);
		if (prioridade.equals("Alta")){
			requisito.setPrioridade(prioridades.get(0));
		}else{
			if (prioridade.equals("Baixa")){
				requisito.setPrioridade(prioridades.get(1));
			}else{
				requisito.setPrioridade(prioridades.get(2));
			}
		}
		
		/* Adiciona Responsável */
		Set<RecursoHumano> responsaveis = new HashSet<RecursoHumano>();
		responsaveis.add(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
		requisito.setResponsaveis(responsaveis);
		
		/* Adiciona Projeto */
		requisito.setProjeto(projeto);
		
		/* Adiciona Tipo de Requisito */
		if (requisito.getIdentificador().contains("Funcional")){
			requisito.setTipoRequisito(tiposRequisito.get(0));
		}else{
			if (requisito.getIdentificador().contains("Não")){
				requisito.setTipoRequisito(tiposRequisito.get(1));
			}else{
				requisito.setTipoRequisito(tiposRequisito.get(2));
			}
		}
		
		StringBuilder sparl = new StringBuilder();
		
		/* Adiciona Dependencias */
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?requisito");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:Requisito");
		sparl.append(" . ");
		sparl.append(" ?individuo onto:conflitaCom ?requisito");
		sparl.append(" . ");
		sparl.append(" ?individuo ?p onto:" + requisito.getIdentificador());
		sparl.append("} order by ?requisito");
		
		List<String> requisitosString = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		Set<Requisito> requisitos = new HashSet<Requisito>();
		for (String string2 : requisitosString) {
			requisitos.add(requisitoDao.obterPorIdentificadorEProjeto(string2, projeto));
		}
		requisito.setConflitos(requisitos);
		
		/* Adiciona Conflitos */
		sparl = new StringBuilder();
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?requisito");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:Requisito");
		sparl.append(" . ");
		sparl.append(" ?individuo onto:dependeDe ?requisito");
		sparl.append(" . ");
		sparl.append(" ?individuo ?p onto:" + requisito.getIdentificador());
		sparl.append("} order by ?requisito");
		
		List<String> dependenciasString = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		Set<Requisito> dependencias = new HashSet<Requisito>();
		for (String string2 : dependenciasString) {
			dependencias.add(requisitoDao.obterPorIdentificadorEProjeto(string2, projeto));
		}
		requisito.setDependencias(dependencias);
		
		/* Adiciona Casos de Uso */
		sparl = new StringBuilder();
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?casouso");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:Requisito");
		sparl.append(" . ");
		sparl.append(" ?individuo onto:atendidoPorCasoUso ?casouso");
		sparl.append(" . ");
		sparl.append(" ?individuo ?p onto:" + requisito.getIdentificador());
		sparl.append("} order by ?casouso");
		
		List<String> casosUsoString = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		Set<CasoUso> casosUso = new HashSet<CasoUso>();
		for (String string2 : casosUsoString) {
			casosUso.add(casoUsoDao.obterPorNomeEProjeto(string2, projeto));
		}
		requisito.setCasosUso(casosUso);
		
		/* Adiciona Pacote */
		Set<Pacote> pacotes = new HashSet<Pacote>();
		pacotes.add(pacoteDefault);
		requisito.setPacotes(pacotes);

		return requisito;
	}
	
	public Classe stringToClasse (String string){
		Classe classe = new Classe();
		int indexInicio, indexFim;
				
		/* Adiciona Nome */
		indexInicio = string.indexOf("#") + 1;
		indexFim = string.indexOf(">");
		String nome = string.substring(indexInicio, indexFim);
		classe.setNome(nome);
		
		return classe;
	}
	
	public Pacote stringToPacote (String string){
		Pacote pacote = new Pacote();
		int indexInicio, indexFim;
		
		/* Adiciona Nome */
		indexInicio = string.indexOf("#") + 1;
		indexFim = string.indexOf(">");
		String nome = string.substring(indexInicio, indexFim);
		pacote.setNome(nome);
		
		/* Adiciona Descrição */
		indexInicio = string.indexOf("\"", string.indexOf("?descricao"));
		indexFim = string.indexOf("^^", indexInicio);
		String descricao = string.substring(indexInicio + 1, indexFim - 1);
		pacote.setDescricao(descricao);
		
		return pacote;
	}
	
	public CasoUso stringToCasoUso(String string){
		CasoUso casoUso = new CasoUso();
		int indexInicio, indexFim;
		
		/* Adiciona Nome */
		indexInicio = string.indexOf("#") + 1;
		indexFim = string.indexOf(">");
		String nome = string.substring(indexInicio, indexFim);
		casoUso.setNome(nome);
		
		/* Adiciona Descrição */
		indexInicio = string.indexOf("\"", string.indexOf("?descricao"));
		indexFim = string.indexOf("^^", indexInicio);
		String descricao = string.substring(indexInicio + 1, indexFim - 1);
		casoUso.setDescricao(descricao);
		
		StringBuilder sparl = new StringBuilder();
		
		/* Adiciona Classes */
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?individuo");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:Classe");
		sparl.append(" . ");
		sparl.append(" ?individuo onto:trataLogicaDe ?casodeuso");
		sparl.append(" . ");
		sparl.append(" ?casodeuso ?p onto:" + casoUso.getNome());
		sparl.append("} order by ?individuo");
		
		List<String> classesString = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		Set<Classe> classes = new HashSet<Classe>();
		for (String string2 : classesString) {
			classes.add(classeDao.recuperarPorNomeEProjeto(string2, projeto));
		}
		casoUso.setClasses(classes);

		return casoUso;
	}
	
	public void recuperarDados (String enderecoBase, String repositorio, String projetoNome){
		modelo = recuperaModeloSemantico(repositorio, enderecoBase);
		
		prioridades.addAll(prioridadeDao.recuperarTodosComOrdenacao("nome"));
		
		tiposRequisito.addAll(tipoRequisitoDao.recuperarTodosComOrdenacao("descricao"));

		projeto = new Projeto();
		projeto.setNome(projetoNome);
		projeto.setDescricao("Projeto importado da PGDS");
		projetoDao.salvar(projeto);
		
		pacoteDefault = new Pacote();
		pacoteDefault.setNome("Default");
		pacoteDefault.setProjeto(projeto);
		pacoteDao.salvar(pacoteDefault);
		
		StringBuilder sparl = new StringBuilder();
		
		/* Importa Classes */
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?individuo");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:Classe");
		sparl.append(" . ");
		sparl.append("} order by ?individuo");
		
		List<String> classes = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		for (String string : classes) {
			classeDao.salvar(stringToClasse(string));
		}
		
		/* Importa Módulos */
		sparl = new StringBuilder();
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?individuo ?descricao ?pacote");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:Modulo");
		sparl.append(" . ");
		sparl.append("?individuo onto:descricao ?descricao");
		sparl.append(" . ");
		sparl.append("} order by ?individuo");
		
		List<String> pacotes = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		for (String string : pacotes) {
			pacoteDao.salvar(stringToPacote(string));
		}

		/* Importa Casos de Uso */
		sparl = new StringBuilder();
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?individuo ?descricao ?pacote");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:CasoUso");
		sparl.append(" . ");
		sparl.append("?individuo onto:descricao ?descricao");
		sparl.append(" . ");
		sparl.append("?individuo onto:prioridade ?pacote");
		sparl.append(" . ");
		sparl.append("} order by ?individuo");
		
		List<String> casosUso = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		for (String string : casosUso) {
			casoUsoDao.salvar(stringToCasoUso(string));
		}
		
		/* Importa Requisitos */
		sparl = new StringBuilder();
		sparl.append(" PREFIX onto: <http://localhost/ontologies/SE/onto.owl#> ");
		sparl.append(" PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ");
		sparl.append("select distinct ?individuo ?descricao ?prioridade");
		sparl.append(" WHERE { ");
		sparl.append(" ?individuo rdf:type onto:Requisito");
		sparl.append(" . ");
		sparl.append("?individuo onto:descricao ?descricao");
		sparl.append(" . ");
		sparl.append("?individuo onto:prioridade ?prioridade");
		sparl.append(" . ");
		sparl.append("} order by ?individuo");
		
		List<String> requisitos = converteRetornoSparql(ExecuteQueryByModel(sparl.toString(), modelo));
		for (String string : requisitos) {
			requisitoDao.salvar(stringToRequisito(string));
		}	
	}
}
