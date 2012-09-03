package ode.controleCaracteristica.cdp;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class PossivelValorNaoOrdenado extends PossivelValor {

	private static final long serialVersionUID = 1L;
	
	Set<Similaridade> similaridades;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	public Set<Similaridade> getSimilaridades(){
		return similaridades;
	}
	
	public void setSimilaridades(Set<Similaridade> simi){
		similaridades = simi;
	}
	
	public void addSimilaridade(Similaridade similaridade){
		similaridades.add(similaridade);
	}

}