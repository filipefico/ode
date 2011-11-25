package ode._infraestruturaCRUD.ciu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class GridDados extends Grid {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7133443852058334459L;
	
	private Columns colunas = new Columns();
	private Column colunaEsquerda = new Column();
	private Column colunaDireita = new Column();
	private Rows linhas = new Rows();

	public GridDados() {
		configurarComponentes();

	}

	protected void configurarComponentes() {
		colunaEsquerda.setWidth("50%");
		colunaEsquerda.setParent(colunas);
		
		colunaDireita.setWidth("50%");
		colunaDireita.setParent(colunas);
		
		colunas.setVisible(false);
		
		colunas.setParent(this);
		linhas.setParent(this);
	}
	
	public void setLarguras(String largura1, String largura2) {
		colunas.getChildren().clear();
		colunaEsquerda.setWidth(largura1);
		colunaEsquerda.setParent(colunas);
		colunaDireita.setWidth(largura2);
		colunaDireita.setParent(colunas);	
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
	
	public void adicionarLinha(String titulo, String valor) {
		Row linha = new Row();
		linha.setParent(linhas);
		
		Label label = new Label(titulo + ": ");
		label.setParent(linha);
		
		Label label2 = new Label(valor);
		label2.setParent(linha);
	}
	
	
	public void adicionarLinhaObrigatoria(String tituloLabel, Component elemento) {
		Row linha = new Row();
		linha.setParent(linhas);
		
		Label label = new Label(tituloLabel+ "*: ");
		
		label.setParent(linha);
		elemento.setParent(linha);

	}

	public void adicionarLinhaDupla(String labelTitulo, Component elemento) {
		Row linha = new Row();
		linha.setParent(linhas);
		
		Cell cell = new Cell();
		cell.setColspan(2);
		cell.setParent(linha);
		
		Label label = new Label(labelTitulo+ ": ");
		label.setParent(cell);
		
		elemento.setParent(cell);
	}
	
	public void adicionarLinhaUnica(String str) {
		Row linha = new Row();
		linha.setParent(linhas);
		
		Cell cell = new Cell();
		cell.setColspan(2);
		cell.setParent(linha);
		
		Label label = new Label(str);
		label.setParent(cell);
	}
	
	public void adicionarLinhaUnica(Component elemento) {
		Row linha = new Row();
		linha.setParent(linhas);
		
		Cell cell = new Cell();
		cell.setColspan(2);
		cell.setParent(linha);
		
		elemento.setParent(cell);
	}

}
