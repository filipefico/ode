package ode.conhecimento.processo.Cci;

import ode.conhecimento.processo.Cdp.KDominioAplicacao;
import ode.conhecimento.processo.Cgt.AplCadastrarKDominioAplicacao;
import ode.conhecimento.processo.Cih.FormDadosKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelCrudKDominioAplicacao;
import ode.conhecimento.processo.Cih.PainelSelecaoKDominioAplicacao;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.JanelaSimples;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

@Controller
public class CtrlKDominioAplicacaoCRUD extends CtrlCRUD<KDominioAplicacao> {
	@Override
	public void iniciar() {
		super.iniciar();
		/*
		 * JanelaSimples jan = factoryJanelaSimples(); String titulo
		 * ="Teste Painel Selecao"; PainelSelecaokDominioAplicacao p = new
		 * PainelSelecaokDominioAplicacao(); p.setParent(jan);
		 * jan.setTitle(titulo); jan.setWidth(getLarguraJandados());
		 * jan.setHeight(getAlturaJanDados()); jan.mostrar();
		 */

	}

	@Autowired
	AplCadastrarKDominioAplicacao aplCadastrarKDominioAplicacao;

	public AplCadastrarKDominioAplicacao getAplCadastrarKDominioAplicacao() {
		return aplCadastrarKDominioAplicacao;
	}

	public void setAplCadastrarKDominioAplicacao(
			AplCadastrarKDominioAplicacao aplCadastrarKDominioAplicacao) {
		this.aplCadastrarKDominioAplicacao = aplCadastrarKDominioAplicacao;
	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return aplCadastrarKDominioAplicacao;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKDominioAplicacao();

	}

	@Override
	public KDominioAplicacao factoryObjetoDados() {
		return new KDominioAplicacao();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKDominioAplicacao();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Domínio de Conhecimento";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Domínios de Conhecimento";
	}

}
