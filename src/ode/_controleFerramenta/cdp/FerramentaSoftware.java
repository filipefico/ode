package ode._controleFerramenta.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;

@Entity
public class FerramentaSoftware extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private String nome;
    private boolean ativo;
	private String versao;
	private String caminho;
	private boolean disponivelApenasParaProjetos;
	private boolean interna;
	private KFerramentaSoftware kFerramentaSoftware;

	public FerramentaSoftware() {
        setAtivo(true);
    }
	
	public String toString(){
        return nome;
    }
    
    @Column(length = 80)
    public String getNome() {
        return nome;
    }
    public void setNome(String parNome) {
        nome = parNome;
    }
    
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean parAtivo) {
        ativo = parAtivo;
    }
	
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
	
	public boolean isDisponivelApenasParaProjetos() {
		return disponivelApenasParaProjetos;
	}
	public void setDisponivelApenasParaProjetos(boolean disponivelApenasParaProjetos) {
		this.disponivelApenasParaProjetos = disponivelApenasParaProjetos;
	}

	public boolean isInterna() {
		return interna;
	}
	public void setInterna(boolean interna) {
		this.interna = interna;
	}
	
    @ManyToOne
    public KFerramentaSoftware getkFerramentaSoftware() {
    	return this.kFerramentaSoftware;
    }
    public void setkFerramentaSoftware(KFerramentaSoftware kFerramentaSoftware) {
    	this.kFerramentaSoftware = kFerramentaSoftware;
    }
}
