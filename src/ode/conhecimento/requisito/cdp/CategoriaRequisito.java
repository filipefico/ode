package ode.conhecimento.requisito.cdp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ode.conhecimento.principal.cdp.Conhecimento;

@Entity
public class CategoriaRequisito extends Conhecimento{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5347949844743355423L;
	
	private CategoriaRequisito superCategoria;
	private TipoRequisito tipoRequisito;

	public CategoriaRequisito() {
    }

	@ManyToOne (fetch = FetchType.LAZY)
	public CategoriaRequisito getSuperCategoria(){
		return superCategoria;
	}

	public void setSuperCategoria(CategoriaRequisito parSuperKTipoRequisito){
		superCategoria = parSuperKTipoRequisito;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public TipoRequisito getTipoRequisito() {
		return tipoRequisito;
	}

	public void setTipoRequisito(TipoRequisito tipoRequisito) {
		this.tipoRequisito = tipoRequisito;
	}

	public String toString(){
		return super.getNome();
	}
}
