package ode.conhecimento.principal.Cdp;

import nucleo.comuns.persistencia.ObjetoPersistente;

public class Conhecimento extends ObjetoPersistente {

	private static final long serialVersionUID = -2477658761966661172L;

	/** Nome do conhecimento. */
	private String nome;
	/** Descricao do conhecimento. */
	private String descricao;

	/** Construtor. */
	public Conhecimento() {
	}

	/**
	 * Obtem nome do conhecimento.
	 * 
	 * @hibernate.property length="100"
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Atribui nome ao conhecimento.
	 */
	public void setNome(String parNome) {
		nome = parNome;
	}

	/**
	 * ObtÃ©m descricao do conhecimento.
	 * 
	 * @hibernate.property length="500"
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atribui descricao ao conhecimento.
	 */
	public void setDescricao(String parDescricao) {
		descricao = parDescricao;
	}

	/** Descreve o objeto Conhecimento detalhadamente. */
	public String sPubDescreverObjeto() {
		StringBuffer locDadosConhecimento = new StringBuffer();
		locDadosConhecimento.append(" *** CONHECIMENTO *** \n");
		locDadosConhecimento.append(" - Nome: ");
		locDadosConhecimento.append(getNome() + "\n");
		locDadosConhecimento.append(" - Descrição: ");
		locDadosConhecimento.append(getDescricao());
		return locDadosConhecimento.toString();
	}

	/** Sobreescreve toString(). */
	public String toString() {
		return this.nome;
	}

}
