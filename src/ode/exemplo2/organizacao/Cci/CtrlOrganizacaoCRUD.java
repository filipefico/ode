package ode.exemplo2.organizacao.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.principal.JanelaSimples;
import ode.exemplo2.organizacao.Cdp.OrganizacaoExemplo;
import ode.exemplo2.organizacao.Cgt.AplCadastrarOrganizacaoExemplo;
import ode.exemplo2.organizacao.Cih.FormDadosOrganizacaoExemplo;
import ode.exemplo2.organizacao.Cih.PainelCrudOrganizacao;
import ode.exemplo2.organizacao.Cih.PainelSelecaoOrganizacao;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlOrganizacaoCRUD extends CtrlCRUD<OrganizacaoExemplo> {
@Override
	public void iniciar() {
		super.iniciar();
		/*
		JanelaSimples jan = factoryJanelaSimples();				
		String titulo ="Teste Painel Selecao";
		PainelSelecaoorganizacao p = new PainelSelecaoorganizacao();		
		p.setParent(jan);
		jan.setTitle(titulo);
		jan.setWidth(getLarguraJandados());
		jan.setHeight(getAlturaJanDados());
		jan.mostrar();
		*/
		
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarOrganizacaoExemplo) SpringUtil
				.getBean("aplCadastrarOrganizacaoExemplo");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudOrganizacao();
		
	}


	@Override
	public OrganizacaoExemplo factoryObjetoDados() {
		return new OrganizacaoExemplo();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosOrganizacaoExemplo();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "organizacao";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Organizacao com Controlador";
	}
	



}
