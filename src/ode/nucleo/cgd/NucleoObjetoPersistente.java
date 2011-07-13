package ode.nucleo.cgd;

import java.io.Serializable;

import ode.nucleo.cdp.NucleoObjetoDominio;



public interface NucleoObjetoPersistente<I extends Serializable, V extends Serializable>
		extends NucleoObjetoDominio {

	/**
	 * Retorna o atributo identificador (chave-primária).
	 * 
	 * @return O identificador do objeto.
	 */
	I getId();

	/**
	 * Retorna o atributo de versionamento.
	 * 
	 * @return Atributo de versão.
	 */
	V getVersion();

	/**
	 * Verifica se o objeto é persistente.
	 * 
	 * @return <code>true</code> se o objeto é persistente, <code>false</code>
	 *         caso contrário.
	 */
	boolean isPersistente();

}
