package ode.conhecimento.organizacao.ciu;


import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.organizacao.cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.cgt.AplCadastrarKDominioConhecimento;

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
	public AplCRUD<KDominioConhecimento> definirAplCRUD() {
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
