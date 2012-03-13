package ode.gerenciaConhecimento.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cgd.LicaoAprendidaDAO;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarLicaoAprendida extends AplCRUD<LicaoAprendida> {

	@Autowired
	private LicaoAprendidaDAO licaoAprendidaDAO;
	
	@Override
	public DAOBase<LicaoAprendida> getNucleoDaoBase() {
		return licaoAprendidaDAO;
	}

}
