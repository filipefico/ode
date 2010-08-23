package ode.conhecimento.processo.Cdp;

import java.util.Set;

import javax.persistence.Entity;

import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity(name="conh_proc_kartefato")
/**
 * Representa os conhecimentos sobre Artefatos do ambiente ODE.
 *
 *@hibernate.joined-subclass
 *      table = "conh_proc_kartefato"
 *@hibernate.joined-subclass-key
 *      column = "id"
 */
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
     *@hibernate.set
     *   table = "conh_proc_ksubartefato"
     *   lazy="true"
     *@hibernate.collection-key
     *   column = "idkartefato"
     *@hibernate.collection-many-to-many
     *   column ="idksubartefato"
     *   class="Ode.Conhecimento.Processo.Cdp.KArtefato"
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
     *@hibernate.set
     *   table = "conh_proc_kdependenciaartefato"
     *   lazy="true"
     *@hibernate.collection-key
     *   column = "idkartefato"
     *@hibernate.collection-many-to-many
     *   column ="idkartefatodependente"
     *   class="Ode.Conhecimento.Processo.Cdp.KArtefato"
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