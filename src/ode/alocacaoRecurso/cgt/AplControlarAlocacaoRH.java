package ode.alocacaoRecurso.cgt;

import java.util.Date;

import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.CancelamentoAlocacaoRH;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;
import ode.alocacaoRecurso.cgd.CancelamentoAlocacaoRHDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplControlarAlocacaoRH {

	@Autowired
	private AlocacaoRHDAO alocacaoRHDAO;
	
	@Autowired
	private CancelamentoAlocacaoRHDAO cancelamentoAlocacaoRHDAO;

	public void cancelarAlocacaoRH(AlocacaoRH alocacaoRH, String motivo) {
		CancelamentoAlocacaoRH cancelamento = new CancelamentoAlocacaoRH();
		cancelamento.setMotivo(motivo);
		cancelamento.setData(new Date());
		cancelamento.setAlocacaoRH(alocacaoRH);
		cancelamento.setEstadoAnterior(alocacaoRH.getEstado());
		cancelamentoAlocacaoRHDAO.salvar(cancelamento);
		alocacaoRH.setEstado(EstadoAlocacaoRH.Cancelada);
		alocacaoRHDAO.atualizar(alocacaoRH);
	}

	public void anularCancelamentoAlocacaoRH(AlocacaoRH alocacaoRH) {
		CancelamentoAlocacaoRH cancelamento = cancelamentoAlocacaoRHDAO.recuperarPorAlocacaoRH(alocacaoRH.getId());
		alocacaoRH.setEstado(cancelamento.getEstadoAnterior());
		alocacaoRHDAO.atualizar(alocacaoRH);
		cancelamentoAlocacaoRHDAO.excluir(cancelamento);
	}
	
	public void solicitarEncerramentoParticipacao(AlocacaoRH alocacaoRH) {
		alocacaoRH.setEstado(EstadoAlocacaoRH.EmAvaliacaoEncerramento);
		alocacaoRHDAO.atualizar(alocacaoRH);
	}
	
	public void avaliarParticipacaodeRH(AlocacaoRH alocacaoRH, EstadoAlocacaoRH estado) {
		alocacaoRH.setEstado(estado);
		if(estado.equals(EstadoAlocacaoRH.Encerrada))
			alocacaoRH.setDtFimEfetivo(new Date());
		alocacaoRHDAO.atualizar(alocacaoRH);
	}
}
