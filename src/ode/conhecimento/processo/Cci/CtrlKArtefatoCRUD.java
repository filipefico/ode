package ode.conhecimento.processo.Cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cgt.AplCadastrarKArtefato;
import ode.conhecimento.processo.Cih.FormDadosKArtefato;
import ode.conhecimento.processo.Cih.PainelCrudKArtefato;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;
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
	public AplBase<KArtefato> definirNucleoAplCadastroBase() {
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
