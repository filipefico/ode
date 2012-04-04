package ode.gerenciaRequisitos.cgt;

import java.util.Collection;
import java.util.HashSet;

import ode.controleProjeto.cdp.Projeto;
import ode.gerenciaRequisitos.cdp.Requisito;
import ode.gerenciaRequisitos.cgd.RequisitoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplGerarRelatorioRastreabilidade {
	
	@Autowired
	RequisitoDAO daoRequisito;
	
	Collection<Requisito> recuperarPorProjeto(Projeto projeto){
		Collection<Requisito> requisitos = daoRequisito.obterPorProjeto(projeto);
		Collection<Requisito> reqs = new HashSet<Requisito>();
		for (Requisito r : requisitos){
			r = daoRequisito.recuperarPorId(r.getId());
			reqs.add(r);
		}
		return reqs;
	}

}
