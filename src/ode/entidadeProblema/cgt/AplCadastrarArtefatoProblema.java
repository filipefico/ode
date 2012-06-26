package ode.entidadeProblema.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.entidadeProblema.cdp.ArtefatoProblema;
import ode.entidadeProblema.cgd.ArtefatoProblemaDAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AplCadastrarArtefatoProblema extends AplCRUD<ArtefatoProblema>{

	@Autowired
	ArtefatoProblemaDAO dao;
	
	@Override
	public DAOBase<ArtefatoProblema> getNucleoDaoBase() {
		return dao;
	}
	
	public Collection<ArtefatoProblema> obterPorArtefato (KArtefato artefato){
		return dao.obterPorArtefato(artefato);
	}

}
