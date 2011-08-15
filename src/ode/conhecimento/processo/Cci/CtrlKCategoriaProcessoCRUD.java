package ode.conhecimento.processo.Cci;

import ode.conhecimento.processo.Cdp.KCategoriaProcesso;
import ode.conhecimento.processo.Cgt.AplCadastrarKCategoriaProcesso;
import ode.conhecimento.processo.Cih.FormDadosCategoriaProcesso;
import ode.conhecimento.processo.Cih.PainelCrudCategoriaProcesso;
import ode.nucleo.crud.cci.CtrlCRUD;
import ode.nucleo.crud.cgt.AplBase;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.PainelCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zkplus.spring.SpringUtil;

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
	public AplBase definirNucleoAplCadastroBase() {
		return aplCadastrarKCategoriaProcesso;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudCategoriaProcesso();
		
	}


	@Override
	public KCategoriaProcesso factoryObjetoDados() {
		return new KCategoriaProcesso();
	}


	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosCategoriaProcesso();
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
