package ode.controleUsuario.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode.nucleo.cdp.ObjetoPersistente;


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

	private int perfilAcessoId;

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
	
	public int getPerfilAcessoId() {
		return perfilAcessoId;
	}

	public void setPerfilAcessoId(int perfilAcessoId) {
		this.perfilAcessoId = perfilAcessoId;
	}
	
	@Transient
	public PerfilAcesso getPerfilAcesso() {
		return PerfilAcesso.obterPorId(this.perfilAcessoId);
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcessoId = perfilAcesso.getId();
	}

}
