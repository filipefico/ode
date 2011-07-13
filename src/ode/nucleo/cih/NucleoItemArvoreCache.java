package ode.nucleo.cih;

import java.util.ArrayList;
import java.util.List;

public class NucleoItemArvoreCache {

	private Object objeto;

	/**
	 * Utilizado para guardar informações relevantes para o objeto
	 */
	private Object informacoesObjeto;

	private boolean folha;

	private boolean acessouBD;

	List<NucleoItemArvoreCache> filhos = new ArrayList<NucleoItemArvoreCache>();

	public NucleoItemArvoreCache(Object objeto, boolean folha, boolean acessouBD) {
		// Faz as atribuições
		setObjeto(objeto);
		setFolha(folha);
		setAcessouBD(acessouBD);
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public Object getInformacoesObjeto() {
		return informacoesObjeto;
	}

	public void setInformacoesObjeto(Object informacaoesObjeto) {
		this.informacoesObjeto = informacaoesObjeto;
	}

	public boolean isAcessouBD() {
		return acessouBD;
	}

	public void setAcessouBD(boolean acessouBD) {
		this.acessouBD = acessouBD;
	}

	public List<NucleoItemArvoreCache> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<NucleoItemArvoreCache> filhos) {
		this.filhos = filhos;
	}

	public boolean isFolha() {
		return folha;
	}

	public void setFolha(boolean folha) {
		this.folha = folha;
	}

}
