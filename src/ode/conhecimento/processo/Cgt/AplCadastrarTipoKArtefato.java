package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.TipoKArtefato;
import ode.conhecimento.processo.cgd.TipoKArtefatoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarTipoKArtefato extends
		AplCRUD<TipoKArtefato> {

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
