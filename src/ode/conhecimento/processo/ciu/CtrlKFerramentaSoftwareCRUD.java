package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cgt.AplCadastrarKFerramentaSoftware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	public AplCRUD<KFerramentaSoftware> definirAplCRUD() {
		return aplCadastrarFerramenta;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKFerramentaSoftware();
		
	}


	@Override
	public KFerramentaSoftware factoryObjetoDados() {
		return new KFerramentaSoftware();
	}

	@Override
	public FormDadosKFerramentaSoftware definirFormularioCadastro() {
		return new FormDadosKFerramentaSoftware();
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
