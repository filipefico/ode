package ode.conhecimento.processo.Cci;

import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cgt.AplCadastrarKCategoriaProcesso;
import ode.conhecimento.processo.Cih.FormDadosCategoriaProcesso;
import ode.conhecimento.processo.Cih.PainelCrudCategoriaProcesso;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

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
