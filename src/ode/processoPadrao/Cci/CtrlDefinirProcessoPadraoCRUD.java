package ode.processoPadrao.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD.ModoExibicao;
import nucleo.comuns.excecao.CtrlExcecoes;
import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cih.FormDadosKArtefato;
import ode.exemplo2.pessoa.Cdp.PessoaExemplo;
import ode.exemplo2.pessoa.Cgt.AplCadastrarPessoaExemplo;
import ode.exemplo2.pessoa.Cih.FormDadosPessoaExemplo;
import ode.exemplo2.pessoa.Cih.PainelCrudPessoa;
import ode.processoPadrao.Cdp.CompPP;

import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.CompPPProcessoSimples;
import ode.processoPadrao.Cdp.CompPPProcessoComplexo;
import ode.processoPadrao.Cdp.InterfaceCompPP;
import ode.processoPadrao.Cgt.AplDefinirProcessoPadrao;
import ode.processoPadrao.Cih.PainelCrudDefinirProcessoPadrao;
import ode.processoPadrao.Cih.FormDadosDefinirProcessoPadrao;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlDefinirProcessoPadraoCRUD extends CtrlCRUD<CompPP> {
	
		public static String TIPO_COMPP_PROCESSO_COMPLEXO = "CompPP Processo Complexo";
	    public static String TIPO_COMPP_PROCESSO_SIMPLES = "CompPP Processo Simples";
	    public static String TIPO_COMPP_MACROATIVIDADE = "CompPP Macroatividade";
	   
	    
	    /** Janela Principal do ambiente de definição de processo padrão. */
	    @Override
		public void iniciar() {
			super.iniciar();
		}

 	
	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase<CompPP> definirNucleoAplCadastroBase(){ 
		return (AplDefinirProcessoPadrao) SpringUtil
				.getBean("aplDefinirProcessoPadrao");
	}

	@Override
	public PainelCRUD<CompPP> definirPainelCRUD() {
		return new PainelCrudDefinirProcessoPadrao();
		
	}

	@Override
	public CompPP factoryObjetoDados() {
		return new CompPP();
	}

	@Override
	public FormularioDadosCRUD<CompPP> definirFormularioCadastro() {
		return new FormDadosDefinirProcessoPadrao();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "CompPP";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Definir CompPP";
	}
	

}
