package ode.resolucaoProblema.cdp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode.problema.cdp.KCriterioSelecaoSolucao;

@Entity
public class AvaliacaoSolucaoOcorrenciaProblema extends ObjetoPersistente {

	
	private static final long serialVersionUID = -1785076271780283043L;
	
	private KCriterioSelecaoSolucao kcriterioselecaosolucao;
	
//	private KValorCriterioSelecaoSolucao kvalorcriterioselecaosolucao;
	
	private RecursoHumano recursohumano;
@ManyToOne
	public KCriterioSelecaoSolucao getKcriterioselecaosolucao() {
		return kcriterioselecaosolucao;
	}

	public void setKcriterioselecaosolucao(
			KCriterioSelecaoSolucao kcriterioselecaosolucao) {
		this.kcriterioselecaosolucao = kcriterioselecaosolucao;
	}
//@ManyToOne
//	public KValorCriterioSelecaoSolucao getKvalorcriterioselecaosolucao() {
//		return kvalorcriterioselecaosolucao;
//	}

//	public void setKvalorcriterioselecaosolucao(
//			KValorCriterioSelecaoSolucao kvalorcriterioselecaosolucao) {
//		this.kvalorcriterioselecaosolucao = kvalorcriterioselecaosolucao;
//	}
@ManyToOne
	public RecursoHumano getrecursohumano() {
		return recursohumano;
	}

	public void setrecursohumano(RecursoHumano recursohumano) {
		this.recursohumano = recursohumano;
	}
	
	

}
