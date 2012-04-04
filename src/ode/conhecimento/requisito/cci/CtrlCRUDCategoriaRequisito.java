package ode.conhecimento.requisito.cci;

import java.util.Collection;

import ode.conhecimento.requisito.cdp.CategoriaRequisito;
import ode.conhecimento.requisito.cdp.TipoRequisito;
import ode.conhecimento.requisito.cgt.AplCadastrarCategoriaRequisito;
import ode.conhecimento.requisito.cih.FormDadosCategoriaRequisito;
import ode.conhecimento.requisito.cih.PainelCrudCategoriaRequisito;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlCRUDCategoriaRequisito extends CtrlCRUD<CategoriaRequisito> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AplCadastrarCategoriaRequisito aplCadastrarCategoriaRequisito;

	@Override
	public String definirTituloJanelaDados() {
		return "Categoria de Requisitos";
	}

	@Override
	public PainelCRUD<CategoriaRequisito> definirPainelCRUD() {
		return new PainelCrudCategoriaRequisito();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Categorias de Requisitos";
	}

	@Override
	public FormularioDadosCRUD<CategoriaRequisito> definirFormularioCadastro() {
		return new FormDadosCategoriaRequisito();
	}

	@Override
	public CategoriaRequisito factoryObjetoDados() {
		return new CategoriaRequisito();
	}

	@Override
	public AplCRUD<CategoriaRequisito> definirAplCRUD() {
		return aplCadastrarCategoriaRequisito;
	}
	
	public Collection<CategoriaRequisito> recuperarTodos(){
		return aplCadastrarCategoriaRequisito.recuperarTodos();
	}
	
	public Collection<CategoriaRequisito> obterCategoriasPorTipo(TipoRequisito tipo){
		return aplCadastrarCategoriaRequisito.obterCategoriasPorTipoRequisito(tipo);
	}

}