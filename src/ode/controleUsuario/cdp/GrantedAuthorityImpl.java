package ode.controleUsuario.cdp;

import javax.persistence.Entity;


import ode.nucleo.cdp.NucleoObjetoPersistenteImpl;

import org.acegisecurity.GrantedAuthority;


/**
 * Authority (role, papel) de um usuário no sistema.
 * 
 * @author Alexandre G. N. Coelho
 */
@Entity
public class GrantedAuthorityImpl extends NucleoObjetoPersistenteImpl<Long, Long>
		implements GrantedAuthority {

	private static final long serialVersionUID = 913991417797911309L;

	/**
	 * Authority do usuário Administrador
	 */
	public static final String AUTHORITY_ADMINISTRADOR = "ROLE_SUPERVISOR";

	/**
	 * Authority do usuário Comum
	 */
	public static final String AUTHORITY_USUARIO_COMUM = "ROLE_USER";

	private String authority;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
