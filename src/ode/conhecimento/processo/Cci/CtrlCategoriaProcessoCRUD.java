package ode.conhecimento.processo.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cgt.AplCadastrarKCategoriaProcesso;
import ode.conhecimento.processo.Cih.FormDadosCategoriaProcesso;
import ode.conhecimento.processo.Cih.PainelCrudCategoriaProcesso;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlCategoriaProcessoCRUD extends CtrlCRUD<KCategoriaProcesso> {
	@Override
	public void iniciar() {
		super.iniciar();
		
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKCategoriaProcesso) SpringUtil
				.getBean("aplCadastrarKCategoriaProcesso");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudCategoriaProcesso();
		
	}


	@Override
	public KCategoriaProcesso factoryObjetoDados() {
		return new KCategoriaProcesso();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosCategoriaProcesso();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Categoria de Processo";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Categoria de Processo";
	}
}
