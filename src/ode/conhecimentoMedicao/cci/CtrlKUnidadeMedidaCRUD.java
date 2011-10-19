package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimentoMedicao.cdp.KUnidadeMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKUnidadeMedida;
import ode.conhecimentoMedicao.cih.FormDadosKUnidadeMedida;
import ode.conhecimentoMedicao.cih.PainelCRUDKUnidadeMedida;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKUnidadeMedidaCRUD.NOME)
public class CtrlKUnidadeMedidaCRUD extends CtrlCRUD<KUnidadeMedida> {

	public static final String NOME = "CtrlKUnidadeMedidaCRUD";
	
	@Autowired
	AplCadastrarKUnidadeMedida apl;
	
	private final String tituloJanelaDados = "Unidade de Medida";
	
	private final String janelaPrincipal = "Unidade de Medida";
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KUnidadeMedida> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KUnidadeMedida> definirPainelCRUD() {
		return new PainelCRUDKUnidadeMedida();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return janelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KUnidadeMedida> definirFormularioCadastro() {
		return new FormDadosKUnidadeMedida();
	}

	@Override
	public KUnidadeMedida factoryObjetoDados() {
		return new KUnidadeMedida();
	}

}
