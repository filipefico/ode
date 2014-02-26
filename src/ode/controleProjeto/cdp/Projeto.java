package ode.controleProjeto.cdp;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import ode._infraestruturaBase.cdp.ObjetoPersistente;



@Entity
@XmlRootElement
public class Projeto extends ObjetoPersistente {

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
	
	public String toString(){
		return this.nome;
	}

}
