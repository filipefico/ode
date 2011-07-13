package ode.conhecimento.processo.Cci;

import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgt.AplCadastrarKProcesso;
import ode.conhecimento.processo.Cih.FormDadosKProcesso;
import ode.conhecimento.processo.Cih.PainelCrudKProcesso;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlKProcessoCRUD extends CtrlCRUD<KProcesso> {
	@Override
	public void iniciar() {
		super.iniciar();
		
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKProcesso) SpringUtil
				.getBean("aplCadastrarKProcesso");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKProcesso();
		
	}


	@Override
	public KProcesso factoryObjetoDados() {
		return new KProcesso();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKProcesso();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Processo";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Processo";
	}
}
