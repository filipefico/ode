package ode.gerenciaConhecimento.ciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cgt.AplCadastrarTema;

@Controller
public class CtrlTema extends CtrlCRUD<Tema> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	AplCadastrarTema aplCadastrarTema;
	
	@Override
	public String definirTituloJanelaDados() {
		// TODO Auto-generated method stub
		return "Novo/Consulta";
	}

	@Override
	public AplCRUD<Tema> definirAplCRUD() {
		// TODO Auto-generated method stub
		return aplCadastrarTema;
	}

	@Override
	public PainelCRUD<Tema> definirPainelCRUD() {
		// TODO Auto-generated method stub
		return new PainelCRUDTema();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		// TODO Auto-generated method stub
		return "Tema";
	}

	@Override
	public FormularioDadosCRUD<Tema> definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return new FormDadosTema();
	}

	@Override
	public Tema factoryObjetoDados() {
		// TODO Auto-generated method stub
		return new Tema();
	}

}
