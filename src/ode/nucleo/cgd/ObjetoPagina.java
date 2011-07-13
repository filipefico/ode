package ode.nucleo.cgd;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class ObjetoPagina {
	
	//Lista de criterios da busca
	List<Criterion> criterios;
	
	int paginaAtual;
	
	public int getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(int paginaAtual) {
		this.paginaAtual = paginaAtual;
	}


	int maxResults;
	
	int firstResults;
	
	boolean isPaginada = true;
	
	Order criterioOrdenacao;
		
	public Order getCriterioOrdenacao() {
		return criterioOrdenacao;
	}

	public void setCriterioOrdenacao(Order criterioOrdenacao) {
		this.criterioOrdenacao = criterioOrdenacao;
	}

	public List<Criterion> getCriterios() {
		return criterios;
	}

	public void setCriterios(List<Criterion> criterios) {
		this.criterios = criterios;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public boolean isPaginada() {
		return isPaginada;
	}

	public void setPaginada(boolean isPaginada) {
		this.isPaginada = isPaginada;
	}

	public int getFirstResults() {
		return firstResults;
	}

	public void setFirstResults(int firstResults) {
		this.firstResults = firstResults;
	}

	
	public static ObjetoPagina factoryObjetoPagina( int parFirstResults,int parMaxResults, List<Criterion> parCriterios){
		ObjetoPagina referencia = new ObjetoPagina();
		referencia.setMaxResults(parMaxResults);
		referencia.setFirstResults(parFirstResults);
		referencia.setCriterios(parCriterios);
		return referencia;			
		
	}
	

}
