package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimentoMedicao.cdp.KProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMetodoAnalitico;
import ode.conhecimentoMedicao.cgt.AplCadastrarKProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cih.FormDadosKProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cih.PainelCRUDKProcedimentoAnaliseMedicao;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKProcedimentoAnaliseMedicaoCRUD.NOME)
public class CtrlKProcedimentoAnaliseMedicaoCRUD extends CtrlCRUD<KProcedimentoAnaliseMedicao>{

	public static final String NOME = "KProcedimentoAnaliseMedicao";
	
	private final String tituloJanelaDados = "Procedimento de Analise de Medicao";
	private final String tituloJanelaPrincipal = "Procedimento de Analise de Medicao";
	
	@Autowired
	AplCadastrarKProcedimentoAnaliseMedicao apl;
	
	@Autowired
	AplCadastrarKMetodoAnalitico aplCadastrarKMetodoAnalitico;
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KProcedimentoAnaliseMedicao> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KProcedimentoAnaliseMedicao> definirPainelCRUD() {
		return new PainelCRUDKProcedimentoAnaliseMedicao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return tituloJanelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KProcedimentoAnaliseMedicao> definirFormularioCadastro() {
		return new FormDadosKProcedimentoAnaliseMedicao();
	}

	@Override
	public KProcedimentoAnaliseMedicao factoryObjetoDados() {
		return new KProcedimentoAnaliseMedicao();
	}

	public AplCadastrarKMetodoAnalitico getAplKMetodoAnalitico() {
		return aplCadastrarKMetodoAnalitico;
	}

}
