package ode.conhecimento.organizacao.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.organizacao.Cdp.KDominioConhecimento;
import ode.conhecimento.organizacao.Cgd.KDominioConhecimentoDAO;

import org.springframework.beans.factory.annotation.Autowired;

public class AplCadastrarKDominioConhecimentoImpl extends
			NucleoAplCadastroBaseImpl<KDominioConhecimento> implements
			AplCadastrarKDominioConhecimento {
		
		@Autowired
		private KDominioConhecimentoDAO kDominioConhecimentoDAO;

		public KDominioConhecimentoDAO getKDominioConhecimentoDAO() {
			return kDominioConhecimentoDAO;
		}

		public void setKDominioConhecimentoDAO(KDominioConhecimentoDAO kDominioConhecimentoDAO) {
			this.kDominioConhecimentoDAO = kDominioConhecimentoDAO;
		}

		@Override
		protected void copiarValor(KDominioConhecimento objetoFonte,
				KDominioConhecimento objetoDestino) {
			objetoDestino.setNome(objetoFonte.getNome());
			objetoDestino.setDescricao(objetoFonte.getDescricao());
		}
		
		protected KDominioConhecimento alterarDados(KDominioConhecimento objeto) throws NucleoRegraNegocioExcecao {
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

}
