package ode.medicao.planejamentoMedicao.cgt;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cgd.NecessidadeInformacaoDAO;

@Service
public class AplCadastrarNecessidadeInformacao extends
		AplCRUD<NecessidadeInformacao> {

	@Autowired
	NecessidadeInformacaoDAO dao;

	@Override
	public void antesExcluir(NecessidadeInformacao objeto)
			throws NucleoRegraNegocioExcecao {
		
	}

	public void antesSalvar(NecessidadeInformacao objeto)
			throws NucleoRegraNegocioExcecao {
	}

	@Override
	public DAOBase<NecessidadeInformacao> getNucleoDaoBase() {
		return dao;
	}

}
