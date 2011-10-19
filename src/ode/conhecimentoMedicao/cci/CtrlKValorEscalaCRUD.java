package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode.conhecimentoMedicao.cgt.AplCadastrarKValorEscala;
import ode.conhecimentoMedicao.cih.FormDadosKValorEscala;
import ode.conhecimentoMedicao.cih.PainelCRUDKValorEscala;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;


@Controller(CtrlKValorEscalaCRUD.NOME)
public class CtrlKValorEscalaCRUD extends CtrlCRUD<KValorEscala>{

	@Autowired
	AplCadastrarKValorEscala apl;
	
	public static final String NOME = "CtrlKValorEscalaCRUD";
	
	private String tituloJanelaDados = "Cadastrar Valor de Escala";
	private String tituloJanelaPrincipal = "Cadastrar Valor de Escala";
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KValorEscala> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KValorEscala> definirPainelCRUD() {
		return new PainelCRUDKValorEscala();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return tituloJanelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KValorEscala> definirFormularioCadastro() {
		return new FormDadosKValorEscala();
	}

	@Override
	public KValorEscala factoryObjetoDados() {
		return new KValorEscala();
	}

}
