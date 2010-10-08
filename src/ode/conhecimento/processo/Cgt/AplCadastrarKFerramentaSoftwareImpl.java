package ode.conhecimento.processo.Cgt;

import nucleo.comuns.aplicacao.NucleoAplCadastroBaseImpl;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.conhecimento.processo.Cdp.KFerramentaSoftware;

import ode.conhecimento.processo.Cgd.KFerramentaSoftwareDAO;

import org.springframework.beans.factory.annotation.Autowired;

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
			// Executa a a��o necess�ria antes de alterar os dados
//			antesAlterarDados(objeto);

			// Obt�m o Principal Servi�o persistido e altera seus dados
		//	PessoaExemplo objetoPersistido = getNucleoDaoBase().recuperarPorId(objeto.getId());
			
		//	copiarValor(objeto, objetoPersistido);

			// Executa a a��o necess�ria depois de alterar os dados
//			depoisAlterarDados(objeto);
			
			// Inclui o Principal Servi�o
		getNucleoDaoBase().merge(objeto);


			//return objetoPersistido;
			return objeto;
		}

}
