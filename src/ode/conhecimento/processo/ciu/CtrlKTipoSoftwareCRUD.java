package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KTipoSoftware;
import ode.conhecimento.processo.cgt.AplCadastrarKTipoSoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class CtrlKTipoSoftwareCRUD extends CtrlCRUD<KTipoSoftware> {
	
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
	AplCadastrarKTipoSoftware aplCadastrarTipoSoftware;

	public AplCadastrarKTipoSoftware getAplCadastrarTipoSoftware() {
		return aplCadastrarTipoSoftware;
	}

	public void setAplCadastrarTipoSoftware(
			AplCadastrarKTipoSoftware aplCadastrarTipoSoftware) {
		this.aplCadastrarTipoSoftware = aplCadastrarTipoSoftware;
	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<KTipoSoftware> definirAplCRUD() {
		return aplCadastrarTipoSoftware;
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
		return "Tipos de Software";
	}

}
