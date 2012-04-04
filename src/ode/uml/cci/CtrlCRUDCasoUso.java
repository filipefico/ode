package ode.uml.cci;


import java.util.Collection;

import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cih.FormDadosCasoUso;
import ode.uml.cih.PainelCrudCasoUso;
import ode.uml.cdp.CasoUso;
import ode.uml.cgt.AplCadastrarCasoUso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlCRUDCasoUso extends CtrlCRUD<CasoUso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarCasoUso aplCadastrarCasoUso;

	@Override
	public String definirTituloJanelaDados() {
		return "Caso de Uso";
	}

	@Override
	public PainelCRUD<CasoUso> definirPainelCRUD() {
		return new PainelCrudCasoUso();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Casos de Uso";
	}

	@Override
	public FormularioDadosCRUD<CasoUso> definirFormularioCadastro() {
		return new FormDadosCasoUso();
	}

	@Override
	public CasoUso factoryObjetoDados() {
		return new CasoUso();
	}

	@Override
	public AplCRUD<CasoUso> definirAplCRUD() {
		return aplCadastrarCasoUso;
	}

	@Override
	public void atualizarPesquisa() {
		Collection<CasoUso> objetos = aplCadastrarCasoUso.obterPorProjeto(NucleoContexto.recuperarProjeto());		
		painelCRUD.getListagem().atualizar(objetos);
	}
	
	public Collection<CasoUso> obterPorProjeto (Projeto p){
		return aplCadastrarCasoUso.obterPorProjeto(p);
	}

}