package nucleo.global.visao;

import java.util.HashMap;
import java.util.Map;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoUserDetails;
import nucleo.comuns.visao.NucleoWindowSelecaoLista;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;


public class NucleoHboxSelecaoUsuario extends Hbox {

	private static final long serialVersionUID = 1039449675672268657L;

	public NucleoHboxSelecaoUsuario() {
		// Listbox com dados da pessoa
		lbxUsuario.setWidth("200px");
		lbxUsuario.setMold("select");
		lbxUsuario.setRows(1);
		lbxUsuario.setDisabled(true);
		lbxUsuario.setParent(this);

		// Botão para pesquisa por pessoa
		btnProcurar.setImage("/imagens/procurar.png");
		btnProcurar.setParent(this);
		btnProcurar.addEventListener("onClick", new EventListenerProcurar());

		// Botão para apagar a pessoa
		btnApagar.setImage("/imagens/apagar.png");
		btnApagar.setParent(this);
		btnApagar.addEventListener("onClick", new EventListenerApagar());

	}

	/** Classe do evento procurar usuário */
	private class EventListenerProcurar implements EventListener {

		public void onEvent(Event event) {
			Map<String, Object> parametrosWindowSelecao = new HashMap<String, Object>();
			parametrosWindowSelecao.put("lbxUsuario",
					NucleoHboxSelecaoUsuario.this.lbxUsuario);
			NucleoWindowSelecaoLista<NucleoUserDetails> win = (NucleoWindowSelecaoLista<NucleoUserDetails>) Executions
					.createComponents(
							"/visao/global/windowSelecaoListaUsuario.zul",
							null, parametrosWindowSelecao);
			try {
				win.doModal();
			} catch (Exception e) {
				// exibirJanelaErro(e);
			}
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento apagar usuário */
	private class EventListenerApagar implements EventListener {

		public void onEvent(Event event) {
			lbxUsuario.getItems().clear();
		}

		public boolean isAsap() {
			return true;
		}

	}

	public NucleoUserDetails getUsuarioSelecionado() {
		if (lbxUsuario.getItems() != null && lbxUsuario.getItems().size() > 0) {
			return (NucleoUserDetails) lbxUsuario.getSelectedItem().getValue();
		} else {
			return null;
		}

	}

	public void setUsuarioSelecionado(NucleoUserDetails usuario) {
		lbxUsuario.getItems().clear();

		if (usuario != null) {
			Listitem listitem = new Listitem(usuario.getPessoa().getNome());
			listitem.setValue(usuario);
			lbxUsuario.appendChild(listitem);
			lbxUsuario.setSelectedIndex(0);
		}
	}

	public void desabilitar(boolean condicao) {
		lbxUsuario.setDisabled(condicao);
		btnProcurar.setDisabled(condicao);
		btnApagar.setDisabled(condicao);
	}

	public Listbox lbxUsuario = new Listbox();

	public Button btnProcurar = new Button();

	public Button btnApagar = new Button();
}
