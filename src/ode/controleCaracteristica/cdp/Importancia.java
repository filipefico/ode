package ode.controleCaracteristica.cdp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class Importancia extends ObjetoPersistente {
	private static final long serialVersionUID = 1L;
	
	private EnumNivelImportancia nivel;
	private boolean obrigatoriedade;
	private Caracteristica caracteristica;
	
	
	@Enumerated(EnumType.STRING)
	public EnumNivelImportancia getNivel() {
		return nivel;
	}

	public void setNivel(EnumNivelImportancia nivel) {
		this.nivel = nivel;
	}

	public boolean getObrigatoriedade() {
		return obrigatoriedade;
	}

	public void setObrigatoriedade(boolean obrigatoriedade) {
		this.obrigatoriedade = obrigatoriedade;
	}
		
	@ManyToOne
	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristicad) {
		caracteristica = caracteristicad;
	}

}
