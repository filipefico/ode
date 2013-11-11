package ode.processoPadrao.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class InterfaceCompPP extends ObjetoPersistente {

	private static final long serialVersionUID = -1819692208262943582L;
	String nome = "";
	String descricao = "";
	String objetivo = "";
	CompPP compPP;

	EstruturaCompPP estruturaCompPP;

	public InterfaceCompPP(String nome, String objetivo, String descricao,	CompPP compPP) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.descricao = descricao;
		this.compPP = compPP;
		this.estruturaCompPP = new EstruturaCompPP();
	}

	public InterfaceCompPP() {
		estruturaCompPP = new EstruturaCompPP();
	}

	// @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity =
	// CompPP.class)
	@OneToOne(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST })
	public CompPP getCompPP() {
		return compPP;
	}

	public void setCompPP(CompPP compPP) {
		this.compPP = compPP;
	}

	/**
	 * Obtém o a descricao da interface
	 * 
	 */
	@Column(nullable = false, length = 500)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Obtém o nome da interface
	 * 
	 */
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o objetivo da interface
	 * 
	 */
	@Column(nullable = false, length = 500)
	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	@OneToOne(cascade = { javax.persistence.CascadeType.MERGE,javax.persistence.CascadeType.PERSIST }, targetEntity = EstruturaCompPP.class, fetch = FetchType.EAGER, optional = true)
	public EstruturaCompPP getEstruturaCompPP() {
		return estruturaCompPP;
	}

	public void setEstruturaCompPP(EstruturaCompPP estruturaCompPP) {
		this.estruturaCompPP = estruturaCompPP;
	}
	
	@Override
	public InterfaceCompPP clone() throws CloneNotSupportedException {
		InterfaceCompPP copia = new InterfaceCompPP();
		
		copia.setNome(new String(this.getNome().toString()));
		copia.setDescricao(new String(this.getDescricao().toString()));
		copia.setObjetivo(new String(this.getObjetivo()).toString());
		copia.setCompPP(this.getCompPP()); // Não vejo necessidade de copiar esse CompPP, 2 CompPPs podem ter a mesma interface
		copia.setEstruturaCompPP(this.getEstruturaCompPP().clone());
					
		return copia;
	}

}
