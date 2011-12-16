package ode.processoPadrao.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.conhecimento.processo.cdp.KModeloCicloVida;

@Entity
public class ModeloCicloVidaProcessoPadrao extends ObjetoPersistente {

	private static final long serialVersionUID = -1966924825919978194L;
	/** Processo Padr�o Espec�fico associado */
	private CompPPProcessoSimples processosEspecifico;
	/** KModeloCicloVida referente */
	private KModeloCicloVida kModeloCicloVida;

	/** Combinacoes para o ModeloCicloVida */

	/**
	 * Obt�m o Processo Padr�o Espec�fico. not-null = "true"
	 */
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = CompPPProcessoSimples.class)
	public CompPPProcessoSimples getProcessosEspecifico() {
		return processosEspecifico;
	}

	public void setProcessosEspecifico(
			CompPPProcessoSimples parProcessosEspecifico) {
		this.processosEspecifico = parProcessosEspecifico;
	}

	/**
	 * Obt�m o KModeloCicloVida refernete a este. not-null = "false"
	 * 
	 */
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KModeloCicloVida.class)
	public KModeloCicloVida getKModeloCicloVida() {
		return kModeloCicloVida;
	}

	public void setKModeloCicloVida(KModeloCicloVida parKModeloCicloVida) {
		this.kModeloCicloVida = parKModeloCicloVida;
	}

	public String toString() {
		// return this.nome;
		return kModeloCicloVida.toString();
	}

}
