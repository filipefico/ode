package ode.controleUsuario.cdp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ode.nucleo.cgd.ObjetoPersistente;

import org.hibernate.annotations.IndexColumn;

@Entity
public class Funcionalidade extends
		ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Nome da funcionalidade
	private String nome;

	// Caminho da Controlador da funcionalidade
	private String srcCtrl;
	
	// Verifica se a funcionalidade est� dispon�vel apenas para projetos abertos
	private boolean disponivelApenasParaProjetosAbertos;

	// Funcionalidade Pai
	private Funcionalidade funcionalidadePai;

	// Subfuncionalidades
	private List<Funcionalidade> subfuncionalidades = new ArrayList<Funcionalidade>();

	public Funcionalidade() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSrcCtrl() {
		return srcCtrl;
	}

	public boolean isDisponivelApenasParaProjetosAbertos() {
		return disponivelApenasParaProjetosAbertos;
	}

	public void setDisponivelApenasParaProjetosAbertos(
			boolean disponivelApenasParaProjetosAbertos) {
		this.disponivelApenasParaProjetosAbertos = disponivelApenasParaProjetosAbertos;
	}

	public void setSrcCtrl(String srcCtrl) {
		this.srcCtrl = srcCtrl;
	}

	@ManyToOne
	public Funcionalidade getFuncionalidadePai() {
		return funcionalidadePai;
	}

	public void setFuncionalidadePai(Funcionalidade funcionalidadePai) {
		this.funcionalidadePai = funcionalidadePai;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "FUNCIONALIDADE_ID")
	@IndexColumn(name = "POS")
	public List<Funcionalidade> getSubfuncionalidades() {
		return subfuncionalidades;
	}

	public void setSubfuncionalidades(
			List<Funcionalidade> subfuncionalidades) {
		this.subfuncionalidades = subfuncionalidades;
	}

	/**
	 * Adiciona uma subfuncionalidade
	 * 
	 * @param subFuncionalidade
	 *            Sub-grupo a ser adicionado
	 * 
	 */
	public void adicionarSubfuncionalidade(Funcionalidade subFuncionalidade) {
		subFuncionalidade.setFuncionalidadePai(this);
		this.getSubfuncionalidades().add(subFuncionalidade);
	}

	/**
	 * Remove uma subfuncionalidade.
	 * 
	 * @param subFuncionalidade
	 *            Subfuncionalidade a ser removida.
	 */
	public void removerSubfuncionalidade(Funcionalidade subFuncionalidade) {
		int indiceDeletar = -1;
		int i = 0;
		for (Funcionalidade funcionalidade : this.getSubfuncionalidades()) {
			if (funcionalidade == subFuncionalidade) {
				indiceDeletar = i;
				break;
			}
			i++;
		}
		if (indiceDeletar != -1) {
			this.getSubfuncionalidades().remove(indiceDeletar);
		}
	}
	
	/**
	 * Sobrescreve o toString.
	 */
	public String toString(){
		return this.getNome();
	}
}