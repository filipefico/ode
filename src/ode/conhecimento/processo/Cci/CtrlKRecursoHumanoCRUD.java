package ode.conhecimento.processo.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHumano;
import ode.conhecimento.processo.Cih.FormDadosKRecursoHumano;
import ode.conhecimento.processo.Cih.PainelCrudKRecursoHumano;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlKRecursoHumanoCRUD extends CtrlCRUD<KRecursoHumano> {
@Override
	public void iniciar() {
		super.iniciar();
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKRecursoHumano) SpringUtil
				.getBean("aplCadastrarKRecursoHumano");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKRecursoHumano();
		
	}


	@Override
	public KRecursoHumano factoryObjetoDados() {
		return new KRecursoHumano();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKRecursoHumano();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Recurso Humano";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Recurso Humano com Controlador";
	}
	



}
