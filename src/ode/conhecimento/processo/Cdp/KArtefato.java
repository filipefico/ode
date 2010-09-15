package ode.conhecimento.processo.Cdp;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;

import ode.conhecimento.principal.Cdp.Conhecimento;

/**
 * Representa os conhecimentos sobre Artefatos do ambiente ODE.
 * 
 */
@Entity
public class KArtefato extends Conhecimento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7090636312647876455L;
	/** Tipo do Conhecimento Artefato. */
	private Set<KArtefato> subArtefatos;
	private Set<KArtefato> dependencias;
	private TipoKArtefato tipo;

	/** Construtor. */
	public KArtefato() {
	}

	/**
	 * Obtém os sub-artefatos do Conhecimento Artefato.
	 * 
	 */
	// /**
	// *@hibernate.many-to-one
	// * column = "idotipo"
	// * not-null = "true"
	// * class = "Ode.Conhecimento.Processo.Cdp.TipoKArtefato"
	// */

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KArtefato.class)
	// @JoinColumn(name="COMP_ID")
	public Set<KArtefato> getSubArtefatos() {
		return subArtefatos;
	}

	/** Atribui os sub-artefatos do Conhecimento Artefato. */
	public void setSubArtefatos(Set<KArtefato> parArtefatos) {
		subArtefatos = parArtefatos;
	}

	/**
	 * Obtém as dependencias do Conhecimento Artefato.
	 * 
	 * 
	 */
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KArtefato.class)
	public Set<KArtefato> getDependencias() {
		return dependencias;
	}

	/** Atribui as dependencias do Conhecimento Artefato. */
	public void setDependencias(Set<KArtefato> parDependencias) {
		dependencias = parDependencias;
	}

	public TipoKArtefato getTipo() {
		return tipo;
	}

	/** Atribui as dependencias do Conhecimento Artefato. */
	public void setTipo(TipoKArtefato parTipo) {
		this.tipo = parTipo;
	}
}