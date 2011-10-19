package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Messagebox;

import ode.conhecimentoMedicao.cdp.KObjetivoEstrategico;
import ode.conhecimentoMedicao.cdp.KObjetivoSoftware;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoSoftware;
import ode.conhecimentoMedicao.cih.FormDadosKObjetivoSoftware;
import ode.conhecimentoMedicao.cih.PainelCRUDKObjetivoSoftware;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaBase.util.NucleoMensagens;

@Controller(CtrlKObjetivoSoftwareCRUD.NOME)
public class CtrlKObjetivoSoftwareCRUD extends CtrlCRUD<KObjetivoSoftware> {

	public static final String NOME = "CtrlKObjetivoSoftwareCRUD";

	@Autowired
	AplCadastrarKObjetivoSoftware aplCadastrarKObjetivoSoftware;

	@Autowired
	AplCadastrarKObjetivoEstrategico aplCadastrarKObjetivoEstrategico;

	private String tituloCadastro = "Cadastrar Objetivo Software";
	private String titulo = "Objetivos de Software";

	@Override
	public String definirTituloJanelaDados() {
		return tituloCadastro;
	}

	@Override
	public AplCRUD<KObjetivoSoftware> definirAplCRUD() {
		return aplCadastrarKObjetivoSoftware;
	}

	public AplCadastrarKObjetivoEstrategico getAplKObjetivoEstrategico() {
		return aplCadastrarKObjetivoEstrategico;
	}

	@Override
	public PainelCRUD<KObjetivoSoftware> definirPainelCRUD() {
		return new PainelCRUDKObjetivoSoftware();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return titulo;
	}

	@Override
	public FormularioDadosCRUD<KObjetivoSoftware> definirFormularioCadastro() {
		return new FormDadosKObjetivoSoftware();
	}

	@Override
	public KObjetivoSoftware factoryObjetoDados() {
		return new KObjetivoSoftware();
	}

	@Override
	public void acaoSalvar() {
		formularioDados.atualizarObjeto();
		KObjetivoSoftware objetoCadastro = formularioDados
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
											((CtrlKObjetivoSoftwareCRUD) this
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
