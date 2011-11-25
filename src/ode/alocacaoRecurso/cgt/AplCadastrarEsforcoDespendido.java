package ode.alocacaoRecurso.cgt;

import java.util.Date;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.EsforcoDespendido;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;
import ode.alocacaoRecurso.cgd.AlocacaoRHDAO;
import ode.alocacaoRecurso.cgd.EsforcoDespendidoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zul.Messagebox;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarEsforcoDespendido extends AplCRUD<EsforcoDespendido> {

	@Autowired
	private EsforcoDespendidoDAO esforcoDespendidoDAO;
	
	@Autowired
	private AlocacaoRHDAO alocacaoRHDAO;
	
	@Override
	public DAOBase<EsforcoDespendido> getNucleoDaoBase() {
		return esforcoDespendidoDAO;
	}

	public List<EsforcoDespendido> recuperarPorAlocacaoRH(Long idAlocacao) {
		return esforcoDespendidoDAO.recuperarPorAlocacaoRH(idAlocacao);
	}

	@Override
	protected void depoisIncluirNovo(EsforcoDespendido esforco) throws NucleoRegraNegocioExcecao {
		super.depoisIncluirNovo(esforco);
		AlocacaoRH a = esforco.getAlocacaoRH(); 
		if(a.getEstado().equals(EstadoAlocacaoRH.AguardandoInicioParticipacao)) {
			a.setEstado(EstadoAlocacaoRH.EmAndamento);
			a.setDtInicioEfetivo(new Date());
			alocacaoRHDAO.atualizar(a);
			try {
				Messagebox.show("Sua participação na atividade foi iniciada!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
