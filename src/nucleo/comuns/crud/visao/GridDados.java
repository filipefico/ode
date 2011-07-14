package nucleo.comuns.crud.visao;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class GridDados extends Grid {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7133443852058334459L;
	private Rows linhas = new Rows();

	public GridDados() {
		configurarComponentes();

	}

	protected void configurarComponentes() {
		linhas.setParent(this);

	}
/**
 * Método que adiciona uma linha simples ao grid de dados
 * */
	public void adicionarLinha(String tituloLabel, Component elemento) {
		Row linha = new Row();
		linha.setParent(linhas);
		
		Label label = new Label(tituloLabel+ ": ");
		label.setParent(linha);
		elemento.setParent(linha);

	}
	
	
	public void adicionarLinhaObrigatoria(String tituloLabel, Component elemento) {
		Row linha = new Row();
		linha.setParent(linhas);
		
		Label label = new Label(tituloLabel+ "*: ");
		label.setStyle("color:red");
		label.setParent(linha);
		elemento.setParent(linha);

	}

}
