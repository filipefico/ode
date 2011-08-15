package ode.conhecimento.processo.Cgt;

import ode.conhecimento.processo.Cdp.KFerramentaSoftware;
import ode.conhecimento.processo.Cgd.KFerramentaSoftwareDAO;
import ode.nucleo.crud.cgd.DAOBase;
import ode.nucleo.crud.cgt.AplBaseImpl;
import ode.nucleo.excecao.NucleoExcecao;
import ode.nucleo.excecao.NucleoRegraNegocioExcecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKFerramentaSoftwareImpl extends
			AplBaseImpl<KFerramentaSoftware> implements
			AplCadastrarKFerramentaSoftware {
		
		@Autowired
		private KFerramentaSoftwareDAO kFerramentaSoftwareDAO;

		public KFerramentaSoftwareDAO getKFerramentaSoftwareDAO() {
			return kFerramentaSoftwareDAO;
		}

		public void setKFerramentaSoftwareDAO(KFerramentaSoftwareDAO kFerramentaSoftwareDAO) {
			this.kFerramentaSoftwareDAO = kFerramentaSoftwareDAO;
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

		@Override
		public DAOBase<KFerramentaSoftware> getNucleoDaoBase() {
			return kFerramentaSoftwareDAO;
		}

}
