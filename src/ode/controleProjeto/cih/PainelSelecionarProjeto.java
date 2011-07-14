package ode.controleProjeto.cih;

import java.util.Collection;
import java.util.Set;

import ode.controleProjeto.cci.CtrlSelecionarProjeto;
import ode.controleProjeto.cdp.Projeto;
import ode.nucleo.util.NucleoContexto;
import ode.nucleo.util.NucleoMensagens;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Separator;

public class PainelSelecionarProjeto extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CtrlSelecionarProjeto ctrlSelecionarProjeto;

	public PainelSelecionarProjeto(CtrlSelecionarProjeto ctrlSelecionarProjeto){

		super();
		
		this.ctrlSelecionarProjeto = ctrlSelecionarProjeto;
		
		// Inicia os componentes da interface
		this.iniciarComponentesInterface();

		// Atualiza a lista de elementos
		this.atualizarLista();
	}

	private void iniciarComponentesInterface() {
		// Cria lista de projetos
		Panelchildren panelchildren = new Panelchildren();
		panelchildren.setParent(this);
		listBox = new Listbox();
		listBox.setHeight("300px");
		listBox.setCheckmark(true);
		listBox.setMultiple(false);
		listBox.setParent(panelchildren);
		Listhead listHead = new Listhead();
		listHead.setParent(listBox);
		Listheader listHeader = new Listheader("Projeto");
		listHeader.setParent(listHead);

		// Botões OK e Cancelar
		Div div = new Div();
		div.setParent(panelchildren);
		div.setStyle("float: right;");
		Separator separator = new Separator();
		separator.setParent(div);
		Button botaoOK = new Button("OK");
		botaoOK.addEventListener("onClick", new EventListenerOK());
		botaoOK.setWidth("100px");
		botaoOK.setStyle("margin-right: 5px");
		botaoOK.setParent(div);
		Button botaoCancelar = new Button("Cancelar");
		botaoCancelar.addEventListener("onClick", new EventListenerCancelar());
		botaoCancelar.setWidth("100px");
		botaoCancelar.setParent(div);
	}

	public void atualizarLista() {
		
		// Obtém os projetos cadastrados
		Collection<Projeto> projetos = ctrlSelecionarProjeto.getAplCadastrarProjeto().recuperarTodos();

		// Preeche a lista com os projetos
		listBox.getItems().clear();
		for (Projeto projeto : projetos) {
			// Cria a linha
			Listitem li = new Listitem();
			li.setValue(projeto);
			listBox.appendChild(li);

			// Cria as celulas e insere na linha
			Listcell lc = new Listcell(projeto.getNome());
			li.appendChild(lc);
		}

	}

	private class EventListenerOK implements EventListener {

		public void onEvent(Event arg0) {
			// Obtém os itens selecionados
			Set itensSelecionados = listBox.getSelectedItems();

			// Verifica se o número de itens selecionados é igual a 1.
			if (itensSelecionados.size() == 1) {
				// Obtém o projeto selecionado e guarda no contexto
				Projeto projeto = (Projeto) listBox.getSelectedItem()
				.getValue();
				NucleoContexto.atribuirProjeto(projeto);

				// Atualiza o menu
				ctrlSelecionarProjeto.atualizarWindowMenu();

				// Finaliza a aplicação
				ctrlSelecionarProjeto.finalizar();

				try {
					// Exibe mensagem de sucesso
					Messagebox
					.show("Projeto selecionado com sucesso.");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				try {
					String mensagem = NucleoMensagens
					.getMensagem(NucleoMensagens.MSG_NENHUM_ELEMENTO_SELECIONADO);
					if (itensSelecionados.size() > 1) {
						mensagem = NucleoMensagens
						.getMensagem(NucleoMensagens.MSG_SELECIONE_APENAS_UM_ITEM);
					}

					// Fecha a lista de projetos
					PainelSelecionarProjeto.this.detach();

					// Exibe mensagem de erro
					Messagebox.show(mensagem, NucleoMensagens
							.getMensagem(NucleoMensagens.TERMO_AVISO), 1,
							Messagebox.INFORMATION);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}

		public boolean isAsap() {
			return true;
		}
	}

	private class EventListenerCancelar implements EventListener {

		public void onEvent(Event arg0) {
			PainelSelecionarProjeto.this.detach();
		}

		public boolean isAsap() {
			return true;
		}
	}

	private Listbox listBox;


}