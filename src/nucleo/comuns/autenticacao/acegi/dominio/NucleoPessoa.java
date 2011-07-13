package nucleo.comuns.autenticacao.acegi.dominio;

import javax.persistence.Entity;

import ode.nucleo.cgd.NucleoObjetoPersistenteImpl;



@Entity
public class NucleoPessoa extends NucleoObjetoPersistenteImpl<Long, Long> {

	private static final long serialVersionUID = 1144168960171946495L;

	private String nome;

	private String ramal;

	private String telefone;

	private String celular;

	private String email;

	private String funcao;

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		NucleoPessoa outro = (NucleoPessoa) obj;
		return this.nome.equalsIgnoreCase(outro.nome);
	}

	@Override
	public String toString() {
		return this.getNome();
	}

}
