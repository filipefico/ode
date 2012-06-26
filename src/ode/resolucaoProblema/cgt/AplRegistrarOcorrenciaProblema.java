package ode.resolucaoProblema.cgt;

import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;
import ode.resolucaoProblema.cgd.OcorrenciaProblemaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional (rollbackFor = NucleoExcecao.class)
public class AplRegistrarOcorrenciaProblema extends AplCRUD<OcorrenciaProblema> {

	@Autowired
	OcorrenciaProblemaDAO ocorrenciaproblema;

	@Override
	public DAOBase<OcorrenciaProblema> getNucleoDaoBase() {
		return ocorrenciaproblema;
	}
	
	public List<OcorrenciaProblema> recuperarTodosOrdenadoImpacto (){
		return ocorrenciaproblema.recuperarTodosOrdenadoImpacto();
	}
	
	

}
