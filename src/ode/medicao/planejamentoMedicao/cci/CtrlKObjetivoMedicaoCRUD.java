package ode.medicao.planejamentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Messagebox;

import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cih.FormDadosKObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cih.PainelCRUDKObjetivoMedicao;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode.conhecimentoMedicao.cci.EventListenerParaControlador;

@Controller(CtrlKObjetivoMedicaoCRUD.NOME)
public class CtrlKObjetivoMedicaoCRUD extends CtrlCRUD<KObjetivoMedicao>{

	public static final String NOME = "CtrlKObjetivoMedicaoCRUD";
	
	@Autowired
	AplCadastrarKObjetivoSoftware aplCadastrarKObjetivoSoftware;
	@Autowired
	AplCadastrarKObjetivoEstrategico aplCadastrarKObjetivoEstrategico;
	@Autowired
	AplCadastrarKObjetivoMedicao aplCadastrarKObjetivoMedicao;
	
	private String tituloCadastro = "Cadastrar Objetivo Medicão";
	private String titulo = "Objetivos de Medicão";
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloCadastro;
	}

	@Override
	public AplCRUD<KObjetivoMedicao> definirAplCRUD() {
		return aplCadastrarKObjetivoMedicao;
	}
	
	public AplCadastrarKObjetivoEstrategico getAplKObjetivoEstrategico(){
		return aplCadastrarKObjetivoEstrategico;
	}
	
	public AplCadastrarKObjetivoSoftware getAplKObjetivoSoftware(){
		return aplCadastrarKObjetivoSoftware;
	}

	@Override
	public PainelCRUD<KObjetivoMedicao> definirPainelCRUD() {
		return new PainelCRUDKObjetivoMedicao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KObjetivoMedicao> definirFormularioCadastro() {
		return new FormDadosKObjetivoMedicao();
	}

	@Override
	public KObjetivoMedicao factoryObjetoDados() {
		return new KObjetivoMedicao();
	}

	@Override
	public void acaoSalvar() {
		formularioDados.atualizarObjeto();
		KObjetivoMedicao objetoCadastro = formularioDados
				.getObjetoCadastroDados();
		if (!objetoCadastro.isPersistente()) {
			super.acaoSalvar();
			return;
		}
		if (aplCadastrarKObjetivoMedicao
				.RelacionamentoNecessidadeInformacao(objetoCadastro)) {
			try {
				Messagebox
						.show(
								NucleoMensagens.getMensagem(NucleoMensagens.MSG_AVISO_NECESSIDADE_INFORMACAO),NucleoMensagens
								.getMensagem(NucleoMensagens.TERMO_ALTERAR)
								+ NucleoMensagens
										.getMensagem(NucleoMensagens.TERMO_OBJETIVO_MEDICAO), Messagebox.YES | Messagebox.NO,
								Messagebox.QUESTION,
								new EventListenerParaControlador(this) {

									@Override
									public void onEvent(Event e)
											throws Exception {
										switch ((Integer) e.getData()) {
										case Messagebox.YES:
											if(/*usuario==adminstrador*/false){
												Messagebox.show(NucleoMensagens.getMensagem(NucleoMensagens.MSG_SOMENTE_ADMINISTRADOR));
												return;
											}
											((CtrlKObjetivoMedicaoCRUD) this
													.getCtrl())
													.recallAcaoSalvar();
											break;
										case Messagebox.NO:

											break;
										default:

											break;
										}
									}
								});
			} catch (InterruptedException ev) {

			}
		}else{
			super.acaoSalvar();
		}
	}

	public void recallAcaoSalvar() {
		super.acaoSalvar();
	}

	
}
