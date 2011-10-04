package ode._controleFerramenta.cci;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.conhecimento.processo.Cgt.AplCadastrarKFerramentaSoftware;
import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleFerramenta.cgt.AplCadastrarFerramentaSoftware;
import ode._controleFerramenta.cih.FormDadosFerramentaSoftware;
import ode._controleFerramenta.cih.PainelCRUDFerramentaSoftware;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

@Controller
public class CtrlFerramentaSoftwareCRUD extends CtrlCRUD<FerramentaSoftware> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarFerramentaSoftware aplCadastrarFerramentaSoftware;
	
	@Autowired
	private AplCadastrarKFerramentaSoftware aplCadastrarKFerramentaSoftware;

	@Override
	public String definirTituloJanelaDados() {
		return "Ferramenta de Software";
	}

	@Override
	public AplBase<FerramentaSoftware> definirNucleoAplCadastroBase() {
		return this.aplCadastrarFerramentaSoftware;
	}

	@Override
	public PainelCRUD<FerramentaSoftware> definirPainelCRUD() {
		return new PainelCRUDFerramentaSoftware();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Ferramentas de Software";
	}

	@Override
	public FormularioDadosCRUD<FerramentaSoftware> definirFormularioCadastro() {
		return new FormDadosFerramentaSoftware();
	}

	@Override
	public FerramentaSoftware factoryObjetoDados() {
		return new FerramentaSoftware();
	}

	public AplCadastrarKFerramentaSoftware getAplCadastrarKFerramentaSoftware() {
		return aplCadastrarKFerramentaSoftware;
	}

	public void setAplCadastrarKFerramentaSoftware (
			AplCadastrarKFerramentaSoftware aplCadastrarKRecursoHardware) {
		this.aplCadastrarKFerramentaSoftware = aplCadastrarKRecursoHardware;
	}
	
	public Collection<KFerramentaSoftware> listarKFerramentasSoftware() {
		return getAplCadastrarKFerramentaSoftware().recuperarTodos();
	}

}
