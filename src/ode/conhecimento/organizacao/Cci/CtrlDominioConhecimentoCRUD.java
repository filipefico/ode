package ode.conhecimento.organizacao.Cci;

import org.zkoss.zkplus.spring.SpringUtil;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.Cgt.AplCadastrarKDominioConhecimento;
import ode.conhecimento.organizacao.Cih.FormDadosDominioConhecimento;
import ode.conhecimento.organizacao.Cih.PainelCrudDominioConhecimento;

public class CtrlDominioConhecimentoCRUD extends CtrlCRUD<KDominioConhecimento> {
	@Override
	public void iniciar() {
		super.iniciar();
		alturaJanDados = "300px";
		larguraJandados = "400px";
		
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKDominioConhecimento) SpringUtil
				.getBean("aplCadastrarKDominioConhecimento");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudDominioConhecimento();
		
	}


	@Override
	public KDominioConhecimento factoryObjetoDados() {
		return new KDominioConhecimento();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosDominioConhecimento();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Domínio de Conhecimento";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Domínio de Conhecimento";
	}
}
