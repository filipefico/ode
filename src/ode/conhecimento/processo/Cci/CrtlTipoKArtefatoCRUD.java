package ode.conhecimento.processo.Cci;

import org.zkoss.zkplus.spring.SpringUtil;

import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.conhecimento.processo.Cgt.AplCadastrarTipoKArtefato;
import ode.conhecimento.processo.Cih.FormDadosTipoKArtefato;
import ode.conhecimento.processo.Cih.PainelCrudTipoKArtefato;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

public class CrtlTipoKArtefatoCRUD extends CtrlCRUD<TipoKArtefato> {

	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Override
	public FormularioDadosCRUD<TipoKArtefato> definirFormularioCadastro() {
		return new FormDadosTipoKArtefato();
	}

	@Override
	public NucleoAplCadastroBase<TipoKArtefato> definirNucleoAplCadastroBase() {
		return (AplCadastrarTipoKArtefato) SpringUtil
				.getBean("aplCadastrarTipoKArtefato"); 
	}

	@Override
	public PainelCRUD<TipoKArtefato> definirPainelCRUD() {
		return new PainelCrudTipoKArtefato();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Tipo Artefato";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Tipo de artefato";
	}

	@Override
	public TipoKArtefato factoryObjetoDados() {
		return new TipoKArtefato();
	}

}
