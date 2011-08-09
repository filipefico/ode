package ode.conhecimento.organizacao.Cci;


import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.Cgt.AplCadastrarKDominioConhecimento;
import ode.conhecimento.organizacao.Cih.FormDadosKDominioConhecimento;
import ode.conhecimento.organizacao.Cih.PainelCrudKDominioConhecimento;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlKDominioConhecimentoCRUD extends CtrlCRUD<KDominioConhecimento> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void iniciar() {
		super.iniciar();
		
	}
	

	
	@Autowired
	AplCadastrarKDominioConhecimento aplCadastrarKDominioConhecimento;

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return aplCadastrarKDominioConhecimento;
	}

	public AplCadastrarKDominioConhecimento getAplCadastrarKDominioConhecimento() {
		return aplCadastrarKDominioConhecimento;
	}

	public void setAplCadastrarKDominioConhecimento(
			AplCadastrarKDominioConhecimento aplCadastrarKDominioConhecimento) {
		this.aplCadastrarKDominioConhecimento = aplCadastrarKDominioConhecimento;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKDominioConhecimento();
		
	}


	@Override
	public KDominioConhecimento factoryObjetoDados() {
		return new KDominioConhecimento();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKDominioConhecimento();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Domínio de Conhecimento";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Domínios de Conhecimento";
	}
}
