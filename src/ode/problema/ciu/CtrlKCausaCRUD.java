package ode.problema.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import ode.problema.cgt.AplCadastrarKCausa;
import ode.problema.cgt.AplCadastrarKProblema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;




@Controller (CtrlKCausaCRUD.Nome)
public class CtrlKCausaCRUD extends CtrlCRUD<KCausa> {

	
	private static final long serialVersionUID = 4673163324906410248L;
	public static final String Nome = "CtrlCRUDKCausa";	
	@Autowired
	private AplCadastrarKCausa aplCadastrarKCausa;
	@Autowired
	private AplCadastrarKProblema aplCadastrarKProblema;

	private String titulo = "CadastroCausa";

	@Override
	public String definirTituloJanelaDados() {
		// TODO Auto-generated method stub
		return titulo;
	}

   @Override
        public AplCRUD<KCausa> definirAplCRUD() {
                return aplCadastrarKCausa;
        }


	@Override
	public PainelCRUD<KCausa> definirPainelCRUD() {
		// TODO Auto-generated method stub
		return new PainelCrudKCausa();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KCausa> definirFormularioCadastro() {
		// TODO Auto-generated method stub
		return new FormDadosKCausa();
	}

	@Override
	public KCausa factoryObjetoDados() {
		// TODO Auto-generated method stub
		return new KCausa();
	}
	
	public AplCadastrarKProblema getAplCadastrarKProblema() {
		return aplCadastrarKProblema;
	}

	public void setAplCadastrarKProblema(
			AplCadastrarKProblema aplCadastrarKProblema) {
		this.aplCadastrarKProblema= aplCadastrarKProblema;
	}
	
	public Collection<KProblema> listarKProblema() {
		return getAplCadastrarKProblema().recuperarTodos();
	}


	
	
}

