package ode.conhecimento.processo.Cgd;

import java.util.Collection;

import ode.conhecimento.processo.Cdp.KLinguagemProgramacao;
import nucleo.comuns.persistencia.NucleoDAOBaseHibernate;

public class KLinguagemProgramacaoDAOHibernate extends NucleoDAOBaseHibernate<KLinguagemProgramacao> implements KLinguagemProgramacaoDAO {

		@Override
		protected Class<KLinguagemProgramacao> getClasseDominio() {
			// TODO Auto-generated method stub
			return KLinguagemProgramacao.class;
		}
	
		public void salvar(KLinguagemProgramacao kLinguagemProgramacao){
	        super.salvar(kLinguagemProgramacao);
	    }
	    
	    public void excluir(KLinguagemProgramacao kLinguagemProgramacao){
	        super.excluir(kLinguagemProgramacao);
	    }
	    
	    /** obter lista com todos Recursos Humanos*/
	    public Collection<KLinguagemProgramacao> recuperarTodos(){
	        return super.recuperarTodos();
	    }
}
