package ode.gerenciaConhecimento.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cgd.ItemConhecimentoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoRegraNegocioExcecao.class)
public class AplCadastrarItemConhecimento extends AplCRUD<ItemConhecimento> {

	@Autowired
	ItemConhecimentoDAO itemConhecimentoDAO;
	
	@Override
	public DAOBase<ItemConhecimento> getNucleoDaoBase() {
		return itemConhecimentoDAO;
	}

}
