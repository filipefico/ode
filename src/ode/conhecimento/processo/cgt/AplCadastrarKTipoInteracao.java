package ode.conhecimento.processo.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KTipoInteracao;
import ode.conhecimento.processo.cgd.KTipoInteracaoDAO;

@Service("AplCadastrarKTipoInteracao")
public class AplCadastrarKTipoInteracao extends AplCRUD<KTipoInteracao>{
	
	@Autowired
	KTipoInteracaoDAO kTipoInteracaoDAO;
	
	@Override
	public DAOBase<KTipoInteracao> getNucleoDaoBase() {
		return kTipoInteracaoDAO;
	}

}
