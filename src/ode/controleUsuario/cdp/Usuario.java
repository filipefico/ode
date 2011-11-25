package ode.controleUsuario.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;


/**
 * Usuário do sistema.
 * 
 * @author Alexandre G. N. Coelho
 * 
 */
@Entity
public class Usuario extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private String senha;

	private RecursoHumano recursoHumano;

	private PerfilAcesso perfilAcesso;

	@Column(length = 50)
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	@Column(length = 50)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@OneToOne(optional=false)
	public RecursoHumano getRecursoHumano() {
		return recursoHumano;
	}

	public void setRecursoHumano(RecursoHumano recursoHumano) {
		this.recursoHumano = recursoHumano;
	}
	
	@Enumerated(EnumType.STRING)
	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

}
