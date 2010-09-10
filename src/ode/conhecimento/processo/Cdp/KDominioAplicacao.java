package ode.conhecimento.processo.Cdp;
import ode.conhecimento.principal.Cdp.Conhecimento;

import javax.persistence.Column;
import javax.persistence.Entity;

//import nucleo.comuns.persistencia.ObjetoPersistente;
//ObjetoPersistente

/****************** Classe KDominioAplicacao *************************/


/** Representa os conhecimentos sobre Dominio da Aplicacao do
 * ambiente ODE
 *
 *@hibernate.joined-subclass
 *      table = "conh_proc_kdominioaplicacao"
 *@hibernate.joined-subclass-key
 *      column = "id"
 */


@Entity
public class KDominioAplicacao extends Conhecimento  {

	private static final long serialVersionUID = 7215443991537131684L;

	private String nome;

	private String descricao;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome +" : " +descricao;
	}

	
	@Column(nullable = false, length = 60)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(nullable = false, length = 300)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	}
