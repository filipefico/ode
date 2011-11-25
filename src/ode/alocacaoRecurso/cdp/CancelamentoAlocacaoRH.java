package ode.alocacaoRecurso.cdp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class CancelamentoAlocacaoRH extends ObjetoPersistente {

	private static final long serialVersionUID = 1L;
	
	private AlocacaoRH alocacaoRH;
	private String motivo;
	private Date data;
	private EstadoAlocacaoRH estadoAnterior;

	@OneToOne
	public AlocacaoRH getAlocacaoRH() {
		return alocacaoRH;
	}
	public void setAlocacaoRH(AlocacaoRH alocacaoRH) {
		this.alocacaoRH = alocacaoRH;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Enumerated(EnumType.STRING)
	public EstadoAlocacaoRH getEstadoAnterior() {
		return estadoAnterior;
	}
	public void setEstadoAnterior(EstadoAlocacaoRH estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

}
