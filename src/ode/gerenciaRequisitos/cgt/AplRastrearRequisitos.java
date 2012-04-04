package ode.gerenciaRequisitos.cgt;

import java.util.Collection;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.gerenciaRequisitos.cgd.RequisitoDAO;
import ode.uml.cdp.CasoUso;
import ode.uml.cdp.Classe;
import ode.uml.cdp.Pacote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplRastrearRequisitos{
	
	@Autowired
	RequisitoDAO requisitoDao;

	public RequisitoDAO getRequisitoDao() {
		return requisitoDao;
	}

	public void setRequisitoDao(RequisitoDAO requisitoDao) {
		this.requisitoDao = requisitoDao;
	}
	
	public Collection<Requisito> obterPorCasoUso (CasoUso casoUso){
		return requisitoDao.obterPorCasoUso(casoUso);
	}
	
	public Collection<Requisito> obterPorClasse (Classe classe){
		return requisitoDao.obterPorClasse(classe);
	}
	
	public Collection<Requisito> obterPorPacote (Pacote pacote){
		return requisitoDao.obterPorPacote(pacote);
	}
	
	public Collection<Requisito> obterPorResponsavel (RecursoHumano responsavel){
		return requisitoDao.obterPorResponsavel(responsavel);
	}
	
	public Collection<Requisito> obterPorInteressado (RecursoHumano interessado){
		return requisitoDao.obterPorInteressado(interessado);
	}
}
