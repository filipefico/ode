package ode.medicao.planejamentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimentoMedicao.cci.SimNaoAlterarHelper;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cih.FormDadosNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cih.PainelCRUDNecessidadeInformacao;
import ode._infraestruturaCRUD.ciu.*;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;

@Controller(CtrlNecessidadeInformacaoCRUD.NOME)
public class CtrlNecessidadeInformacaoCRUD extends
		CtrlCRUD<NecessidadeInformacao> {

	public static final String NOME = "CtrlKNecessidadeInformacaoCRUD";

	private String titulo = "Necessidade de Informacao";
	private String tituloDeDados = "Necessidade de Informacao";

	@Autowired
	AplCadastrarNecessidadeInformacao aplCadastrarKNecessidadeInformacao;

	@Autowired
	AplCadastrarObjetivoMedicao aplCadastrarKObjetivoMedicao;

	@Autowired
	AplCadastrarKProcesso aplCadastrarKProcesso;

	@Override
	public String definirTituloJanelaDados() {
		return tituloDeDados;
	}

	@Override
	public AplCRUD<NecessidadeInformacao> definirAplCRUD() {
		return aplCadastrarKNecessidadeInformacao;
	}

	@Override
	public PainelCRUD<NecessidadeInformacao> definirPainelCRUD() {
		return new PainelCRUDNecessidadeInformacao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<NecessidadeInformacao> definirFormularioCadastro() {
		return new FormDadosNecessidadeInformacao();
	}

	@Override
	public NecessidadeInformacao factoryObjetoDados() {
		return new NecessidadeInformacao();
	}

	public AplCadastrarObjetivoMedicao getAplKObjetivoMedicao() {
		return aplCadastrarKObjetivoMedicao;
	}

	public AplCadastrarKProcesso getAplKProcesso() {
		return aplCadastrarKProcesso;
	}

	@Override
	public void acaoSalvar(){
		
			super.acaoSalvar();
	}

	public void callbackAcaoSalvar() {
		super.acaoSalvar();
	}

}
