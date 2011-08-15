package ode.controleUsuario.cdp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import ode.nucleo.cdp.ObjetoPersistente;

/**
 * Classe que representa perfil de acesso. Utilizada para gerenciar
 * permissões de acesso às funcionalidades do sistema.
 * 
 */
@Entity
public class PerfilAcesso extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nome do perfil de acesso.
	 */
	private String nome;

	/**
	 * Funcionalidades permitidas.
	 */
	private List<Funcionalidade> funcionalidadesPermitidas = new ArrayList<Funcionalidade>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public List<Funcionalidade> getFuncionalidadesPermitidas() {
		return funcionalidadesPermitidas;
	}

	public void setFuncionalidadesPermitidas(List<Funcionalidade> funcionalidadesPermitidas) {
		this.funcionalidadesPermitidas = funcionalidadesPermitidas;
	}

}
