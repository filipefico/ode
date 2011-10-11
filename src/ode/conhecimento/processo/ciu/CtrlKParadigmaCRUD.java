package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KParadigma;
import ode.conhecimento.processo.cgt.AplCadastrarKParadigma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlKParadigmaCRUD extends CtrlCRUD<KParadigma> {
	@Override
	public void iniciar() {
		super.iniciar();
		/*
		 * JanelaSimples jan = factoryJanelaSimples(); String titulo
		 * ="Teste Painel Selecao"; PainelSelecaoPessoa p = new
		 * PainelSelecaoPessoa(); p.setParent(jan); jan.setTitle(titulo);
		 * jan.setWidth(getLarguraJandados());
		 * jan.setHeight(getAlturaJanDados()); jan.mostrar();
		 */

	}

	@Autowired
	AplCadastrarKParadigma aplCadastrarKParadigma;

	public AplCadastrarKParadigma getAplCadastrarKParadigma() {
		return aplCadastrarKParadigma;
	}

	public void setAplCadastrarKParadigma(
			AplCadastrarKParadigma aplCadastrarKParadigma) {
		this.aplCadastrarKParadigma = aplCadastrarKParadigma;
	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<KParadigma> definirAplCRUD() {
		return aplCadastrarKParadigma;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKParadigma();

	}

	@Override
	public KParadigma factoryObjetoDados() {
		return new KParadigma();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKParadigma();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Paradigma";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Paradigmas";
	}
}
