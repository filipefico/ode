package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.KAtividade;

@Entity
public class AtividadeMensuravel extends EntidadeMensuravel<KAtividade>{

	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.ATIVIDADE;

	
	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}


	@Override
	public KAtividade getEntidade() {
		return entidade;
	}


	@Override
	public void setEntidade(KAtividade entidade) {
		this.entidade = entidade;
	}

}
