package ode.processoPadrao.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.exemplo2.pessoa.Cgt.AplCadastrarPessoaExemplo;
import ode.exemplo2.pessoa.Cih.FormDadosPessoaExemplo;
import ode.exemplo2.pessoa.Cih.PainelCrudPessoa;
import ode.processoPadrao.Cdp.CompPP;

import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.CompPPProcessoSimples;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;
import ode.processoPadrao.Cgt.AplCadastrarProcessoPadrao;
import ode.processoPadrao.Cih.PainelCrudProcessoPadrao;
import ode.processoPadrao.Cih.FormDadosProcessoPadrao;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlDefinirProcessoPadraoCRUD extends CtrlCRUD<CompPP> {
	
		public static String TIPO_COMPP_PROCESSO_COMPLEXO = "CompPP Processo Complexo";
	    public static String TIPO_COMPP_PROCESSO_SIMPLES = "CompPP Processo Simples";
	    //public static String TIPO_COMPP_MACROATIVIDADE = "CompPP Macroatividade";
	    /** Aplicação de definição ded processo padrão */
	    //private AplDefinirProcessoPadrao aplDefinirProcessoPadrao = new AplDefinirProcessoPadrao();
	    /** Janela Principal do ambiente de definição de processo padrão. */
	    
	
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
	public NucleoAplCadastroBase<CompPP> definirNucleoAplCadastroBase() {
		return (AplCadastrarProcessoPadrao) SpringUtil
				.getBean("aplCadastrarProcessoPadrao");
	}

	@Override
	public PainelCRUD<CompPP> definirPainelCRUD() {
		return new PainelCrudProcessoPadrao();
		
	}


	@Override
	public CompPP factoryObjetoDados() {
		return new CompPP();
	}

	@Override
	public FormularioDadosCRUD<CompPP> definirFormularioCadastro() {
		return new FormDadosProcessoPadrao();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "CompPP";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de CompPP";
	}
	
}
