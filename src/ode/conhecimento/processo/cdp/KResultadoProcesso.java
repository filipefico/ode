package ode.conhecimento.processo.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class KResultadoProcesso extends Conhecimento {
	
	private KProcesso kProcesso;
	
	public KResultadoProcesso() {}
	
	public KProcesso getKProcesso() {
		return kProcesso;
	}
	
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL, targetEntity = KProcesso.class)
	public void setKProcesso(KProcesso parKProcesso) {
		this.kProcesso = parKProcesso;
	}
	public String toString() {
		if (kProcesso!=null) {
			String sigla = kProcesso.getSigla();
			if (sigla != null && sigla.length()>0){
				return kProcesso.getSigla() + "." + this.getNome();
			}
		}
		return this.getNome();
	}
}
