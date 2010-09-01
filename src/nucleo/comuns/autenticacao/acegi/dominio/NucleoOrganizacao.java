package nucleo.comuns.autenticacao.acegi.dominio;

import javax.persistence.Entity;

import nucleo.comuns.persistencia.NucleoObjetoPersistenteImpl;


@Entity
public class NucleoOrganizacao extends NucleoObjetoPersistenteImpl<Long, Long> {

	private static final long serialVersionUID = 1144168960171946495L;

	private String nome;

	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		NucleoOrganizacao outro = (NucleoOrganizacao) obj;
		return this.nome.equalsIgnoreCase(outro.nome);
	}

	@Override
	public String toString() {
		return this.getNome();
	}

}
