package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

@Entity
public class AtividadeMensuravel extends EntidadeMensuravel<KAtividade>{

	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.ATIVIDADE;
	
	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}

}
