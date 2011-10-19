package ode.conhecimentoMedicao.cdp;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class KObjetivoSoftware extends KObjetivo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1860872188027432039L;
	
	private Set<KObjetivoEstrategico> objetivosEstrategicos;

	public static final String SoftEstr = "KObjetivoSoftware_KObjetivoEstrategico";
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name=KObjetivoSoftware.SoftEstr)
	public Set<KObjetivoEstrategico> getObjetivosEstrategicos() {
		return objetivosEstrategicos;
	}

	public void setObjetivosEstrategicos(Set<KObjetivoEstrategico> objetivosEstrategicos) {
		this.objetivosEstrategicos = objetivosEstrategicos;
	}

	public boolean hasObjetivoEstrategico(KObjetivoEstrategico obj){
		return objetivosEstrategicos.contains(obj);
	}
	

}
