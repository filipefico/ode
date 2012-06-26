package ode.problema.ciu;


import java.util.Collection;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KSolucao;
import ode.problema.cgt.AplCadastrarKCausa;
import ode.problema.cgt.AplCadastrarKProblema;
import ode.problema.cgt.AplCadastrarKSolucao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller (CtrlKSolucaoCRUD.Nome)
public class CtrlKSolucaoCRUD extends CtrlCRUD<KSolucao> {
	
private static final long serialVersionUID = -7816845659569342303L;
public static final String Nome = "CtrlCRUDKSolucao";	
@Autowired
AplCadastrarKSolucao aplCadastrarKSolucao;

@Autowired
private AplCadastrarKCausa aplCadastrarKCausa;


private String titulo = "CadastroSolucao";
	
	
	@Override
	public String definirTituloJanelaDados() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public AplCRUD<KSolucao> definirAplCRUD() {
		// TODO Auto-generated method stub
		return aplCadastrarKSolucao ;
	}

	@Override
	public PainelCRUD<KSolucao> definirPainelCRUD() {
		// TODO Auto-generated method stub
		return new PainelCrudKSolucao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KSolucao> definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return new FormDadosKSolucao();
	}

	@Override
	public KSolucao factoryObjetoDados() {
		// TODO Auto-generated method stub
		return new KSolucao();
	}
	
	public AplCadastrarKCausa getAplCadastrarKCausa() {
		return aplCadastrarKCausa;
	}

	public void setAplCadastrarKCausa(
			AplCadastrarKCausa aplCadastrarKCausa) {
		this.aplCadastrarKCausa = aplCadastrarKCausa;
	}

	public Collection<KCausa> listarKCausa() {
		return getAplCadastrarKCausa().recuperarTodos();
	}

	public AplCadastrarKSolucao getAplCadastrarKSolucao() {
		return aplCadastrarKSolucao;
	}

}
