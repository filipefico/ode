package ode.conhecimento.processo.Cci;


import nucleo.comuns.crud.controlador.CtrlCRUD;
import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.PainelCRUD;
import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHardware;
import ode.conhecimento.processo.Cih.FormDadosKRecursoHardware;
import ode.conhecimento.processo.Cih.PainelCrudKRecursoHardware;
import ode.nucleo.cgt.NucleoAplCadastroBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlKRecursoHardwareCRUD extends CtrlCRUD<KRecursoHardware> {
	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Autowired
	AplCadastrarKRecursoHardware aplCadastrarKRecursoHardware;

	public AplCadastrarKRecursoHardware getAplCadastrarKRecursoHardware() {
		return aplCadastrarKRecursoHardware;
	}

	public void setAplCadastrarKRecursoHardware(
			AplCadastrarKRecursoHardware aplCadastrarKRecursoHardware) {
		this.aplCadastrarKRecursoHardware = aplCadastrarKRecursoHardware;
	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return aplCadastrarKRecursoHardware;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKRecursoHardware();

	}

	@Override
	public KRecursoHardware factoryObjetoDados() {
		return new KRecursoHardware();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKRecursoHardware();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Recurso Hardware";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Recurso Hardware com Controlador";
	}

}
