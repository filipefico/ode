package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KDominioAplicacao;
import ode.conhecimento.processo.cgt.AplCadastrarKDominioAplicacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public AplCRUD<KDominioAplicacao> definirAplCRUD() {
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
