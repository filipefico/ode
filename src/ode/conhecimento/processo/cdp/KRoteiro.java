package ode.conhecimento.processo.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Representa os conhecimentos sobre Roteiros do ambiente ODE.
 */
@Entity
public class KRoteiro extends KDiretriz{

	private static final long serialVersionUID = -6962120785483636484L;
	/** Arquivo do Conhecimento Roteiro.*/
    private String arquivo;
    /** Conhecimento do roteiro sobre qual tipo de artefato ele produz */
    private KArtefato kArtefato;
    
    
    /**Construtor.*/
    public KRoteiro() {
    }
    
    /**
     * Obtém o arquivo do Conhecimento Roteiro.
     */
    @Column(length=500)
    public String getArquivo() {
        return arquivo;
    }
    
    /** Atribui o arquivo que contém o roteiro. */
    public void setArquivo(String parArquivo) {
        arquivo = parArquivo;
    }
    
    /**
     * Obtém o KArtefato ao qual o roteiro se aplica.
     */
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KArtefato.class)
    public KArtefato getKArtefato() {
        return kArtefato;
    }
    
    /** Atribui o kartefato para qual este roteiro se aplica. */
    public void setKArtefato(KArtefato parKArtefato) {
        kArtefato = parKArtefato;
    }
    
        public String obterNomeCanonico() {
        return "Roteiro";
    }
    
    
}
