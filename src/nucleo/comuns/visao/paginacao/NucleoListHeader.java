package nucleo.comuns.visao.paginacao;

import org.zkoss.zul.Listheader;

public class NucleoListHeader extends Listheader {

	private String atributoBanco;

	public NucleoListHeader(String titulo, String atributoBanco, String tamanho) {
		super(titulo);
		setAtributoBanco(atributoBanco);
		setWidth(tamanho);
	}

	public String getAtributoBanco() {
		return atributoBanco;
	}

	public void setAtributoBanco(String atributoBanco) {
		this.atributoBanco = atributoBanco;
	}

}
