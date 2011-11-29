package ode.medicao.EntidadeMensuravel.cdp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimentoMedicao.cdp.TipoEntidadeMensuravel;

@Entity
public class ProcessoProjetoEspecificoMensuravel extends EntidadeMensuravel<KProcesso>{

	private final TipoEntidadeMensuravel tipo = TipoEntidadeMensuravel.PROCESSOESPECIFICOPROJETO;
	
	private ProcessoPadraoMensuravel processoPadraoMensuravel;
	
	@Override
	public TipoEntidadeMensuravel recuperaTipo() {
		return tipo;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	public ProcessoPadraoMensuravel getProcessoPadraoMensuravel() {
		return processoPadraoMensuravel;
	}

	public void setProcessoPadraoMensuravel(ProcessoPadraoMensuravel processoPadraoMensuravel) {
		this.processoPadraoMensuravel = processoPadraoMensuravel;
	}

}
