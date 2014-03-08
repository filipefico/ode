package ode.middlewareIssueTracker.cdp;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.controleUsuario.cdp.Usuario;

@Entity
public class UsuarioMantis extends ObjetoPersistente {
	
	private String usuarioMantis;
	private String senhaMantis;
	
	private Usuario usuario;

	public String getUsuarioMantis() {
		return usuarioMantis;
	}

	public void setUsuarioMantis(String usuarioMantis) {
		this.usuarioMantis = usuarioMantis;
	}

	public String getSenhaMantis() {
		return senhaMantis;
	}

	public void setSenhaMantis(String senhaMantis) {
		this.senhaMantis = senhaMantis;
	}

	@OneToOne
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
