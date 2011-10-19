package ode.conhecimentoMedicao.cci;

import java.util.Set;
import ode.controleUsuario.cgt.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
import ode.conhecimentoMedicao.cih.FormDadosKObjetivoEstrategico;
import ode.conhecimentoMedicao.cih.PainelCRUDKObjetivoEstrategico;
import ode._infraestruturaBase.ciu.*;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;

@Controller(CtrlKObjetivoEstrategicoCRUD.NOME)
public class CtrlKObjetivoEstrategicoCRUD extends
		CtrlCRUD<KObjetivoEstrategico> {

	public static final String NOME = "CtrlKObjetivoEstrategicoCRUD";

	@Autowired
	AplCadastrarKObjetivoEstrategico aplCadastrarKObjetivoEstrategico;

	private String titulo = "Cadastrar Objetivo Estrategico";

	@Override
	public String definirTituloJanelaDados() {
		return titulo;
	}

	@Override
	public AplCRUD<KObjetivoEstrategico> definirAplCRUD() {
		return aplCadastrarKObjetivoEstrategico;
	}

	@Override
	public PainelCRUD<KObjetivoEstrategico> definirPainelCRUD() {
		return new PainelCRUDKObjetivoEstrategico();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KObjetivoEstrategico> definirFormularioCadastro() {
		return new FormDadosKObjetivoEstrategico();
	}

	@Override
	public KObjetivoEstrategico factoryObjetoDados() {
		return new KObjetivoEstrategico();
	}

	@Override
	public void acaoSalvar() {
		formularioDados.atualizarObjeto();
		KObjetivoEstrategico objetoCadastro = formularioDados
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
											((CtrlKObjetivoEstrategicoCRUD) this
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
