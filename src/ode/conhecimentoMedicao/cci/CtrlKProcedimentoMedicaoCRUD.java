package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimentoMedicao.cdp.KProcedimentoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKProcedimentoMedicao;
import ode.conhecimentoMedicao.cih.FormDadosKProcedimentoMedicao;
import ode.conhecimentoMedicao.cih.PainelCRUDKProcedimentoMedicao;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKProcedimentoMedicaoCRUD.NOME)
public class CtrlKProcedimentoMedicaoCRUD extends CtrlCRUD<KProcedimentoMedicao>{

	public static final String NOME = "CtrlKProcedimentoMedicao";
	
	private final String tituloJanelaDados = "Procedimento de Medicao";
	private final String tituloJanelaPrincipal = "Procedimento de Medicao";
	
	@Autowired
	AplCadastrarKProcedimentoMedicao apl;
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KProcedimentoMedicao> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KProcedimentoMedicao> definirPainelCRUD() {
		return new PainelCRUDKProcedimentoMedicao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return tituloJanelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KProcedimentoMedicao> definirFormularioCadastro() {
		return new FormDadosKProcedimentoMedicao();
	}

	@Override
	public KProcedimentoMedicao factoryObjetoDados() {
		return new KProcedimentoMedicao();
	}

}
