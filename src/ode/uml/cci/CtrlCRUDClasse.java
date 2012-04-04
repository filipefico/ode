package ode.uml.cci;

import java.util.Collection;

import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cih.FormDadosClasse;
import ode.uml.cih.PainelCrudClasse;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;
import ode.uml.cgt.AplCadastrarClasse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlCRUDClasse extends CtrlCRUD<Classe> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarClasse aplCadastrarClasse;

	@Override
	public String definirTituloJanelaDados() {
		return "Classe";
	}

	@Override
	public PainelCRUD<Classe> definirPainelCRUD() {
		return new PainelCrudClasse();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Classes";
	}

	@Override
	public FormularioDadosCRUD<Classe> definirFormularioCadastro() {
		return new FormDadosClasse();
	}

	@Override
	public Classe factoryObjetoDados() {
		return new Classe();
	}

	@Override
	public AplCRUD<Classe> definirAplCRUD() {
		return aplCadastrarClasse;
	}

	@Override
	public void atualizarPesquisa() {
		Collection<Classe> objetos = aplCadastrarClasse.obterPorProjeto(NucleoContexto.recuperarProjeto());		
		painelCRUD.getListagem().atualizar(objetos);
	}
	
	public Collection<Classe> obterPorPacote (Pacote pacote){
		return aplCadastrarClasse.obterPorPacote(pacote);
	}
	
	public Collection<Classe> obterPorProjeto (Projeto projeto){
		return aplCadastrarClasse.obterPorProjeto(projeto);
	}

}