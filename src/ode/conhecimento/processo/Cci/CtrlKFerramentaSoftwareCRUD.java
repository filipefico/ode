package ode.conhecimento.processo.Cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.conhecimento.processo.Cgt.AplCadastrarKFerramentaSoftware;
import ode.conhecimento.processo.Cih.FormDadosFerramentaSoftware;
import ode.conhecimento.processo.Cih.PainelCrudFerramentaSoftware;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

@Controller
public class CtrlKFerramentaSoftwareCRUD extends CtrlCRUD<KFerramentaSoftware> {
	
	
	
	@Override
	public void iniciar() {
		super.iniciar();
		
	}
	
	@Autowired
	private AplCadastrarKFerramentaSoftware aplCadastrarFerramenta;

	public AplCadastrarKFerramentaSoftware getAplCadastrarFerramenta() {
		return aplCadastrarFerramenta;
	}

	public void setAplCadastrarFerramenta(
			AplCadastrarKFerramentaSoftware aplCadastrarFerramenta) {
		this.aplCadastrarFerramenta = aplCadastrarFerramenta;
	}

	@Override
	public AplBase definirNucleoAplCadastroBase() {
		return aplCadastrarFerramenta;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudFerramentaSoftware();
		
	}


	@Override
	public KFerramentaSoftware factoryObjetoDados() {
		return new KFerramentaSoftware();
	}

	@Override
	public FormDadosFerramentaSoftware definirFormularioCadastro() {
		return new FormDadosFerramentaSoftware();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Ferramenta de Software";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Ferramentas de Software";
	}
}
