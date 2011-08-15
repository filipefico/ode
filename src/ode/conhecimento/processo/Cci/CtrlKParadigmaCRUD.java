package ode.conhecimento.processo.Cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;
import ode.conhecimento.processo.Cdp.KParadigma;
import ode.conhecimento.processo.Cgt.AplCadastrarKParadigma;
import ode.conhecimento.processo.Cih.FormDadosKParadigma;
import ode.conhecimento.processo.Cih.PainelCrudKParadigma;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

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
	public AplBase definirNucleoAplCadastroBase() {
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
