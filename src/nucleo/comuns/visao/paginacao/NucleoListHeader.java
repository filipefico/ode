package nucleo.comuns.visao.paginacao;

import org.zkoss.zul.Listheader;

public class NucleoListHeader extends Listheader {

	private String atributoBanco;

	public NucleoListHeader(String titulo) {
		super(titulo);
	}

	public String getAtributoBanco() {
		return atributoBanco;
	}

	public void setAtributoBanco(String atributoBanco) {
		this.atributoBanco = atributoBanco;
	}

}
