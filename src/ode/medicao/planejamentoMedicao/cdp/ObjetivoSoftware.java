package ode.medicao.planejamentoMedicao.cdp;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class ObjetivoSoftware extends Objetivo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860872188027432039L;

	private Set<ObjetivoEstrategico> objetivoEstrategico;
	private Set<ObjetivoMedicao> objetivoMedicao;

	public static final String SoftEstr = "KObjetivoSoftware_KObjetivoEstrategico";

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="objetivosSoftware")
	public Set<ObjetivoMedicao> getObjetivoMedicao() {
		return objetivoMedicao;
	}

	public void setObjetivoMedicao(Set<ObjetivoMedicao> objetivoMedicao) {
		this.objetivoMedicao = objetivoMedicao;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = ObjetivoSoftware.SoftEstr)
	public Set<ObjetivoEstrategico> getObjetivoEstrategico() {
		return objetivoEstrategico;
	}

	public void setObjetivoEstrategico(
			Set<ObjetivoEstrategico> objetivoEstrategico) {
		this.objetivoEstrategico = objetivoEstrategico;
	}

	public boolean hasObjetivoEstrategico(ObjetivoEstrategico obj) {
		return objetivoEstrategico.contains(obj);
	}


}
