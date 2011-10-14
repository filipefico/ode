package ode.processoPadrao.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class InterfaceCompPP extends ObjetoPersistente {

	private static final long serialVersionUID = -1819692208262943582L;
	String nome;
	String descricao;
	String objetivo;
	CompPP compPP;

	EstruturaCompPP estruturaCompPP;
		

	public InterfaceCompPP(String nome, String objetivo, String descricao,
			CompPP compPP) {
		this.nome = nome;
		this.objetivo = objetivo;
		this.descricao = descricao;
		this.compPP = compPP;
		this.estruturaCompPP = new EstruturaCompPP();
		this.estruturaCompPP.setInterafeCompPP(this);
	}

	public InterfaceCompPP() {
	}

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPP.class)
	public CompPP getCompPP() {
		return compPP;
	}

	public void setCompPP(CompPP compPP) {
		this.compPP = compPP;
	}

	/**
	 * Obt�m o a descricao da interface
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
	 * Obt�m o nome da interface
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
	 * Obt�m o objetivo da interface
	 * 
	 */
	@Column(nullable = false, length = 500)
	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	public EstruturaCompPP getEstruturaCompPP() {
		return estruturaCompPP;
	}

	public void setEstruturaCompPP(EstruturaCompPP estruturaCompPP) {
		this.estruturaCompPP = estruturaCompPP;
	}



}
