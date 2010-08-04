package nucleo.comuns.visao.principal;

import nucleo.comuns.visao.NucleoWindow;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class NucleoMessageBox extends NucleoWindow {

	private static final long serialVersionUID = 3648807769226212348L;

	private String tituloMessageBox = "";

	private String msgAviso = "";

	private String msgExplicacao = "";

	public void setMsgAviso(String msgAviso) {
		this.msgAviso = msgAviso;
		this.lblAviso.setValue(msgAviso);
	}

	public void setMsgExplicacao(String msgExplicacao) {
		this.msgExplicacao = msgExplicacao;
		this.txtExplicacao.setText(msgExplicacao);
	}

	public void setTituloMessageBox(String tituloMessageBox) {
		this.tituloMessageBox = tituloMessageBox;
		this.setTitle(tituloMessageBox);
	}

	public static void show(String tituloMessageBox, String msgAviso,
			String msgExplicacao) {
		NucleoMessageBox nucleoMessageBox = (NucleoMessageBox) Executions
				.createComponents("/visao/messagebox/nucleoMessageBox.zul", null,
						null);

		nucleoMessageBox.setTituloMessageBox(tituloMessageBox);
		nucleoMessageBox.setMsgAviso(msgAviso);
		nucleoMessageBox.setMsgExplicacao(msgExplicacao);

		try {
			nucleoMessageBox.doModal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public NucleoMessageBox() {
		super();
	}

	@Override
	protected String getTituloWindow() {
		return this.tituloMessageBox;
	}

	@Override
	public void onCreateWindow() {
		// Realiza a configuração inicial da janela
		iniciarComponentesInterface();

		// Preenche os conteúdos dos componentes
		preencherDadosTela();
	}

	private void iniciarComponentesInterface() {
		// Configura os componentes
		this.configurarComponentes();

		// Adiciona os componentes
		this.adicionarComponentes();
	}

	private void configurarComponentes() {
		this.setWidth("383px");

		txtExplicacao.setRows(4);
		txtExplicacao.setCols(70);
		txtExplicacao.setDisabled(true);

		btnOK.addEventListener("onClick", new EventListenerFechar());
	}

	private void adicionarComponentes() {
		Vbox vbox = new Vbox();
		vbox.setStyle("align:center");
		vbox.setParent(this);
		lblAviso.setParent(vbox);
		txtExplicacao.setParent(vbox);
		btnOK.setParent(vbox);
	}

	private void preencherDadosTela() {
		lblAviso.setValue(msgAviso);
		txtExplicacao.setText(msgExplicacao);
	}

	private class EventListenerFechar implements EventListener {

		public void onEvent(Event event) {
			NucleoMessageBox.this.detach();
		}

		public boolean isAsap() {
			return true;
		}

	}

	private Label lblAviso = new Label();

	private Textbox txtExplicacao = new Textbox();

	private Button btnOK = new Button("OK");

}
