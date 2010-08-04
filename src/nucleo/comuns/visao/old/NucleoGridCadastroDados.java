package nucleo.comuns.visao.old;

import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

/**
 * Esta classe tem como objeto definir um grid que, por sua vez, é um componente
 * de interface gráfica do framework Zkoss. Cada linha desse grid possui um
 * label e um textbox.
 * 
 * @author Alexandre G. N. Coelho
 * 
 */

public abstract class NucleoGridCadastroDados<T> extends Grid {

	/** Grupo de linhas . */
	private Rows rows;

	/** Recupera labels para o preenchimento do grid. */
	public abstract String[] recuperarLabels();

	/** Recupera tamanhos dos textbox's do grid. */
	public abstract String[] recuperarTamanhosTextboxs();

	/** Recupera dados do objeto a serem inseridos. */
	public abstract String[] recuperarDadosObjeto();

	/** Construtor. */
	public NucleoGridCadastroDados() {
		this.iniciarComponentesInterface();
		this.preencherGrid(this.recuperarLabels(), this
				.recuperarTamanhosTextboxs(), this.recuperarDadosObjeto());
	}

	/** Inicia componentes de interface. */
	private void iniciarComponentesInterface() {
		rows = new Rows();
		rows.setParent(this);
	}

	/** Preenche grid, definindo os labels e o tamanho dos textbox's. */
	public void preencherGrid(String[] labels, String[] tamanhos, String[] dados) {

		// cria linhas
		for (int i = 0; i < labels.length; i++) {
			// cria uma linha
			Row locRow = new Row();
			locRow.setParent(rows);

			// cria label
			Label locLabel = new Label(labels[i]);
			locLabel.setParent(locRow);

			// cria textfild e define o tamanho
			Textbox locTextbox = new Textbox();
			locTextbox.setParent(locRow);
			locTextbox.setWidth(tamanhos[i]);
			locTextbox.setValue(dados[i]);
		}
	}


}
