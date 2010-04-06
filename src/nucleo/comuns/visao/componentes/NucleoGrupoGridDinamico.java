package nucleo.comuns.visao.componentes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.util.NucleoMensagens;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;


public abstract class NucleoGrupoGridDinamico<T> extends Groupbox {

	private static final long serialVersionUID = -7170282784053066981L;

	protected Caption captionGrupo;

	protected Grid gridDinamico;

	protected Rows rowsGridDinamico;

	protected List<Row> linhasGrupoGridDinamico = new ArrayList<Row>();

	private Row rowAdicionarLinha;

	protected Button btnAdicionarLinha = new Button(NucleoMensagens
			.getMensagem(NucleoMensagens.TERMO_ADICIONAR));

	/**
	 * Este atributo serve para habilitar/desabilitar os botões excluir e
	 * adicionar do grid dinâmico.
	 */
	protected boolean desabilitarBotoes = false;

	public boolean isDesabilitarBotoes() {
		return desabilitarBotoes;
	}

	public void setDesabilitarBotoes(boolean desabilitarBotoes) {
		this.desabilitarBotoes = desabilitarBotoes;
	}

	public NucleoGrupoGridDinamico(String tituloGrupo, String[] titulosColunas,
			boolean desabilitarBotoes) {
		this.instanciarAtributos();
		this.setDesabilitarBotoes(desabilitarBotoes);
		this.iniciarComponentes(tituloGrupo, titulosColunas);
	}

	/**
	 * Método que pode ser implementadado caso haja necessidade de instaciar
	 * atributos da sub-classe no construtor dessa classe.
	 * 
	 */
	protected void instanciarAtributos() {
	}

	public NucleoGrupoGridDinamico(String tituloGrupo, String[] titulosColunas) {
		this.iniciarComponentes(tituloGrupo, titulosColunas);
	}

	private void iniciarComponentes(String tituloGrupo, String[] titulosColunas) {
		// Atribui o caption ao grupo
		captionGrupo = new Caption(tituloGrupo);
		captionGrupo.setParent(this);

		// Cria o grid e o conjunto de rows
		gridDinamico = new Grid();
		gridDinamico.setParent(this);
		colunas = new Columns();
		colunas.setParent(gridDinamico);
		for (String tituloColuna : titulosColunas) {
			Column colunaGrid = new Column(tituloColuna);
			colunaGrid.setParent(colunas);
		}
		Column colunaExcluir = new Column();
		colunaExcluir.setParent(colunas);
		rowsGridDinamico = new Rows();
		rowsGridDinamico.setParent(gridDinamico);

		// Cria uma linha vazia
		Row linhaGrid = criarNovaLinhaComBotaoExcluir();
		linhaGrid.setParent(rowsGridDinamico);
		linhasGrupoGridDinamico.add(linhaGrid);

		// Cria a linha de adição de linhas
		rowAdicionarLinha = new Row();
		rowAdicionarLinha.setParent(rowsGridDinamico);
		// Cria um Hbox para cada coluna anterior à coluna do botão adicionar
		for (int i = 0; i < titulosColunas.length; i++) {
			new Hbox().setParent(rowAdicionarLinha);
		}
		btnAdicionarLinha.setParent(rowAdicionarLinha);
		btnAdicionarLinha.addEventListener("onClick",
				new EventListenerAdicionarLinha());

		// Habilita/Desabilita botão adicionar
		btnAdicionarLinha.setDisabled(this.desabilitarBotoes);

		this.iniciarComponentesExtensao();

	}

	/**
	 * Método que pode ser implementado para executar algo após o método iniciar
	 * componentes.
	 * 
	 */
	protected void iniciarComponentesExtensao() {
	}

	private Row criarNovaLinhaComBotaoExcluir() {
		Row novaLinha = criarNovaLinha();

		Button btnExcluirLinha = new Button(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_EXCLUIR));
		btnExcluirLinha.setParent(novaLinha);
		btnExcluirLinha.addEventListener("onClick",
				new EventListenerExcluirLinha());

		return novaLinha;
	}

	protected abstract Row criarNovaLinha();

	public void inserirLinhasVisao(Collection<T> listaObjetosDominio) {
		if (listaObjetosDominio != null & listaObjetosDominio.size() > 0) {
			// Limpa as linhas
			while (linhasGrupoGridDinamico.size() > 0) {
				acaoExcluirLinha(linhasGrupoGridDinamico.get(0));
			}

			// Insere as novas linhas
			for (T objetoDominio : listaObjetosDominio) {
				Row linha = objetoDominioToLinhaComBotaoExcluir(objetoDominio);
				acaoAdicionarLinha(linha);
			}
		}
	}

	private Row objetoDominioToLinhaComBotaoExcluir(T objetoDominio) {
		Row linha = objetoDominioToLinha(objetoDominio);

		Button btnExcluirLinha = new Button(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_EXCLUIR));
		btnExcluirLinha.setParent(linha);
		btnExcluirLinha.addEventListener("onClick",
				new EventListenerExcluirLinha());

		// Habilita/Desabilita botão excluir
		btnExcluirLinha.setDisabled(this.desabilitarBotoes);

		return linha;
	}

	protected abstract Row objetoDominioToLinha(T objetoDominio);

	public List<T> toListaObjetosDominio() {
		List<T> listaObjetosDominio = new ArrayList<T>();
		for (Row linhaGrid : linhasGrupoGridDinamico) {
			listaObjetosDominio.add(linhaToObjetoDominio(linhaGrid));
		}
		return listaObjetosDominio;
	}

	protected abstract T linhaToObjetoDominio(Row linha);

	private class EventListenerAdicionarLinha implements EventListener {

		public void onEvent(Event evento) {
			Row novaLinhaGrid = criarNovaLinhaComBotaoExcluir();
			acaoAdicionarLinha(novaLinhaGrid);
		}

		public boolean isAsap() {
			return true;
		}
	}

	public void acaoAdicionarLinha(Row novaLinha) {
		antesAdicionarLinha(novaLinha);

		rowAdicionarLinha.setParent(null);

		novaLinha.setParent(rowsGridDinamico);
		linhasGrupoGridDinamico.add(novaLinha);

		rowAdicionarLinha.setParent(rowsGridDinamico);

		depoisAdicionarLinha(novaLinha);
	}

	protected void antesAdicionarLinha(Row novaLinha) {
	}

	protected void depoisAdicionarLinha(Row novaLinha) {
	}

	private class EventListenerExcluirLinha implements EventListener {

		public void onEvent(Event evento) {
			if (linhasGrupoGridDinamico.size() <= 1) {
				// exibirJanelaErro(NucleoMensagens
				// .getMensagem(NucleoMensagens.MSG_MINIMO_CONDICOES_IGUAL_UM));
			} else {
				Row linhaExcluir = (Row) evento.getTarget().getParent();
				acaoExcluirLinha(linhaExcluir);
			}
		}

		public boolean isAsap() {
			return true;
		}
	}

	public void acaoExcluirLinha(Row linhaExcluir) {
		antesExcluirLinha(linhaExcluir);

		linhaExcluir.setParent(null);
		linhasGrupoGridDinamico.remove(linhaExcluir);

		depoisExcluirLinha(linhaExcluir);
	}

	protected void antesExcluirLinha(Row linhaExcluir) {
	}

	protected void depoisExcluirLinha(Row linhaExcluir) {
	}

	protected Columns colunas;

}
