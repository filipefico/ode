package ode.gerenciaConhecimento.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cgd.ConhecimentoRelativoDiscussaoDAO;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarConhecimentoRelativoDiscussao extends
		AplCRUD<ConhecimentoRelativoDiscussao> {

	@Autowired
	private ConhecimentoRelativoDiscussaoDAO conhecimentoRelativoDiscussaoDAO;
	
	@Override
	public DAOBase<ConhecimentoRelativoDiscussao> getNucleoDaoBase() {
		return conhecimentoRelativoDiscussaoDAO;
	}
	
}
