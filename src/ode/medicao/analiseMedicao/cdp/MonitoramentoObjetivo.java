package ode.medicao.analiseMedicao.cdp;

import java.util.Date;

import javax.persistence.Entity;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class MonitoramentoObjetivo extends ObjetoPersistente{

	private Date data ;
	private RecursoHumano responsavel;
	
	
	private String gambiarra;
	public MonitoramentoObjetivo(String nome){
		gambiarra = nome;
	}
	public String toString(){
		return gambiarra;
	}
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public RecursoHumano getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(RecursoHumano responsavel) {
		this.responsavel = responsavel;
	}
	
	
}
