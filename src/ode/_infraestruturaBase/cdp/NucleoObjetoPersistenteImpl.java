package ode._infraestruturaBase.cdp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * @version 1.0
 * @created 28-jul-2006 08:51:55
 */
@MappedSuperclass
public class NucleoObjetoPersistenteImpl<I, V>
		extends NucleoObjetoDominioImpl implements NucleoObjetoPersistente<I, V>,
		Comparable<NucleoObjetoPersistenteImpl<I, V>> {

	/** Garante a eficácia da serialização */
	private static final long serialVersionUID = 4752855742755760839L;

	/** Atributo identificador (chave-primária). */
	private I id;

	/** Atributo de versionamento. */
	private V version;

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.comuns.persistencia.NucleoObjetoPersistente#getId()
	 */
	@Id
	@TableGenerator(name = "TableGenerator", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="TableGenerator")
	public I getId() {
		return id;
	}

	/**
	 * @param id
	 *            O id a ser setado.
	 */
	public void setId(I id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.comuns.persistencia.NucleoObjetoPersistente#getVersion()
	 */
	@Version
	@Column(nullable = false)
	public V getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            A versão a ser setada no objeto persistente
	 */
	public void setVersion(V version) {
		this.version = version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nucleo.comuns.persistencia.NucleoObjetoPersistente#isPersistente()
	 */
	@Transient
	public boolean isPersistente() {
		return (id != null);
	}

	/**
	 * Implementação padrão do método compareTo não faz nada de funcional.
	 * Compara os uuid's para se manter fiel ao método equals.
	 */
	public int compareTo(NucleoObjetoPersistenteImpl<I, V> o) {
		return getUuid().compareTo(o.getUuid());
	}

}