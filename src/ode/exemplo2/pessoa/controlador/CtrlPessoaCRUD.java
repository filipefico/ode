package ode.exemplo2.pessoa.controlador;

import java.util.Collection;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.visao.principal.JanelaSimples;
import ode.exemplo2.pessoa.aplicacao.AplCadastrarPessoaExemplo;
import ode.exemplo2.pessoa.dominio.PessoaExemplo;
import ode.exemplo2.pessoa.visao.FormDadosPessoaExemplo;
import ode.exemplo2.pessoa.visao.PainelCrudPessoa;
import ode.exemplo2.pessoa.visao.PainelSelecaoPessoa;

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

	public Collection<PessoaExemplo> recuperarTodasPessoas() {
		AplCadastrarPessoaExemplo apl= (AplCadastrarPessoaExemplo) SpringUtil
		.getBean("aplCadastrarPessoaExemplo");;
		
		try {
			return apl.recuperarTodos();
		} catch (NucleoRegraNegocioExcecao e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	



}
