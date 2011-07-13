package ode.conhecimento.processo.Cci;

import ode.conhecimento.processo.Cdp.KTipoSoftware;
import ode.conhecimento.processo.Cgt.AplCadastrarKTipoSoftware;
import ode.conhecimento.processo.Cih.FormDadosKTipoSoftware;
import ode.conhecimento.processo.Cih.PainelCrudKTipoSoftware;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlTipoSoftwareCRUD extends CtrlCRUD<KTipoSoftware> {
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
		return (AplCadastrarKTipoSoftware) SpringUtil
				.getBean("aplCadastrarTipoSoftware");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKTipoSoftware();
	}

	@Override
	public KTipoSoftware factoryObjetoDados() {
		return new KTipoSoftware();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKTipoSoftware();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Tipo de Software";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Tipo de Software";
	}
	


}
