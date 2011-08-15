package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.conhecimento.processo.Cgd.TipoKArtefatoDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarTipoKArtefatoImpl extends
		AplBaseImpl<TipoKArtefato> implements
		AplCadastrarTipoKArtefato {

	@Autowired
	private TipoKArtefatoDAO tipoKArtefatoDAO;

	public TipoKArtefatoDAO getTipoKArtefatoDAO() {
		return tipoKArtefatoDAO;
	}

	public void setTipoKArtefatoDAO(TipoKArtefatoDAO tipoKArtefatoDAO) {
		this.tipoKArtefatoDAO = tipoKArtefatoDAO;
	}

	@Override
	public DAOBase<TipoKArtefato> getNucleoDaoBase() {
		return tipoKArtefatoDAO;
	}


}
