package ode.conhecimento.requisito.cgd;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode.conhecimento.requisito.cdp.TipoRequisito;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value="tipoRequisitoDao")
@Transactional(rollbackFor = NucleoExcecao.class)
public class TipoRequisitoDAOImpl extends DAOBaseImpl<TipoRequisito> implements TipoRequisitoDAO{

	@Override
	public void salvar(TipoRequisito objeto) {
		super.salvar(objeto);
	}
	

}