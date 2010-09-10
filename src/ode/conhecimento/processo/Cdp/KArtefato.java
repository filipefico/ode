package ode.conhecimento.processo.Cdp;

import java.util.Set;

import javax.persistence.Entity;

import ode.conhecimento.principal.Cdp.Conhecimento;


/**
 * Representa os conhecimentos sobre Artefatos do ambiente ODE.
 *
 */
@Entity(name="conh_proc_kartefato")
public class KArtefato extends Conhecimento {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7090636312647876455L;
	/** Tipo do Conhecimento Artefato. */
    private Set subArtefatos;
    private Set dependencias;
    private TipoKArtefato tipo;
    
    /**Construtor. */
    public KArtefato() {
    }
    
    /** Obtém os sub-artefatos do Conhecimento Artefato.
     *
     */
    public Set getSubArtefatos() {
        return subArtefatos;
    }
    
    /** Atribui os sub-artefatos do Conhecimento Artefato. */
    public void setSubArtefatos(Set parArtefatos) {
        subArtefatos = parArtefatos ;
    }
    
    /** Obtém as dependencias do Conhecimento Artefato.
     *
     *
     */
    public Set getDependencias() {
        return dependencias;
    }
    
    /** Atribui as dependencias do Conhecimento Artefato. */
    public void setDependencias(Set parDependencias) {
        dependencias = parDependencias;
    }
    
    
    /**
     * @hibernate.many-to-one
     *   column = "idotipo"
     *   not-null = "true"
     *   class = "Ode.Conhecimento.Processo.Cdp.TipoKArtefato"
     */
    public TipoKArtefato getTipo() {
        return tipo;
    }
    
    /** Atribui as dependencias do Conhecimento Artefato. */
    public void setTipo(TipoKArtefato parTipo) {
        this.tipo = parTipo;
    }
}