package ode.conhecimento.processo.ciu;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cdp.KProcedimento;
import ode.conhecimento.processo.cgt.AplCadastrarKProcedimento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlKProcedimentoCRUD extends CtrlCRUD<KProcedimento> {
	@Override
	public void iniciar() {
		super.iniciar();
	}

	@Autowired
	AplCadastrarKProcedimento aplCadastrarKProcedimento;
	

	public AplCadastrarKProcedimento getAplCadastrarKProcedimento() {
		return aplCadastrarKProcedimento;
	}

	public void setAplCadastrarKProcedimento(
			AplCadastrarKProcedimento aplCadastrarKProcedimento) {
		this.aplCadastrarKProcedimento = aplCadastrarKProcedimento;
	}

	// lembrar que o controlador eh melhor injetado pelo spring
	@Override
	public AplCRUD<KProcedimento> definirAplCRUD() {
		return aplCadastrarKProcedimento;
	}

	@Override
	public PainelCRUD definirPainelCRUD() {
		return new PainelCrudKProcedimento();

	}

	@Override
	public KProcedimento factoryObjetoDados() {
		return new KProcedimento();
	}

	@Override
	public FormularioDadosCRUD definirFormularioCadastro() {
		return new FormDadosKProcedimento();
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Procedimento";
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Procedimentos";
	}

}
