package ode.entidadeProblema.cgd;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.entidadeProblema.cdp.ArtefatoProblema;


public interface ArtefatoProblemaDAO extends DAOBase<ArtefatoProblema>{
	Collection<ArtefatoProblema> obterPorArtefato (KArtefato artefato);		
}
