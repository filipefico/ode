package ode.controleProcesso.cdp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import ode.nucleo.cdp.ObjetoPersistente;


/**************************** Classe Recurso **********************************/
/** Representa os recursos do ambiente ODE.                                   */

/**
 *  @hibernate.class table="ctrl_proc_recurso"
 */
@MappedSuperclass
public abstract class Recurso extends ObjetoPersistente{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Nome do Recurso. */
    protected String nome;
    
    /** true se o recurso esta ativo ou false se o recurso esta inativo */
    private boolean ativo;
    
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
       
    public String toString(){
        return nome;
    }

}