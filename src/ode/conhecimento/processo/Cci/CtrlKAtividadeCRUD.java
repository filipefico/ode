package ode.conhecimento.processo.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.principal.JanelaSimples;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.Cgt.AplCadastrarKDominioAplicacao;
import ode.conhecimento.processo.Cih.FormDadosKAtividade;
import ode.conhecimento.processo.Cih.FormDadosKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelCrudKAtividade;
import ode.conhecimento.processo.Cih.PainelCrudKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelSelecaoKDominioAplicacao;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlKAtividadeCRUD extends CtrlCRUD<KAtividade> {
@Override
	public void iniciar() {
		super.iniciar();
		alturaJanDados = "350px";
		larguraJandados = "750px";
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKAtividade) SpringUtil
				.getBean("aplCadastrarKAtividade");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKAtividade();
		
	}


	@Override
	public KAtividade factoryObjetoDados() {
		return new KAtividade();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKAtividade();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Atividade";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Atividade";
	}
	



}
