package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class CtrlKProcessoCRUD extends CtrlCRUD<KProcesso> {
	@Override
	public void iniciar() {
		super.iniciar();
		
	}
	
	@Autowired
	AplCadastrarKProcesso aplCadastrarKProcesso;
	//lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<KProcesso> definirAplCRUD() {
		return aplCadastrarKProcesso;
	}

	public AplCadastrarKProcesso getAplCadastrarKProcesso() {
		return aplCadastrarKProcesso;
	}

	public void setAplCadastrarKProcesso(AplCadastrarKProcesso aplCadastrarKProcesso) {
		this.aplCadastrarKProcesso = aplCadastrarKProcesso;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKProcesso();
		
	}


	@Override
	public KProcesso factoryObjetoDados() {
		return new KProcesso();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKProcesso();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Processo";
	}
	
	@Override
	public String definirTituloJanelaPrincipal() {
		return "Processos";
	}
}
