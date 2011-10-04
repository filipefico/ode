package ode._controleFerramenta.cdp;

import javax.persistence.Enumerated;
import javax.persistence.Entity;
import javax.persistence.Transient;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;

@Entity
public class FerramentaSoftware extends Recurso {

	private static final long serialVersionUID = 1L;
	
	private String versao;
	
	private String caminho;
	
	private EscopoFerramentaSoftware escopo;
	
	private OrigemFerramentaSoftware origem;

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
	
	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	@Enumerated
	public EscopoFerramentaSoftware getEscopo() {
		return escopo;
	}

	public void setEscopo(EscopoFerramentaSoftware escopo) {
		this.escopo = escopo;
	}

	@Enumerated
	public OrigemFerramentaSoftware getOrigem() {
		return origem;
	}

	public void setOrigem(OrigemFerramentaSoftware origem) {
		this.origem = origem;
	}

	@Transient
	public KFerramentaSoftware getKFerramentaSoftware() {
		return (KFerramentaSoftware)getKRecurso();
	}

}
