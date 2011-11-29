package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

@Entity
public class ArtefatoMensuravel extends EntidadeMensuravel<KArtefato>{

	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.ARTEFATO;
	
	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}

}
