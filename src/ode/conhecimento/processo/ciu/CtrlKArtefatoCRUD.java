package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cgt.AplCadastrarKArtefato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class CtrlKArtefatoCRUD extends CtrlCRUD<KArtefato> {

	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Override
	public FormularioDadosCRUD<KArtefato> definirFormularioCadastro() {
		return new FormDadosKArtefato();
	}

	@Autowired
	AplCadastrarKArtefato aplCadastrarKArtefato;
	
	public AplCadastrarKArtefato getAplCadastrarKArtefato() {
		return aplCadastrarKArtefato;
	}

	public void setAplCadastrarKArtefato(AplCadastrarKArtefato aplCadastrarKArtefato) {
		this.aplCadastrarKArtefato = aplCadastrarKArtefato;
	}

	@Override
	public AplCRUD<KArtefato> definirAplCRUD() {
		return aplCadastrarKArtefato;
	}

	@Override
	public PainelCRUD<KArtefato> definirPainelCRUD() {
		return new PainelCrudKArtefato();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Artefato";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Artefatos";
	}

	@Override
	public KArtefato factoryObjetoDados() {
		return new KArtefato();
	}

}
