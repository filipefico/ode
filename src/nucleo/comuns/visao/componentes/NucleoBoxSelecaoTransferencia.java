package nucleo.comuns.visao.componentes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import nucleo.comuns.util.NucleoMensagens;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;


public class NucleoBoxSelecaoTransferencia extends Hbox {

	private static final long serialVersionUID = 3187883277275058718L;

	/**
	 * Cria um novo componente de transferência entre listas.
	 * 
	 * @param todosItens
	 *            Lista com todos os itens possíveis de serem selecionados
	 *            (inclusive com os elementos selecionados).
	 * @param itensSelecionados
	 *            Lista com os itens selecionados.
	 * @param transformaInternacionalizavel
	 *            Se verdadeiro, internacionaliza o toString na hora de mostrar
	 *            os dados das listas. Se falso, supõe que já é
	 *            internacionalizável.
	 */
	public NucleoBoxSelecaoTransferencia(List todosItens, List itensSelecionados,
			boolean transformaInternacionalizavel) {
		super();

		iniciarComponentesInterface();

		preencherListas(todosItens, itensSelecionados,
				transformaInternacionalizavel);
	}

	/**
	 * Configura os componentes e os adiciona à interface gráfica.
	 */
	private void iniciarComponentesInterface() {
		// Configura os componentes
		configurarComponentes();

		// Adiciona os componentes
		adicionarComponentes();
	}

	/**
	 * Define a configuração dos componentes da interface no que diz respeito a
	 * dimensões, imagem utilizada, eventos, etc.
	 */
	private void configurarComponentes() {
		// //////////////////////////////////////
		// Configuração do box que envolve todos os componentes
		// //////////////////////////////////////
		this.setAlign("middle");

		// //////////////////////////////////////
		// Box com a lista dos elementos disponíveis
		// //////////////////////////////////////
		lbxListaDisponiveis.setWidth("230px");
		lbxListaDisponiveis.setHeight("170px");
		lbxListaDisponiveis.setMultiple(true);

		// //////////////////////////////////////
		// Box com os botões de transferência
		// //////////////////////////////////////
		btnTransfereParaSelecionados.setImage("/imagens/transf_dir.png");
		btnTransfereParaSelecionados.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_TRANSFERIR_PARA_SELECIONADOS));
		btnTransfereParaSelecionados.addEventListener("onClick",
				new EventListenerTransferirParaSelecionados());

		btnTransfereParaDisponiveis.setImage("/imagens/transf_esq.png");
		btnTransfereParaDisponiveis.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_TRANSFERIR_PARA_DISPONIVEIS));
		btnTransfereParaDisponiveis.addEventListener("onClick",
				new EventListenerTransferirParaDisponiveis());

		// //////////////////////////////////////
		// Box com a lista dos elementos selecionados
		// //////////////////////////////////////
		lbxListaSelecionados.setWidth("230px");
		lbxListaSelecionados.setHeight("170px");
		lbxListaSelecionados.setMultiple(true);

		// //////////////////////////////////////
		// Box com os botões para mover elementos selecionados
		// //////////////////////////////////////
		btnMoveParaCima.setImage("/imagens/move_cima.png");
		btnMoveParaCima.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_MOVER_PARA_CIMA));
		btnMoveParaCima.addEventListener("onClick",
				new EventListenerMoverParaCima());

		btnMoveParaBaixo.setImage("/imagens/move_baixo.png");
		btnMoveParaBaixo.setTooltiptext(NucleoMensagens
				.getMensagem(NucleoMensagens.MSG_MOVER_PARA_BAIXO));
		btnMoveParaBaixo.addEventListener("onClick",
				new EventListenerMoverParaBaixo());
	}

	/**
	 * Adiciona os componentes, posicionando-os na janela.
	 */
	private void adicionarComponentes() {
		// //////////////////////////////////////
		// Box com a lista dos elementos disponíveis
		// //////////////////////////////////////
		Vbox vboxDisponiveis = new Vbox();
		vboxDisponiveis.setParent(this);

		lblDisponiveis.setParent(vboxDisponiveis);

		lbxListaDisponiveis.setParent(vboxDisponiveis);

		// //////////////////////////////////////
		// Box com os botões de transferência
		// //////////////////////////////////////
		Vbox vboxBotoesTransferencia = new Vbox();
		vboxBotoesTransferencia.setParent(this);

		btnTransfereParaSelecionados.setParent(vboxBotoesTransferencia);

		btnTransfereParaDisponiveis.setParent(vboxBotoesTransferencia);

		// //////////////////////////////////////
		// Box com a lista dos elementos selecionados
		// //////////////////////////////////////
		Vbox vboxSelecionados = new Vbox();
		vboxSelecionados.setParent(this);

		lblSelecionados.setParent(vboxSelecionados);

		lbxListaSelecionados.setParent(vboxSelecionados);

		// //////////////////////////////////////
		// Box com os botões para mover elementos selecionados
		// //////////////////////////////////////
		Vbox vboxBotoesMovimento = new Vbox();
		vboxBotoesMovimento.setParent(this);

		btnMoveParaCima.setParent(vboxBotoesMovimento);

		btnMoveParaBaixo.setParent(vboxBotoesMovimento);
	}

	/**
	 * Preenche as listas de elementos disponíveis e selecionados. A lista de
	 * elementos disponíveis é preenchida a partir da diferença entre a lista de
	 * todos os elementos passada e a lista de elementos selecionados passada. A
	 * lista de elementos selecionados é igual à própria lista passada.
	 * 
	 * @param todosItens
	 *            Lista com todos os itens possíveis de serem selecionados
	 *            (inclusive com os elementos selecionados).
	 * @param itensSelecionados
	 *            Lista com os itens selecionados.
	 * @param transformaInternacionalizavel
	 *            Se verdadeiro, internacionaliza o toString na hora de mostrar
	 *            os dados das listas. Se falso, supõe que já é
	 *            internacionalizável.
	 */
	public void preencherListas(List todosItens, List itensSelecionados,
			boolean transformaInternacionalizavel) {
		if (todosItens != null) {
			// Cria uma lista com os itens disponíveis a partir da diferença
			// entre a lista com todos os itens e a lista com os itens
			// selecionados
			List<Object> itensDisponiveis = new ArrayList<Object>();
			for (Object elemento : todosItens) {
				if (itensSelecionados == null
						|| !itensSelecionados.contains(elemento)) {
					itensDisponiveis.add(elemento);
				}
			}

			// Remove os elementos da lista de disponíveis
			lbxListaDisponiveis.getItems().clear();

			// Preenche a lista de disponíveis
			for (Object elemento : itensDisponiveis) {
				Listitem listitem;
				if (transformaInternacionalizavel) {
					listitem = new Listitem(NucleoMensagens.getMensagem(elemento
							.toString()), elemento);
				} else {
					listitem = new Listitem(elemento.toString(), elemento);
				}
				listitem.setParent(lbxListaDisponiveis);
			}

		}

		if (itensSelecionados != null) {
			// Remove os elementos da lista de selecionados
			lbxListaSelecionados.getItems().clear();

			// Preenche a lista de selecionados
			for (Object elemento : itensSelecionados) {
				Listitem listitem;
				if (transformaInternacionalizavel) {
					listitem = new Listitem(NucleoMensagens.getMensagem(elemento
							.toString()), elemento);
				} else {
					listitem = new Listitem(elemento.toString(), elemento);
				}
				listitem.setParent(lbxListaSelecionados);
			}
		}
	}

	/**
	 * Obtém uma lista com os itens selecionados.
	 * 
	 * @return Lista com os itens selecionados.
	 */
	@SuppressWarnings("unchecked")
	public List obterItensSelecionados() {
		List<Listitem> listitems = lbxListaSelecionados.getItems();
		List<Object> itensSelecionados = new ArrayList<Object>();

		for (Listitem listitem : listitems) {
			itensSelecionados.add(listitem.getValue());
		}

		return itensSelecionados;
	}

	/**
	 * Obtém uma lista com todos os itens disponíveis.
	 * 
	 * @return Lista com os itens disponíveis.
	 */
	@SuppressWarnings("unchecked")
	public List obterItensDisponiveis() {
		List<Listitem> listitems = lbxListaDisponiveis.getItems();
		List<Object> itensDisponiveis = new ArrayList<Object>();

		for (Listitem listitem : listitems) {
			itensDisponiveis.add(listitem.getValue());
		}

		return itensDisponiveis;
	}

	/** Classe do evento Transferir para Selecionados. */
	private class EventListenerTransferirParaSelecionados implements
			EventListener {

		public void onEvent(Event event) {
			transferirEntreListas(lbxListaDisponiveis, lbxListaSelecionados);
		}

		public boolean isAsap() {
			return true;
		}

	}

	/**
	 * Transfere os elementos selecionados da lista fonte para a lista de
	 * destino.
	 * 
	 * @param listaFonte
	 *            Lista de onde os elementos selecionados serão transferidos.
	 * @param listaDestino
	 *            Lista para a qual serão transferidos os elementos.
	 */
	@SuppressWarnings("unchecked")
	private void transferirEntreListas(Listbox listaFonte, Listbox listaDestino) {
		// Obtém os itens selecionados da lista Fonte
		List<Listitem> itensSelecionados = new ArrayList<Listitem>();
		List<Listitem> itensListaFonte = listaFonte.getItems();
		for (Listitem item : itensListaFonte) {
			if (item.isSelected()) {
				itensSelecionados.add(item);
			}
		}

		// Insere os itens selecionados em um vetor
		// ATENÇÃO: Não use iterator ou foreach pois será disparada Concurrent
		// Modification Exception!!!
		Listitem[] vetorItensSelecionados = new Listitem[itensSelecionados
				.size()];
		int i = 0;
		for (Listitem item : itensSelecionados) {
			vetorItensSelecionados[i] = item;
			i++;
		}

		// Insere os elementos do vetor na lista destino
		for (i = 0; i < vetorItensSelecionados.length; i++) {
			vetorItensSelecionados[i].setParent(listaDestino);
		}
	}

	/**
	 * Altera as larguras das listas de disponíveis e selecionados.
	 * 
	 * @param larguraPixels
	 *            Largura em pixels das listas de disponíveis e selecionados.
	 */
	public void setLarguraListas(int larguraPixels) {
		lbxListaDisponiveis.setWidth(larguraPixels + "px");
		lbxListaSelecionados.setWidth(larguraPixels + "px");
	}

	/**
	 * Altera as alturas das listas de disponíveis e selecionados.
	 * 
	 * @param alturaPixels
	 *            Altura em pixels das listas de disponíveis e selecionados.
	 */
	public void setAlturaListas(int alturaPixels) {
		lbxListaDisponiveis.setHeight(alturaPixels + "px");
		lbxListaSelecionados.setHeight(alturaPixels + "px");
	}

	/** Classe do evento Transferir para Disponíveis. */
	private class EventListenerTransferirParaDisponiveis implements
			EventListener {

		public void onEvent(Event event) {
			transferirEntreListas(lbxListaSelecionados, lbxListaDisponiveis);
		}

		public boolean isAsap() {
			return true;
		}

	}

	/** Classe do evento Mover para Cima. */
	private class EventListenerMoverParaCima implements EventListener {

		@SuppressWarnings("unchecked")
		public void onEvent(Event event) {
			// Cria vetor com os itens que serão movidos para cima
			Set<Listitem> itensSelecionados = lbxListaSelecionados
					.getSelectedItems();
			int pos;
			List<ItemMover> itensMover = new ArrayList<ItemMover>();
			for (Listitem item : itensSelecionados) {
				pos = lbxListaSelecionados.getIndexOfItem(item);
				ItemMover itemMover = new ItemMover();
				itemMover.item = item;
				itemMover.posicaoDestino = pos - 1;
				itensMover.add(itemMover);
			}

			// Ordena os elementos por posição destino
			Collections.sort(itensMover);

			// Move os elementos para cima
			int idxNaoMover = -1;
			for (ItemMover itemMover : itensMover) {
				if (itemMover.posicaoDestino == idxNaoMover) {
					idxNaoMover++;
				} else {
					lbxListaSelecionados.insertBefore(itemMover.item,
							lbxListaSelecionados
									.getItemAtIndex(itemMover.posicaoDestino));
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

		private class ItemMover implements Comparable {
			public int posicaoDestino;

			public Listitem item;

			public int compareTo(Object o) {
				if (!(o instanceof ItemMover)) {
					throw new ClassCastException();
				}
				ItemMover outro = (ItemMover) o;

				return (this.posicaoDestino - outro.posicaoDestino);
			}
		}

	}

	/** Classe do evento Mover para Baixo. */
	private class EventListenerMoverParaBaixo implements EventListener {

		@SuppressWarnings("unchecked")
		public void onEvent(Event event) {
			// Cria vetor com os itens que serão movidos para baixo
			Set<Listitem> itensSelecionados = lbxListaSelecionados
					.getSelectedItems();
			int pos;
			List<ItemMover> itensMover = new ArrayList<ItemMover>();
			for (Listitem item : itensSelecionados) {
				pos = lbxListaSelecionados.getIndexOfItem(item);
				ItemMover itemMover = new ItemMover();
				itemMover.item = item;
				itemMover.posicaoDestino = pos + 2;
				itensMover.add(itemMover);
			}

			// Ordena os elementos por posição destino
			Collections.sort(itensMover);

			// Move os elementos para cima
			int posicaoUltimo = lbxListaSelecionados.getItems().size();
			int idxNaoMover = lbxListaSelecionados.getItems().size() + 1;

			for (ItemMover itemMover : itensMover) {
				if (itemMover.posicaoDestino == idxNaoMover) {
					idxNaoMover--;
				} else {
					if (itemMover.posicaoDestino == posicaoUltimo) {
						lbxListaSelecionados.removeChild(itemMover.item);
						lbxListaSelecionados.appendChild(itemMover.item);
					} else {
						lbxListaSelecionados
								.insertBefore(
										itemMover.item,
										lbxListaSelecionados
												.getItemAtIndex(itemMover.posicaoDestino));
					}
				}
			}
		}

		public boolean isAsap() {
			return true;
		}

		private class ItemMover implements Comparable {
			public int posicaoDestino;

			public Listitem item;

			public int compareTo(Object o) {
				if (!(o instanceof ItemMover)) {
					throw new ClassCastException();
				}
				ItemMover outro = (ItemMover) o;

				return (outro.posicaoDestino - this.posicaoDestino);
			}
		}

	}

	private Label lblDisponiveis = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_ITENS_DISPONIVEIS)
			+ ": ");

	private Listbox lbxListaDisponiveis = new Listbox();

	public Button btnTransfereParaSelecionados = new Button();

	public Button btnTransfereParaDisponiveis = new Button();

	private Label lblSelecionados = new Label(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_ITENS_SELECIONADOS)
			+ ": ");

	private Listbox lbxListaSelecionados = new Listbox();

	public Button btnMoveParaCima = new Button();

	public Button btnMoveParaBaixo = new Button();
}
