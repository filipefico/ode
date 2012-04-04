package ode.uml.cci;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.util.NucleoContexto;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleProjeto.cdp.Projeto;
import ode.uml.cdp.Pacote;
import ode.uml.cgt.AplCadastrarPacote;
import ode.uml.cih.FormDadosPacote;
import ode.uml.cih.PainelCrudPacote;

@Controller
public class CtrlCRUDPacote extends CtrlCRUD<Pacote> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarPacote aplCadastrarPacote;

	public AplCadastrarPacote getAplCadastrarPacote() {
		return aplCadastrarPacote;
	}

	public void setAplCadastrarPacote(AplCadastrarPacote aplCadastrarPacote) {
		this.aplCadastrarPacote = aplCadastrarPacote;
	}
	
	@Override
	public String definirTituloJanelaDados() {
		return "Pacote";
	}

	@Override
	public PainelCRUD<Pacote> definirPainelCRUD() {
		return new PainelCrudPacote();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Pacotes";
	}

	@Override
	public FormularioDadosCRUD<Pacote> definirFormularioCadastro() {
		return new FormDadosPacote();
	}

	@Override
	public Pacote factoryObjetoDados() {
		return new Pacote();
	}

	@Override
	public AplCRUD<Pacote> definirAplCRUD() {
		return aplCadastrarPacote;
	}
	
	public Collection<Pacote> obterPorProjeto(Projeto p){
		return aplCadastrarPacote.obterPacotesPorProjeto(p);
	}

	@Override
	public void atualizarPesquisa() {
		Collection<Pacote> objetos = aplCadastrarPacote.obterPacotesPorProjeto(NucleoContexto.recuperarProjeto());		
		painelCRUD.getListagem().atualizar(objetos);
	}

	
}