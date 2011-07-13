package ode.nucleo.cgd;

import java.io.Serializable;

import ode.nucleo.cdp.NucleoObjetoDominio;



public interface NucleoObjetoPersistente<I extends Serializable, V extends Serializable>
		extends NucleoObjetoDominio {

	/**
	 * Retorna o atributo identificador (chave-prim�ria).
	 * 
	 * @return O identificador do objeto.
	 */
	I getId();

	/**
	 * Retorna o atributo de versionamento.
	 * 
	 * @return Atributo de vers�o.
	 */
	V getVersion();

	/**
	 * Verifica se o objeto � persistente.
	 * 
	 * @return <code>true</code> se o objeto � persistente, <code>false</code>
	 *         caso contr�rio.
	 */
	boolean isPersistente();

}
