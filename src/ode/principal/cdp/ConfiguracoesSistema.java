package ode.principal.cdp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import ode.controleUsuario.cdp.Funcionalidade;
import ode.nucleo.cgd.ObjetoPersistente;

import org.hibernate.annotations.IndexColumn;

@Entity
public class ConfiguracoesSistema extends
		ObjetoPersistente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Funcionalidades raíz do sistema
	private List<Funcionalidade> funcionalidadesSistema = new ArrayList<Funcionalidade>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONFIGURACOES_SISTEMA_ID")
	@IndexColumn(name = "POS")
	public List<Funcionalidade> getFuncionalidadesSistema() {
		return funcionalidadesSistema;
	}

	public void setFuncionalidadesSistema(
			List<Funcionalidade> funcionalidadesSistema) {
		this.funcionalidadesSistema = funcionalidadesSistema;
	}
	
	/**
	 * Adiciona uma subfuncionalidade
	 * 
	 * @param subFuncionalidade
	 *            Sub-grupo a ser adicionado
	 * 
	 */
	public void adicionarSubfuncionalidade(Funcionalidade subFuncionalidade) {
		subFuncionalidade.setFuncionalidadePai(null);
		this.getFuncionalidadesSistema().add(subFuncionalidade);
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
		for (Funcionalidade funcionalidade : this.getFuncionalidadesSistema()) {
			if (funcionalidade == subFuncionalidade) {
				indiceDeletar = i;
				break;
			}
			i++;
		}
		if (indiceDeletar != -1) {
			this.getFuncionalidadesSistema().remove(indiceDeletar);
		}
	}

}
