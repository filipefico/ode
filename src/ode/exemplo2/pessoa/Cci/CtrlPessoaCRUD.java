package ode.exemplo2.pessoa.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.visao.principal.JanelaSimples;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.exemplo2.pessoa.Cgt.AplCadastrarPessoaExemplo;
import ode.exemplo2.pessoa.Cih.FormDadosPessoaExemplo;
import ode.exemplo2.pessoa.Cih.PainelCrudPessoa;
import ode.exemplo2.pessoa.Cih.PainelSelecaoPessoa;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlPessoaCRUD extends CtrlCRUD<PessoaExemplo> {
@Override
	public void iniciar() {
		super.iniciar();
		/*
		JanelaSimples jan = factoryJanelaSimples();				
		String titulo ="Teste Painel Selecao";
		PainelSelecaoPessoa p = new PainelSelecaoPessoa();		
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
		return (AplCadastrarPessoaExemplo) SpringUtil
				.getBean("aplCadastrarPessoaExemplo");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudPessoa();
		
	}


	@Override
	public PessoaExemplo factoryObjetoDados() {
		return new PessoaExemplo();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosPessoaExemplo();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Pessoa";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Pessoa com Controlador";
	}
	



}
