package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;

@Entity
public class ProcessoPadraoMensuravel extends EntidadeMensuravel<KProcesso>{

	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.PROCESSOPADRAO;
	
	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}

	@Override
	public KProcesso getEntidade() {
		return entidade;
	}

	@Override
	public void setEntidade(KProcesso entidade) {
		this.entidade = entidade;
	}
	
}
