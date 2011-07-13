package ode.conhecimento.organizacao.Cci;

import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.Cgt.AplCadastrarKDominioConhecimento;
import ode.conhecimento.organizacao.Cih.FormDadosDominioConhecimento;
import ode.conhecimento.organizacao.Cih.PainelCrudDominioConhecimento;
import ode.nucleo.cgt.NucleoAplCadastroBase;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.zkoss.zkplus.spring.SpringUtil;

public class CtrlDominioConhecimentoCRUD extends CtrlCRUD<KDominioConhecimento> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void iniciar() {
		super.iniciar();
		
	}

	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public NucleoAplCadastroBase definirNucleoAplCadastroBase() {
		return (AplCadastrarKDominioConhecimento) SpringUtil
				.getBean("aplCadastrarKDominioConhecimento");
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudDominioConhecimento();
		
	}


	@Override
	public KDominioConhecimento factoryObjetoDados() {
		return new KDominioConhecimento();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosDominioConhecimento();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Domínio de Conhecimento";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Cadastro de Domínio de Conhecimento";
	}
}
