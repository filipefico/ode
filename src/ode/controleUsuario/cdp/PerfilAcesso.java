package ode.controleUsuario.cdp;

/**
 * Classe que representa perfil de acesso. Utilizada para gerenciar permissões
 * de acesso às funcionalidades do sistema.
 * 
 */
public enum PerfilAcesso {

	Administrador("Administrador", 1), Desenvolvedor("Desenvolvedor", 2);

	private String nome;
	private int id;

	private PerfilAcesso(String nome, int id) {
		this.nome = nome;
		this.setId(id);
	}

	public String toString() {
		return this.getNome();
	}

	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static PerfilAcesso obterPorId(int id) {
		for (PerfilAcesso perfil : PerfilAcesso.values()) {
			if (perfil.getId() == id)
				return perfil;
		}
		return null;
	}

}