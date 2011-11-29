package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;
import ode.controleProjeto.cdp.Projeto;

@Entity
public class ProjetoMensuravel extends EntidadeMensuravel<Projeto>{
	
	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.PROJETO; 

	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}
}
