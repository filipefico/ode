package nucleo.comuns.visao.componentes;

import java.util.HashMap;
import java.util.Map;

import nucleo.comuns.autenticacao.acegi.dominio.NucleoPessoa;
import nucleo.comuns.visao.NucleoWindowSelecaoLista;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;


public class NucleoHboxSelecaoPessoa extends Hbox {

	private static final long serialVersionUID = -7848219069471753415L;

	public NucleoHboxSelecaoPessoa() {
		// Listbox com dados da pessoa
		lbxPessoa.setWidth("150px");
		lbxPessoa.setMold("select");
		lbxPessoa.setRows(1);
		lbxPessoa.setDisabled(true);
		lbxPessoa.setParent(this);

		// Botão para pesquisa por pessoa
		btnProcurar.setImage("/imagens/procurar.png");
		btnProcurar.setParent(this);
		btnProcurar.addEventListener("onClick", new EventListenerProcurar());

		// Botão para apagar a pessoa
		btnApagar.setImage("/imagens/apagar.png");
		btnApagar.setParent(this);
		btnApagar.addEventListener("onClick", new EventListenerApagar());

	}

	/** Classe do evento procurar pessoa */
	private class EventListenerProcurar implements EventListener {

		public void onEvent(Event event) {
			Map<String, Object> parametrosWindowSelecao = new HashMap<String, Object>();
			parametrosWindowSelecao.put("lbxPessoa",
					NucleoHboxSelecaoPessoa.this.lbxPessoa);
			NucleoWindowSelecaoLista<NucleoPessoa> win = (NucleoWindowSelecaoLista<NucleoPessoa>) Executions
					.createComponents(recuperarNomeWindowSelecao(), null,
							parametrosWindowSelecao);
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

	/**
	 * Método pode ser sobrescrito caso seja necessário mudar o nome/caminho da
	 * window.
	 * 
	 * @return
	 */
	protected String recuperarNomeWindowSelecao() {
		return "/visao/cadastros_gerais/windowSelecaoListaPessoa.zul";
	}

	/** Classe do evento apagar pessoa */
	private class EventListenerApagar implements EventListener {

		public void onEvent(Event event) {
			lbxPessoa.getItems().clear();
		}

		public boolean isAsap() {
			return true;
		}

	}

	public NucleoPessoa getPessoaSelecionada() {
		if (lbxPessoa.getItems() != null && lbxPessoa.getItems().size() > 0) {
			return (NucleoPessoa) lbxPessoa.getSelectedItem().getValue();
		} else {
			return null;
		}

	}

	public void setPessoaSelecionada(NucleoPessoa pessoa) {
		lbxPessoa.getItems().clear();

		if (pessoa != null) {
			Listitem listitem = new Listitem(pessoa.getNome());
			listitem.setValue(pessoa);
			lbxPessoa.appendChild(listitem);
			lbxPessoa.setSelectedIndex(0);
		}
	}

	public void desabilitar(boolean condicao) {
		lbxPessoa.setDisabled(condicao);
		btnProcurar.setDisabled(condicao);
		btnApagar.setDisabled(condicao);
	}

	public Listbox lbxPessoa = new Listbox();

	public Button btnProcurar = new Button();

	public Button btnApagar = new Button();

}
