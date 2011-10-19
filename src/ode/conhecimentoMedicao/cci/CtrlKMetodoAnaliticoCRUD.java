package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimentoMedicao.cdp.KMetodoAnalitico;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMetodoAnalitico;
import ode.conhecimentoMedicao.cih.FormDadosKMetodoAnalitico;
import ode.conhecimentoMedicao.cih.PainelCRUDKMetodoAnalitico;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKMetodoAnaliticoCRUD.NOME)
public class CtrlKMetodoAnaliticoCRUD extends CtrlCRUD<KMetodoAnalitico>{

	public static final String NOME = "CtrlKMetodoAnaliticoCRUD";
	
	private final String tituloJanelaDados = "Metodo Analitico";
	private final String tituloJanelaPrincipal = "Metodo Analitico";
	
	@Autowired
	AplCadastrarKMetodoAnalitico apl;
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KMetodoAnalitico> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KMetodoAnalitico> definirPainelCRUD() {
		return new PainelCRUDKMetodoAnalitico();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return tituloJanelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KMetodoAnalitico> definirFormularioCadastro() {
		return new FormDadosKMetodoAnalitico();
	}

	@Override
	public KMetodoAnalitico factoryObjetoDados() {
		return new KMetodoAnalitico();
	}

}
