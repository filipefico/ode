package ode.medicao.planejamentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Messagebox;

import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cih.FormDadosObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cih.PainelCRUDObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode.conhecimentoMedicao.cci.EventListenerParaControlador;

@Controller(CtrlObjetivoSoftwareCRUD.NOME)
public class CtrlObjetivoSoftwareCRUD extends CtrlCRUD<ObjetivoSoftware> {

	public static final String NOME = "CtrlKObjetivoSoftwareCRUD";

	@Autowired
	AplCadastrarObjetivoSoftware aplCadastrarKObjetivoSoftware;

	@Autowired
	AplCadastrarObjetivoEstrategico aplCadastrarKObjetivoEstrategico;

	private String tituloCadastro = "Cadastrar Objetivo Software";
	private String titulo = "Objetivos de Software";

	@Override
	public String definirTituloJanelaDados() {
		return tituloCadastro;
	}

	@Override
	public AplCRUD<ObjetivoSoftware> definirAplCRUD() {
		return aplCadastrarKObjetivoSoftware;
	}

	public AplCadastrarObjetivoEstrategico getAplKObjetivoEstrategico() {
		return aplCadastrarKObjetivoEstrategico;
	}

	@Override
	public PainelCRUD<ObjetivoSoftware> definirPainelCRUD() {
		return new PainelCRUDObjetivoSoftware();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<ObjetivoSoftware> definirFormularioCadastro() {
		return new FormDadosObjetivoSoftware();
	}

	@Override
	public ObjetivoSoftware factoryObjetoDados() {
		return new ObjetivoSoftware();
	}

	@Override
	public void acaoSalvar() {
		formularioDados.atualizarObjeto();
		ObjetivoSoftware objetoCadastro = formularioDados
				.getObjetoCadastroDados();
		if (!objetoCadastro.isPersistente()) {
			super.acaoSalvar();
			return;
		}
		if (aplCadastrarKObjetivoSoftware.RelacionamentoMedicao(objetoCadastro)) {
			try {
				Messagebox
						.show(NucleoMensagens
								.getMensagem(NucleoMensagens.MSG_AVISO_SOFTWARE),
								NucleoMensagens
										.getMensagem(NucleoMensagens.TERMO_ALTERAR)
										+ NucleoMensagens
												.getMensagem(NucleoMensagens.TERMO_OBJETIVO_SOFTWARE),
								Messagebox.YES | Messagebox.NO,
								Messagebox.QUESTION,
								new EventListenerParaControlador(this) {

									@Override
									public void onEvent(Event e)
											throws Exception {
										switch ((Integer) e.getData()) {
										case Messagebox.YES:
											if (/* usuario==adminstrador */false) {
												Messagebox.show(NucleoMensagens
														.getMensagem(NucleoMensagens.MSG_SOMENTE_ADMINISTRADOR));
												return;
											}
											((CtrlObjetivoSoftwareCRUD) this
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
		} else {
			super.acaoSalvar();
		}
	}

	public void recallAcaoSalvar() {
		super.acaoSalvar();
	}

}
