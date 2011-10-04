package ode._controleRecursoHumano.cdp;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.Cdp.KRecursoHumano;
import ode.nucleo.cdp.ObjetoPersistente;

@Entity
public class RecursoHumano extends ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Nome do Recurso. */
    protected String nome;
    
    /** true se o recurso esta ativo ou false se o recurso esta inativo */
    private boolean ativo;

	private Integer cargaHoraria;
	
	private String telefone;
	
	private String email;

	private KRecursoHumano cargo;

	private Set<KRecursoHumano> papeis;
	
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
       
    public String toString(){
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="recursohumano_papel")
	public Set<KRecursoHumano> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<KRecursoHumano> papeis) {
		this.papeis = papeis;
	}

	public void setCargo(KRecursoHumano cargo) {
		this.cargo = cargo;
	}

	@ManyToOne
	public KRecursoHumano getCargo() {
		return cargo;
	}	

}
