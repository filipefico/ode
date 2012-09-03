package ode.medicao.analiseMedicao.ciu;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.medicao.analiseMedicao.cdp.AcaoCorretiva;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.analiseMedicao.cdp.MonitoramentoObjetivo;
import ode.medicao.analiseMedicao.cgt.AplAcaoCorretiva;

@Controller
public class CtrlAcaoCorretiva extends CtrlCRUD<AcaoCorretiva>{

	@Autowired
	AplAcaoCorretiva aplAcaoCorretiva;
	
	@Override
	public String definirTituloJanelaDados() {
		return "Ação Corretiva";
	}

	@Override
	public AplCRUD<AcaoCorretiva> definirAplCRUD() {
		return aplAcaoCorretiva;
	}

	@Override
	public PainelCRUD<AcaoCorretiva> definirPainelCRUD() {
		return new PainelAcaoCorretiva();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Ações Corretivas";
	}

	@Override
	public FormularioDadosCRUD<AcaoCorretiva> definirFormularioCadastro() {
		return new FormAcaoCorretiva();
	}

	@Override
	public AcaoCorretiva factoryObjetoDados() {
		return new AcaoCorretiva();
	}

}
