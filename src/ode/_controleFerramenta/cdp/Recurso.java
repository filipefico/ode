package ode._controleFerramenta.cdp;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecurso;

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