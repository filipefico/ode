package ode.nucleo.cdp;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NucleoObjetoDominioImpl implements NucleoObjetoDominio {
	/**
	 * Garante a eficácia da serialização
	 */
	private static final long serialVersionUID = -7809234430654204592L;

	/** Identificador Universal Único (UUID). */
	private String uuid;

	/** Construtor. */
	public NucleoObjetoDominioImpl() {
		// Gera o UUID na construção do objeto.
		uuid = UUID.randomUUID().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.ufes.inf.labes.util.dominio.ObjetoDominio#getUuid()
	 */
	@Column(nullable = false, length = 40)
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the uuid.
	 * 
	 * @param uuid
	 *            The uuid.
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// Verifica se o outro objeto é nulo
		if (obj == null) {
			return false;
		}

		// Verifica se a classe é a mesma.
		if (!getClass().equals(obj.getClass()))
			return false;

		NucleoObjetoDominioImpl o = (NucleoObjetoDominioImpl) obj;

		// Verifica se o UUID é o mesmo.
		return uuid.equals(o.uuid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
}
