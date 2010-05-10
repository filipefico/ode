package nucleo.comuns.visao.paginacao;

import org.zkoss.zul.Listheader;

public class NucleoListHeader extends Listheader {

	private String atributoBanco;
/**
 * @param String titulo - Titulo do Cabecalho
 * @param String atributoBanco - String do atributo no banco que sera pesquisado
 * @param String tamanhoColuna - tamanho em px das colunas
 * */
	public NucleoListHeader(String titulo, String atributoBanco, String tamanhoColuna) {
		super(titulo);
		setAtributoBanco(atributoBanco);
		setWidth(tamanhoColuna);
	}

	public String getAtributoBanco() {
		return atributoBanco;
	}

	public void setAtributoBanco(String atributoBanco) {
		this.atributoBanco = atributoBanco;
	}

}
