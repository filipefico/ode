package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoExcecao;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.persistencia.NucleoDAOBase;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.conhecimento.processo.Cgd.KFerramentaSoftwareDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKFerramentaSoftwareImpl extends
			NucleoAplCadastroBaseImpl<KFerramentaSoftware> implements
			AplCadastrarKFerramentaSoftware {
		
		@Autowired
		private KFerramentaSoftwareDAO kFerramentaSoftwareDAO;

		public KFerramentaSoftwareDAO getKFerramentaSoftwareDAO() {
			return kFerramentaSoftwareDAO;
		}

		public void setKFerramentaSoftwareDAO(KFerramentaSoftwareDAO kFerramentaSoftwareDAO) {
			this.kFerramentaSoftwareDAO = kFerramentaSoftwareDAO;
		}

		@Override
		protected void copiarValor(KFerramentaSoftware objetoFonte,
				KFerramentaSoftware objetoDestino) {
			objetoDestino.setNome(objetoFonte.getNome());
			objetoDestino.setDescricao(objetoFonte.getDescricao());
		}
		
		protected KFerramentaSoftware alterarDados(KFerramentaSoftware objeto) throws NucleoRegraNegocioExcecao {
			// Executa a ação necessária antes de alterar os dados
//			antesAlterarDados(objeto);

			// Obtém o Principal Serviço persistido e altera seus dados
		//	PessoaExemplo objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());
			
		//	copiarValor(objeto, objetoPersistido);

			// Executa a ação necessária depois de alterar os dados
//			depoisAlterarDados(objeto);
			
			// Inclui o Principal Serviço
		getNucleoDaoBase().merge(objeto);


			//return objetoPersistido;
			return objeto;
		}

		@Override
		public NucleoDAOBase<KFerramentaSoftware> getNucleoDaoBase() {
			return kFerramentaSoftwareDAO;
		}

}
