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
public class KObjetivoEstrategico extends KObjetivo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799798574645830161L;
	
	
	private Set<KObjetivoSoftware> objetivoSoftware;

	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy="objetivoEstrategico")
	public Set<KObjetivoSoftware> getObjetivoSoftware() {
		return objetivoSoftware;
	}

	public void setObjetivoSoftware(Set<KObjetivoSoftware> objetivosSoftware) {
		this.objetivoSoftware = objetivosSoftware;
	}

}
