package ode.conhecimento.processo.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.jcodings.util.Hash;

import ode.conhecimento.principal.cdp.Conhecimento;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

/**
 * Representa os conhecimentos sobre Artefatos do ambiente ODE.
 * 
 */
@Entity
public class KArtefato extends Conhecimento {

	private static final long serialVersionUID = 3692165438374399151L;
	/** Tipo do Conhecimento Artefato. */
	private Set<KArtefato> subArtefatos;
	private Set<KArtefato> dependencias;
	private TipoKArtefato tipo;

	/** Construtor. */
	public KArtefato() {
	}

	/** Obt�m os sub-artefatos do Conhecimento Artefato. */
	@ManyToMany
	@JoinTable(name="kartefato_subkartefato")
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
	@ManyToMany
	@JoinTable(name="kartefato_dependencia")
	public Set<KArtefato> getDependencias() {
		return dependencias;
	}

	/** Atribui as dependencias do Conhecimento Artefato. */
	public void setDependencias(Set<KArtefato> parDependencias) {
		dependencias = parDependencias;
	}

	@ManyToOne
	public TipoKArtefato getTipo() {
		return tipo;
	}

	/** Atribui as dependencias do Conhecimento Artefato. */
	public void setTipo(TipoKArtefato parTipo) {
		this.tipo = parTipo;
	}
	
	
}