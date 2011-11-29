package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

@Entity
public class ProcessoPadraoMensuravel extends EntidadeMensuravel<KProcesso>{

	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.PROCESSOPADRAO;
	
	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}

}
