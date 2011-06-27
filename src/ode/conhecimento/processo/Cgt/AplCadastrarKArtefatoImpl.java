package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cgd.KArtefatoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKArtefatoImpl extends
		NucleoAplCadastroBaseImpl<KArtefato> implements AplCadastrarKArtefato {

	@Autowired
	private KArtefatoDAO kArtefatoDAO;
	
	public KArtefatoDAO getkArtefatoDAO() {
		return kArtefatoDAO;
	}

	public void setkArtefatoDAO(KArtefatoDAO kArtefatoDAO) {
		this.kArtefatoDAO = kArtefatoDAO;
	}

	@Override
	protected void copiarValor(KArtefato objetoFonte, KArtefato objetoDestino) {

		objetoDestino.setId(objetoFonte.getId());
		objetoDestino.setNome(objetoFonte.getNome());
		objetoDestino.setDescricao(objetoFonte.getDescricao());
		objetoDestino.setTipo(objetoFonte.getTipo());
		objetoDestino.setDependencias(objetoFonte.getDependencias());
		objetoDestino.setSubArtefatos(objetoFonte.getSubArtefatos());

	}

	@Override
	public NucleoDAOBase<KArtefato> getNucleoDaoBase() {
		return kArtefatoDAO;
	}

}
