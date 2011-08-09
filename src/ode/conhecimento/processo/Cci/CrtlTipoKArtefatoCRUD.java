package ode.conhecimento.processo.Cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.conhecimento.processo.Cgt.AplCadastrarTipoKArtefato;
import ode.conhecimento.processo.Cih.FormDadosTipoKArtefato;
import ode.conhecimento.processo.Cih.PainelCrudTipoKArtefato;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

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
	public NucleoAplCadastroBase<TipoKArtefato> definirNucleoAplCadastroBase() {
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
