package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.conhecimento.processo.Cgd.TipoKArtefatoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarTipoKArtefatoImpl extends
		NucleoAplCadastroBaseImpl<TipoKArtefato> implements
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
	protected void copiarValor(TipoKArtefato objetoFonte,
			TipoKArtefato objetoDestino) {

		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());

	}

	@Override
	public NucleoDAOBase<TipoKArtefato> getNucleoDaoBase() {
		return tipoKArtefatoDAO;
	}


}
