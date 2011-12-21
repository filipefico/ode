package ode.medicao.planejamentoMedicao.cci;

import java.util.Set;

import ode.conhecimentoMedicao.cci.EventListenerParaControlador;
import ode.controleUsuario.cgt.*;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cih.FormDadosObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cih.PainelCRUDObjetivoEstrategico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import ode._infraestruturaBase.ciu.*;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;

@Controller(CtrlObjetivoEstrategicoCRUD.NOME)
public class CtrlObjetivoEstrategicoCRUD extends
		CtrlCRUD<ObjetivoEstrategico> {

	public static final String NOME = "CtrlKObjetivoEstrategicoCRUD";

	
	@Autowired
	AplCadastrarObjetivoEstrategico aplCadastrarKObjetivoEstrategico;

	private String titulo = "Cadastrar Objetivo Estrategico";

	@Override
	public String definirTituloJanelaDados() {
		return titulo;
	}

	@Override
	public AplCRUD<ObjetivoEstrategico> definirAplCRUD() {
		return aplCadastrarKObjetivoEstrategico;
	}

	@Override
	public PainelCRUD<ObjetivoEstrategico> definirPainelCRUD() {
		return new PainelCRUDObjetivoEstrategico();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<ObjetivoEstrategico> definirFormularioCadastro() {
		return new FormDadosObjetivoEstrategico();
	}

	@Override
	public ObjetivoEstrategico factoryObjetoDados() {
		return new ObjetivoEstrategico();
	}

	@Override
	public void acaoSalvar() {
		formularioDados.atualizarObjeto();
		ObjetivoEstrategico objetoCadastro = formularioDados
				.getObjetoCadastroDados();
		if (!objetoCadastro.isPersistente()) {
			super.acaoSalvar();
			return;
		}
		if (aplCadastrarKObjetivoEstrategico
				.RelacionamentoSoftwareMedicao(objetoCadastro)) {
			try {
				Messagebox
						.show(NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_AVISO_MEDICAO_SOFTWARE),NucleoMensagens
								.getMensagem(NucleoMensagens.TERMO_ALTERAR)
								+ NucleoMensagens
										.getMensagem(NucleoMensagens.TERMO_OBJETIVO_ESTRATEGICO)+"?", Messagebox.YES | Messagebox.NO,
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
											((CtrlObjetivoEstrategicoCRUD) this
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
