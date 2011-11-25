package ode.conhecimento.processo.cgt;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cgd.KFerramentaSoftwareDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AplCadastrarKFerramentaSoftware")
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarKFerramentaSoftware extends
			AplCRUD<KFerramentaSoftware> {
		
		@Autowired
		private KFerramentaSoftwareDAO kFerramentaSoftwareDAO;

		public KFerramentaSoftwareDAO getKFerramentaSoftwareDAO() {
			return kFerramentaSoftwareDAO;
		}

		public void setKFerramentaSoftwareDAO(KFerramentaSoftwareDAO kFerramentaSoftwareDAO) {
			this.kFerramentaSoftwareDAO = kFerramentaSoftwareDAO;
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
		getNucleoDaoBase().atualizar(objeto);


			//return objetoPersistido;
			return objeto;
		}

		@Override
		public DAOBase<KFerramentaSoftware> getNucleoDaoBase() {
			return kFerramentaSoftwareDAO;
		}

}
