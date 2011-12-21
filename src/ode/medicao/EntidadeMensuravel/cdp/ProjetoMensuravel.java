package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;

import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;
import ode.controleProjeto.cdp.Projeto;

@Entity
public class ProjetoMensuravel extends EntidadeMensuravel<Projeto>{
	
	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.PROJETO;
	private Projeto entidade; 

	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}

	@Override
	public Projeto getEntidade() {
		return entidade;
	}

	@Override
	public void setEntidade(Projeto entidade) {
		this.entidade = entidade;
	}



}
