package ode.gerenciaRequisitos.cci;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.gerenciaRequisitos.cgt.AplCadastrarRequisito;
import ode.gerenciaRequisitos.cih.FormDadosRequisito;
import ode.gerenciaRequisitos.cih.PainelCrudRequisito;

@Controller
public class CtrlCRUDRequisito extends CtrlCRUD<Requisito>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarRequisito aplCadastrarRequisito;

	@Override
	public String definirTituloJanelaDados() {
		return "Requisito";
	}

	@Override
	public PainelCRUD<Requisito> definirPainelCRUD() {
		return new PainelCrudRequisito();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Requisitos";
	}

	@Override
	public FormularioDadosCRUD<Requisito> definirFormularioCadastro() {
		return new FormDadosRequisito();
	}

	@Override
	public Requisito factoryObjetoDados() {
		return new Requisito();
	}

	@Override
	public AplCRUD<Requisito> definirAplCRUD() {
		return aplCadastrarRequisito;
	}
	
	public Collection<Requisito> obterPorProjeto (Projeto p){
		return aplCadastrarRequisito.obterPorProjeto(p);
	}
	
	public Requisito obterPorId(long id){
		return aplCadastrarRequisito.recuperarPorId(id);
	}

	@Override
	public void atualizarPesquisa() {
		Collection<Requisito> objetos = aplCadastrarRequisito.obterPorProjeto(NucleoContexto.recuperarProjeto());		
		painelCRUD.getListagem().atualizar(objetos);
	}
}
