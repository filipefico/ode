package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cgt.AplCadastrarKNecessidadeInformacao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoMedicao;
import ode.conhecimentoMedicao.cih.FormDadosKNecessidadeInformacao;
import ode.conhecimentoMedicao.cih.PainelCRUDKNecessidadeInformacao;
import ode._infraestruturaCRUD.ciu.*;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;

@Controller(CtrlKNecessidadeInformacaoCRUD.NOME)
public class CtrlKNecessidadeInformacaoCRUD extends
		CtrlCRUD<KNecessidadeInformacao> {

	public static final String NOME = "CtrlKNecessidadeInformacaoCRUD";

	private String titulo = "Necessidade de Informacao";
	private String tituloDeDados = "Necessidade de Informacao";

	@Autowired
	AplCadastrarKNecessidadeInformacao aplCadastrarKNecessidadeInformacao;

	@Autowired
	AplCadastrarKObjetivoMedicao aplCadastrarKObjetivoMedicao;

	@Autowired
	AplCadastrarKProcesso aplCadastrarKProcesso;

	@Override
	public String definirTituloJanelaDados() {
		return tituloDeDados;
	}

	@Override
	public AplCRUD<KNecessidadeInformacao> definirAplCRUD() {
		return aplCadastrarKNecessidadeInformacao;
	}

	@Override
	public PainelCRUD<KNecessidadeInformacao> definirPainelCRUD() {
		return new PainelCRUDKNecessidadeInformacao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KNecessidadeInformacao> definirFormularioCadastro() {
		return new FormDadosKNecessidadeInformacao();
	}

	@Override
	public KNecessidadeInformacao factoryObjetoDados() {
		return new KNecessidadeInformacao();
	}

	public AplCadastrarKObjetivoMedicao getAplKObjetivoMedicao() {
		return aplCadastrarKObjetivoMedicao;
	}

	public AplCadastrarKProcesso getAplKProcesso() {
		return aplCadastrarKProcesso;
	}

	@Override
	public void acaoSalvar(){
		formularioDados.atualizarObjeto();
		KNecessidadeInformacao objetoCadastro = formularioDados
				.getObjetoCadastroDados();
		if (!objetoCadastro.isPersistente()) {
			super.acaoSalvar();
			return;
		}
		if(aplCadastrarKNecessidadeInformacao.estaRelacionadoComMedida(objetoCadastro)){
			new SimNaoAlterarHelper<CtrlKNecessidadeInformacaoCRUD>(this, NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NECESSIDADE_INFORMACAO), NucleoMensagens.getMensagem(NucleoMensagens.TERMO_MEDIDA), new EventListener() {
		
				@Override
				public void onEvent(Event arg0) throws Exception {
					callbackAcaoSalvar();
					
				}
			});
		}else{
			super.acaoSalvar();
		}
	}

	public void callbackAcaoSalvar() {
		super.acaoSalvar();
	}

}
