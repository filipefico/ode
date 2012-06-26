package ode.resolucaoProblema.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;

@Entity
public class HistoricoOcorrenciaProblema extends ObjetoPersistente {

	
	private static final long serialVersionUID = 5077003111967344175L;

	private java.util.Date datahistorico;
	
	private RecursoHumano recursohumano;
	
	private EstadoOcorrencia estadoocorrencia;


@ManyToOne
	public RecursoHumano getrecursohumano() {
		return recursohumano;
	}

	public void setrecursohumano(RecursoHumano recursohumano) {
		this.recursohumano = recursohumano;
	}
@Enumerated(EnumType.STRING)
	public EstadoOcorrencia getEstadoocorrencia() {
		return estadoocorrencia;
	}

	public void setEstadoocorrencia(EstadoOcorrencia estadoocorrencia) {
		this.estadoocorrencia = estadoocorrencia;
	}
	
	@Column (nullable = false, length = 100)
	public java.util.Date getDatahistorico() {
		return datahistorico;
	}

	public void setDatahistorico(java.util.Date datahistorico) {
		this.datahistorico = datahistorico;
	}
	
	
}
