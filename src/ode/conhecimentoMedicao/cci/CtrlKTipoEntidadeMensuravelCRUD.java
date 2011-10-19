package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cih.FormDadosKTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cih.PainelCRUDKTipoEntidadeMensuravel;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKTipoEntidadeMensuravelCRUD.NOME)
public class CtrlKTipoEntidadeMensuravelCRUD extends CtrlCRUD<KTipoEntidadeMensuravel>{

	@Autowired
	AplCadastrarKTipoEntidadeMensuravel aplCadastrarKTipoEntidadeMensuravel;
	
	public static final String NOME = "CtrlKTipoEntidadeMensuravelCRUD";
	
	private String janelaDados = "Entidade Mensuravel";
	
	private String janelaPrincipal = "Entidade Mensuravel";
	
	@Override
	public String definirTituloJanelaDados() {
		return janelaDados;
	}

	@Override
	public AplCRUD<KTipoEntidadeMensuravel> definirAplCRUD() {
		return aplCadastrarKTipoEntidadeMensuravel;
	}

	@Override
	public PainelCRUD<KTipoEntidadeMensuravel> definirPainelCRUD() {
		return new PainelCRUDKTipoEntidadeMensuravel();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return janelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KTipoEntidadeMensuravel> definirFormularioCadastro() {
		return new FormDadosKTipoEntidadeMensuravel();
	}

	@Override
	public KTipoEntidadeMensuravel factoryObjetoDados() {
		return new KTipoEntidadeMensuravel();
	}
	
}
