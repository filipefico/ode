package ode.conhecimento.processo.ciu;


import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KRecursoHardware;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHardware;

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
	public AplCRUD<KRecursoHardware> definirAplCRUD() {
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
