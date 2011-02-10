package ode.conhecimento.processo.Cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ode.conhecimento.principal.Cdp.Conhecimento;
import ode.conhecimento.processo.Cdp.KProcesso;

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
