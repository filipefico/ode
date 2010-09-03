package ode.conhecimento.processo.Cdp;

import java.util.Set;
import javax.persistence.Entity;
import ode.conhecimento.principal.Cdp.Conhecimento;

@Entity
public class KArtefato extends Conhecimento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7090636312647876455L;
	/** Tipo do Conhecimento Artefato. */
	private Set subArtefatos;
	private Set dependencias;
	private TipoKArtefato tipo;

	/** Construtor. */
	public KArtefato() {
	}

	public Set getSubArtefatos() {
		return subArtefatos;
	}

	/** Atribui os sub-artefatos do Conhecimento Artefato. */
	public void setSubArtefatos(Set parArtefatos) {
		subArtefatos = parArtefatos;
	}

	public Set getDependencias() {
		return dependencias;
	}

	/** Atribui as dependencias do Conhecimento Artefato. */
	public void setDependencias(Set parDependencias) {
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