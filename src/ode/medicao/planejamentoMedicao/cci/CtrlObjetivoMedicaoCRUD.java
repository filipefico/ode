package ode.medicao.planejamentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Messagebox;

import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cih.FormDadosObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cih.PainelCRUDObjetivoMedicao;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode.conhecimentoMedicao.cci.EventListenerParaControlador;

@Controller(CtrlObjetivoMedicaoCRUD.NOME)
public class CtrlObjetivoMedicaoCRUD extends CtrlCRUD<ObjetivoMedicao>{

	public static final String NOME = "CtrlKObjetivoMedicaoCRUD";
	
	@Autowired
	AplCadastrarObjetivoSoftware aplCadastrarKObjetivoSoftware;
	@Autowired
	AplCadastrarObjetivoEstrategico aplCadastrarKObjetivoEstrategico;
	@Autowired
	AplCadastrarObjetivoMedicao aplCadastrarKObjetivoMedicao;
	
	private String tituloCadastro = "Cadastrar Objetivo Medicão";
	private String titulo = "Objetivos de Medicão";
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloCadastro;
	}

	@Override
	public AplCRUD<ObjetivoMedicao> definirAplCRUD() {
		return aplCadastrarKObjetivoMedicao;
	}
	
	public AplCadastrarObjetivoEstrategico getAplKObjetivoEstrategico(){
		return aplCadastrarKObjetivoEstrategico;
	}
	
	public AplCadastrarObjetivoSoftware getAplKObjetivoSoftware(){
		return aplCadastrarKObjetivoSoftware;
	}

	@Override
	public PainelCRUD<ObjetivoMedicao> definirPainelCRUD() {
		return new PainelCRUDObjetivoMedicao();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<ObjetivoMedicao> definirFormularioCadastro() {
		return new FormDadosObjetivoMedicao();
	}

	@Override
	public ObjetivoMedicao factoryObjetoDados() {
		return new ObjetivoMedicao();
	}

	@Override
	public void acaoSalvar() {
		formularioDados.atualizarObjeto();
		ObjetivoMedicao objetoCadastro = formularioDados
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
											((CtrlObjetivoMedicaoCRUD) this
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
