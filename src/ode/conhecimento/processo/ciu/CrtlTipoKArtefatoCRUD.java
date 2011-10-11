package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.TipoKArtefato;
import ode.conhecimento.processo.cgt.AplCadastrarTipoKArtefato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CrtlTipoKArtefatoCRUD extends CtrlCRUD<TipoKArtefato> {

	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Override
	public FormularioDadosCRUD<TipoKArtefato> definirFormularioCadastro() {
		return new FormDadosTipoKArtefato();
	}

	@Autowired
	AplCadastrarTipoKArtefato aplCadastrarTipoKArtefato;

	public AplCadastrarTipoKArtefato getAplCadastrarTipoKArtefato() {
		return aplCadastrarTipoKArtefato;
	}

	public void setAplCadastrarTipoKArtefato(
			AplCadastrarTipoKArtefato aplCadastrarTipoKArtefato) {
		this.aplCadastrarTipoKArtefato = aplCadastrarTipoKArtefato;
	}

	@Override
	public AplCRUD<TipoKArtefato> definirAplCRUD() {
		return aplCadastrarTipoKArtefato;
	}

	@Override
	public PainelCRUD<TipoKArtefato> definirPainelCRUD() {
		return new PainelCrudTipoKArtefato();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Tipo de Artefato";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Tipos de Artefato";
	}

	@Override
	public TipoKArtefato factoryObjetoDados() {
		return new TipoKArtefato();
	}

}
