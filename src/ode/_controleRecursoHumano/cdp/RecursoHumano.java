package ode._controleRecursoHumano.cdp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.gerenciaConhecimento.cdp.Tema;

@Entity
public class RecursoHumano extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;

	protected String nome;
	private boolean ativo;
	private Integer cargaHoraria;
	private String telefone;
	private String email;
	private KRecursoHumano cargo;
	private List<Tema> temasInteresse;

	public RecursoHumano() {
		setAtivo(true);
	}

	@Column(length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String parNome) {
		nome = parNome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean parAtivo) {
		ativo = parAtivo;
	}

	public String toString() {
		return nome;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne
	public KRecursoHumano getCargo() {
		return cargo;
	}

	public void setCargo(KRecursoHumano cargo) {
		this.cargo = cargo;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public List<Tema> getTemasInteresse() {
		return temasInteresse;
	}

	public void setTemasInteresse(List<Tema> temasInteresse) {
		this.temasInteresse = temasInteresse;
	}
	
}