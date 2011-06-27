package ode.conhecimento.requisito.Cci;

import nucleo.comuns.aplicacao.NucleoAplCadastroBase;
import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.conhecimento.requisito.Cdp.KTipoRequisito;
import ode.conhecimento.requisito.Cgt.AplCadastrarKTipoRequisito;
import ode.conhecimento.requisito.Cih.FormDadosKTipoRequisito;
import ode.conhecimento.requisito.Cih.PainelCrudKTipoRequisito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

@Controller(CtrlKTipoRequisitoCRUD.NOME)
public class CtrlKTipoRequisitoCRUD extends CtrlCRUD<KTipoRequisito> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String NOME = "CtrlKTipoRequisitoCRUD";
	
	@Autowired
	private AplCadastrarKTipoRequisito aplCadastrarKTipoRequisito;
	
	@Override
	public void iniciar() {
		super.iniciar();
		alturaJanDados = "400px";
		larguraJandados = "400px";
		
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return aplCadastrarKTipoRequisito;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKTipoRequisito();
		
	}


	@Override
	public KTipoRequisito factoryObjetoDados() {
		return new KTipoRequisito();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKTipoRequisito();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Tipo de Requisito";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Tipo de Requisito";
	}
}
