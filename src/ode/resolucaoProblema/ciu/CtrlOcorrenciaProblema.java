package ode.resolucaoProblema.ciu;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.entidadeProblema.cdp.ArtefatoProblema;
import ode.entidadeProblema.cdp.ProjetoProblema;
import ode.entidadeProblema.cgt.AplCadastrarArtefatoProblema;
import ode.entidadeProblema.cgt.AplCadastrarProjetoProblema;
import ode.problema.cdp.KProblema;
import ode.problema.cgt.AplCadastrarKProblema;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;
import ode.resolucaoProblema.cgt.AplRegistrarOcorrenciaProblema;
@Controller (CtrlOcorrenciaProblema.Nome)
public class CtrlOcorrenciaProblema extends CtrlCRUD<OcorrenciaProblema>{

	
	
	private static final long serialVersionUID = -7588639120008471693L;
	
	@Autowired
	private AplRegistrarOcorrenciaProblema aplRegistrarOcorrenciaProblema;
	private String titulo = "Cadastro de Ocorrência de Problema";
	public static final String Nome = "CtrlOcorrenciaProblema";
	@Autowired
	private AplCadastrarKProblema aplCadastrarKProblema;
	@Autowired
	private AplCadastrarArtefatoProblema aplCadastrarArtefatoProblema;
	@Autowired
	private AplCadastrarProjetoProblema aplCadastrarProjetoProblema;
	@Autowired
	private AplCadastrarRecursoHumano aplCadastrarRecursoHumano;
	
	
	@Override
	public String definirTituloJanelaDados() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public AplCRUD<OcorrenciaProblema> definirAplCRUD() {
		// TODO Auto-generated method stub
		return aplRegistrarOcorrenciaProblema;
	}

	
	@Override
	public PainelCRUD<OcorrenciaProblema> definirPainelCRUD() {
		// TODO Auto-generated method stub
		return new PainelOcorrenciaProblema();
		
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		// TODO Auto-generated method stub
		return titulo;
	}
	

	@Override
	public FormularioDadosCRUD<OcorrenciaProblema> definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return new FormDadosOcorrenciaProblema();
	}
	
	@Override
	public OcorrenciaProblema factoryObjetoDados() {
		// TODO Auto-generated method stub
		return new OcorrenciaProblema();
	}
	
	public AplCadastrarKProblema getAplCadastrarKProblema() {
		return aplCadastrarKProblema;
	}
	
	public void setAplCadastrarKProblema(
			AplCadastrarKProblema aplCadastrarKProblema) {
		this.aplCadastrarKProblema= aplCadastrarKProblema;
	}
	public Collection<KProblema> listarKProblema() {
		return getAplCadastrarKProblema().recuperarTodos();
	}
	
	public AplCadastrarArtefatoProblema getAplCadastrarArtefatoProblema() {
		return aplCadastrarArtefatoProblema;
	}
	
	public void setAplCadastrarArtefatoProblema(
			AplCadastrarArtefatoProblema aplCadastrarArtefatoProblema) {
		this.aplCadastrarArtefatoProblema= aplCadastrarArtefatoProblema;
	}
	public Collection<ArtefatoProblema> listarArtefatoProblema() {
		return getAplCadastrarArtefatoProblema().recuperarTodos();
	}
	
	public AplCadastrarProjetoProblema getAplCadastrarProjetoProblema() {
		return aplCadastrarProjetoProblema;
	}
	
	public void setAplCadastrarProjetoProblema(
			AplCadastrarProjetoProblema aplCadastrarProjetoProblema) {
		this.aplCadastrarProjetoProblema= aplCadastrarProjetoProblema;
	}
	public Collection<ProjetoProblema> listarProjetoProblema() {
		return getAplCadastrarProjetoProblema().recuperarTodos();
	}

	public AplCadastrarRecursoHumano getAplCadastrarRecursoHumano() {
		return aplCadastrarRecursoHumano;
	}
	
	public void setAplCadastrarRecursoHumano(
			AplCadastrarRecursoHumano aplCadastrarPRecursoHumano) {
		this.aplCadastrarRecursoHumano= aplCadastrarPRecursoHumano;
	}
	public Collection<RecursoHumano> listarRecursoHumano() {
		return getAplCadastrarRecursoHumano().recuperarTodos();
	}
	
	
	
}

