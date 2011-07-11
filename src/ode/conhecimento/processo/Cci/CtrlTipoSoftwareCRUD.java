package ode.conhecimento.processo.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.conhecimento.processo.Cdp.KTipoSoftware;
import ode.conhecimento.processo.Cgt.AplCadastrarKTipoSoftware;
import ode.conhecimento.processo.Cih.FormDadosKTipoSoftware;
import ode.conhecimento.processo.Cih.PainelCrudKTipoSoftware;

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
