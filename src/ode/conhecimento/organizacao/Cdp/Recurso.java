package ode.controle.controleProcesso.Cdp;

import nucleo.comuns.persistencia.ObjetoPersistente;
import ode.conhecimento.processo.Cdp.KRecurso;

/**************************** Classe Recurso **********************************/
/** Representa os recursos do ambiente ODE.                                   */

/**
 *  @hibernate.class table="ctrl_proc_recurso"
 */
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
    
    /**
     * @hibernate.property length="80"
     */
    public String getNome() {
        return nome;
    }
    
    public void setNome(String parNome) {
        nome = parNome;
    }
    
    /**
     * @hibernate.property
     */
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean parAtivo) {
        ativo = parAtivo;
    }
    
    public abstract KRecurso getConhecimento();
    
    public abstract void setConhecimento(KRecurso parConhecimento);
    
    public String toString(){
        return nome;
    }
}