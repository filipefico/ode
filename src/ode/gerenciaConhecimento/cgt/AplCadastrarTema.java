package ode.gerenciaConhecimento.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.gerenciaConhecimento.cdp.Tema;
import ode.gerenciaConhecimento.cgd.TemaDAO;

@Service
public class AplCadastrarTema extends AplCRUD<Tema> {

	@Autowired
	TemaDAO temaDAO;
	
	@Override
	public DAOBase<Tema> getNucleoDaoBase() {
		// TODO Auto-generated method stub
		return temaDAO;
	}

}
