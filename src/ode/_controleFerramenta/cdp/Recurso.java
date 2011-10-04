package ode._controleFerramenta.cdp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.Cdp.KRecurso;
import ode.nucleo.cdp.ObjetoPersistente;

@MappedSuperclass
public abstract class Recurso extends ObjetoPersistente{
    
	private static final long serialVersionUID = 1L;

    protected String nome;
    
    private boolean ativo;
    
    private KRecurso kRecurso;
    
    /** Construtor. */
    public Recurso() {
        setAtivo(true);
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
    
    @ManyToOne
    public KRecurso getKRecurso() {
    	return this.kRecurso;
    }
    
    public void setKRecurso(KRecurso kRecurso) {
    	this.kRecurso = kRecurso;
    }
       
    public String toString(){
        return nome;
    }

}