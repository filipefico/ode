package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KCategoriaProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKCategoriaProcesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlKCategoriaProcessoCRUD extends CtrlCRUD<KCategoriaProcesso> {
	@Override
	public void iniciar() {
		super.iniciar();
		
	}
	@Autowired
	private AplCadastrarKCategoriaProcesso aplCadastrarKCategoriaProcesso;
	
	public AplCadastrarKCategoriaProcesso getAplCadastrarKCategoriaProcesso() {
		return aplCadastrarKCategoriaProcesso;
	}

	public void setAplCadastrarKCategoriaProcesso(
			AplCadastrarKCategoriaProcesso aplCadastrarKCategoriaProcesso) {
		this.aplCadastrarKCategoriaProcesso = aplCadastrarKCategoriaProcesso;
	}


	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<KCategoriaProcesso> definirAplCRUD() {
		return aplCadastrarKCategoriaProcesso;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKCategoriaProcesso();
		
	}


	@Override
	public KCategoriaProcesso factoryObjetoDados() {
		return new KCategoriaProcesso();
	}


	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKCategoriaProcesso();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Categoria de Processo";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Categorias de Processo";
	}
	
}
