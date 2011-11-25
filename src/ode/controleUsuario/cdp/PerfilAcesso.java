package ode.controleUsuario.cdp;

/**
 * Classe que representa perfil de acesso. Utilizada para gerenciar permissões
 * de acesso às funcionalidades do sistema.
 * 
 */
public enum PerfilAcesso {

	Administrador("Administrador"),
	Desenvolvedor("Desenvolvedor");

	private String label;
	
	private PerfilAcesso(String nome) {
		this.label = nome;
	}

	public String toString() {
		return this.label;
	}
}