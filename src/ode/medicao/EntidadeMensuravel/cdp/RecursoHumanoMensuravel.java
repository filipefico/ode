package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

@Entity
public class RecursoHumanoMensuravel extends EntidadeMensuravel<KRecursoHumano>{

	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.RECURSOHUMANO;
	
	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}

}
