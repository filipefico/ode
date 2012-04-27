package ode.gerenciaConhecimento.cdp;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Relativo a Discussao")
public class ConhecimentoRelativoDiscussao extends ItemConhecimento {



	/**
	 * 
	 */
	private static final long serialVersionUID = -9106412352034918802L;

	private String conhecimentoAdquirido;
	
	private String pontosFortes;
	
	private String pontosFracos;
	
	private String linkDiscussao;

	public String getConhecimentoAdquirido() {
		return conhecimentoAdquirido;
	}

	public void setConhecimentoAdquirido(String conhecimentoAdquirido) {
		this.conhecimentoAdquirido = conhecimentoAdquirido;
	}

	public String getPontosFortes() {
		return pontosFortes;
	}

	public void setPontosFortes(String pontosFortes) {
		this.pontosFortes = pontosFortes;
	}

	public String getPontosFracos() {
		return pontosFracos;
	}

	public void setPontosFracos(String pontosFracos) {
		this.pontosFracos = pontosFracos;
	}

	public String getLinkDiscussao() {
		return linkDiscussao;
	}

	public void setLinkDiscussao(String linkDiscussao) {
		this.linkDiscussao = linkDiscussao;
	}
	
}
