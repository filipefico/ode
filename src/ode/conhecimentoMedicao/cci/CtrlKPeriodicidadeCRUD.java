package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimentoMedicao.cdp.KPeriodicidade;
import ode.conhecimentoMedicao.cgt.AplCadastrarKPeriodicidade;
import ode.conhecimentoMedicao.cih.FormDadosKPeriodicidade;
import ode.conhecimentoMedicao.cih.PainelCRUDKPeriodicidade;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKPeriodicidadeCRUD.NOME)
public class CtrlKPeriodicidadeCRUD extends CtrlCRUD<KPeriodicidade> {

	public static final String NOME = "CtrlKPeriodicidadeCRUD";
	
	@Autowired
	AplCadastrarKPeriodicidade apl;
	
	private final String tituloJanelaDados = "Periodicidade";
	
	private final String janelaPrincipal = "Periodicidade";
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KPeriodicidade> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KPeriodicidade> definirPainelCRUD() {
		return new PainelCRUDKPeriodicidade();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return janelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KPeriodicidade> definirFormularioCadastro() {
		return new FormDadosKPeriodicidade();
	}

	@Override
	public KPeriodicidade factoryObjetoDados() {
		return new KPeriodicidade();
	}

}
