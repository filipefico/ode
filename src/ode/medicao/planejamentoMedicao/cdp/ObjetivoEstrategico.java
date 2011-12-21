package ode.medicao.planejamentoMedicao.cdp;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class ObjetivoEstrategico extends Objetivo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799798574645830161L;
	
	
	private Set<ObjetivoSoftware> objetivoSoftware;

	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy="objetivoEstrategico")
	public Set<ObjetivoSoftware> getObjetivoSoftware() {
		return objetivoSoftware;
	}

	public void setObjetivoSoftware(Set<ObjetivoSoftware> objetivosSoftware) {
		this.objetivoSoftware = objetivosSoftware;
	}

}
