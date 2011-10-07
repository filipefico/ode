package ode._controleFerramenta.ciu;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleFerramenta.cgt.AplCadastrarFerramentaSoftware;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cgt.AplCadastrarKFerramentaSoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public AplCRUD<FerramentaSoftware> definirAplCRUD() {
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
