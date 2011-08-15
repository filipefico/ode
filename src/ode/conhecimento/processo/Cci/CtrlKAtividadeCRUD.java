package ode.conhecimento.processo.Cci;

import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.Cgt.AplCadastrarKDominioAplicacao;
import ode.conhecimento.processo.Cih.FormDadosKAtividade;
import ode.conhecimento.processo.Cih.FormDadosKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelCrudKAtividade;
import ode.conhecimento.processo.Cih.PainelCrudKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelSelecaoKDominioAplicacao;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.JanelaSimples;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

@Controller
public class CtrlKAtividadeCRUD extends CtrlCRUD<KAtividade> {


	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Autowired
	AplCadastrarKAtividade aplCadastrarKAtividade;

	public AplCadastrarKAtividade getAplCadastrarKAtividade() {
		return aplCadastrarKAtividade;
	}

	public void setAplCadastrarKAtividade(
			AplCadastrarKAtividade aplCadastrarKAtividade) {
		this.aplCadastrarKAtividade = aplCadastrarKAtividade;
	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplBase definirNucleoAplCadastroBase() {
		return aplCadastrarKAtividade;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKAtividade();

	}

	@Override
	public KAtividade factoryObjetoDados() {
		return new KAtividade();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKAtividade();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Atividade";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Atividades";
	}

}
