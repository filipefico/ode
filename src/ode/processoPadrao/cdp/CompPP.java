package ode.processoPadrao.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CompPP extends ObjetoPersistente implements Cloneable {

	private static final long serialVersionUID = -6201008355921694079L;

	/** Indica se a definição do processo foi encerrada */
	private boolean definicaoConcluida;

	private String requisitoCompPP = "";

	private CompPP compPPBase;
	
	private InterfaceCompPP interfaceCompPP;

	
	
	
	// Metodos:
	
	public CompPP() {
		interfaceCompPP = new InterfaceCompPP();
		interfaceCompPP.setCompPP(this);
	}

	// Verifica se um Processo já teve sua definição encerrada
	@Column
	public boolean isDefinicaoConcluida() {
		return definicaoConcluida;
	}

	public void setDefinicaoConcluida(boolean parEhDefinido) {

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

	@OneToOne(cascade = { javax.persistence.CascadeType.ALL }, targetEntity = InterfaceCompPP.class)
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

	public String getRequisitoCompPP() {
		return requisitoCompPP;
	}

	// @ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPP.class)
	@ManyToOne(cascade = { javax.persistence.CascadeType.ALL }, targetEntity = CompPP.class)
	public CompPP getCompPPBase() {
		return compPPBase;
	}

	public void setCompPPBase(CompPP compPPBase) {
		this.compPPBase = compPPBase;
	}

	public String getObjetivo() {
		return this.interfaceCompPP.getObjetivo();
	}

	public void setObjetivo(String objetivo) {
		this.interfaceCompPP.setObjetivo(objetivo);
	}


	@Override
	protected CompPP clone() throws CloneNotSupportedException {
		CompPP copia = null;
		try {

			copia = this.getClass().newInstance(); // cria uma instancia da classe associada.
			copia.setCompPPBase(this);
			copia.setInterfaceCompPP(this.getInterfaceCompPP().clone());
			copia.setRequisitoCompPP(new String(this.getRequisitoCompPP()));
			copia.definicaoConcluida = false;

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return copia;
	}

}