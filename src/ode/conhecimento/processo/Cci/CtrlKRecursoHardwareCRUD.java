package ode.conhecimento.processo.Cci;


import ode.conhecimento.processo.Cdp.KRecursoHardware;
import ode.conhecimento.processo.Cgt.AplCadastrarKRecursoHardware;
import ode.conhecimento.processo.Cih.FormDadosKRecursoHardware;
import ode.conhecimento.processo.Cih.PainelCrudKRecursoHardware;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

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
	public AplBase definirNucleoAplCadastroBase() {
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
		return "Recurso de Hardware";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Recursos de Hardware";
	}

}
