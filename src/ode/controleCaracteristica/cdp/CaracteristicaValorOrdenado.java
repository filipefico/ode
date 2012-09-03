package ode.controleCaracteristica.cdp;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class CaracteristicaValorOrdenado extends Caracteristica{
	
	private static final long serialVersionUID = 1L;
	
	private double valorMinimo;
	private double valorMaximo;
	
	public double getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public Collection<CaracteristicaValorOrdenado> recuperarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
   public void setValores(Set<PossivelValor> setValores){
       
	   	Iterator i= setValores.iterator();
        double maior, menor, atual;       
        
	        if (i.hasNext()) {
	            maior = menor = ((PossivelValorOrdenado) i.next()).getValor();
	            while (i.hasNext()){
	                atual = ((PossivelValorOrdenado) i.next()).getValor();
	                if (atual > maior)
	                    maior = atual;
	                if (atual < menor)    
	                    menor = atual;
	            }        
	            this.valorMaximo = maior;
	            this.valorMinimo = menor;
	        }        
	        super.possiveisValores = setValores;
        }
        
}