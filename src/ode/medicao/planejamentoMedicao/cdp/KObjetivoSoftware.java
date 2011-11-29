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
public class KObjetivoSoftware extends KObjetivo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1860872188027432039L;

	private Set<KObjetivoEstrategico> objetivoEstrategico;
	private Set<KObjetivoMedicao> objetivoMedicao;

	public static final String SoftEstr = "KObjetivoSoftware_KObjetivoEstrategico";

	@ManyToMany(fetch = FetchType.EAGER, mappedBy="objetivosSoftware")
	public Set<KObjetivoMedicao> getObjetivoMedicao() {
		return objetivoMedicao;
	}

	public void setObjetivoMedicao(Set<KObjetivoMedicao> objetivoMedicao) {
		this.objetivoMedicao = objetivoMedicao;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = KObjetivoSoftware.SoftEstr)
	public Set<KObjetivoEstrategico> getObjetivoEstrategico() {
		return objetivoEstrategico;
	}

	public void setObjetivoEstrategico(
			Set<KObjetivoEstrategico> objetivoEstrategico) {
		this.objetivoEstrategico = objetivoEstrategico;
	}

	public boolean hasObjetivoEstrategico(KObjetivoEstrategico obj) {
		return objetivoEstrategico.contains(obj);
	}


}
