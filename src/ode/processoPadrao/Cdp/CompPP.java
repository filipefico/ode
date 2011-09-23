package ode.processoPadrao.Cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import ode.nucleo.cdp.ObjetoPersistente;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class CompPP extends ObjetoPersistente {

	private static final long serialVersionUID = -6201008355921694079L;

	/** Indica se a definição do processo foi encerrada */
	private boolean definicaoConcluida;

	/** Requisito */
	private String requisitoCompPP;
	/** Componente base */
	private CompPP compPPBase;

	private InterfaceCompPP interfaceCompPP;

	public CompPP() {
		interfaceCompPP = new InterfaceCompPP();
	}

	
	//Verifica se um Processo já teve sua definição encerrada
	@Column
	public boolean isEhDefinido() {
		return definicaoConcluida;
	}

	public void setEhDefinido(boolean parEhDefinido) {
	this.definicaoConcluida = parEhDefinido;
	}
	
	@Column(nullable = false, length = 100)
	public String getNome() {
		return this.interfaceCompPP.getNome();
	}

	public void setNome(String parNome) {
		this.interfaceCompPP.setNome(parNome);
	}

	@Column(nullable = true, length = 300)
	public String getDescricao() {
		return this.interfaceCompPP.getDescricao();
	}

	public void setDescricao(String parDescricao) {
		this.interfaceCompPP.setDescricao(parDescricao);
	}

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = InterfaceCompPP.class)
	public InterfaceCompPP getInterfaceCompPP() {
		return interfaceCompPP;
	}

	public void setInterfaceCompPP(InterfaceCompPP interfaceCompPP) {
		this.interfaceCompPP = interfaceCompPP;
	}

	public String toString() {
		if (this.interfaceCompPP != null)
			return this.interfaceCompPP.getNome();
		else
			return null;
	}

	public void setRequisitoCompPP(String requisitoCompPP) {
		this.requisitoCompPP = requisitoCompPP;
	}

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPP.class)
	public CompPP getCompPPBase() {
		return compPPBase;
	}

	public void setCompPPBase(CompPP compPPBase) {
		this.compPPBase = compPPBase;
	}
	
	public String getObjetivo(){
		return this.interfaceCompPP.getObjetivo();
	}
	
	public void setObjetivo(String objetivo){
		this.interfaceCompPP.setObjetivo(objetivo);
	}

}