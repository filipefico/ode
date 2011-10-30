package ode.atuacaoRecursoHumano.cgt;

import java.util.Collection;

import ode.atuacaoRecursoHumano.cdp.AtuacaoRH;
import ode.atuacaoRecursoHumano.cdp.CompetenciaRH;
import ode.atuacaoRecursoHumano.cgd.AtuacaoRHDAO;
import ode.atuacaoRecursoHumano.cgd.CompetenciaRHDAO;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgd.RecursoHumanoDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaBase.excecao.NucleoExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = NucleoExcecao.class)
public class AplCadastrarAtuacaoRH extends AplCRUD<RecursoHumano> {

	@Autowired
	private AtuacaoRHDAO atuacaoRHDAO;
	
	@Autowired
	private RecursoHumanoDAO recursoHumanoDAO;
	
	@Autowired
	private CompetenciaRHDAO competenciaRHDAO;
	
	@Override
	public DAOBase<RecursoHumano> getNucleoDaoBase() {
		return recursoHumanoDAO;
	}
	
	public void salvar(RecursoHumano rh, AtuacaoRH atuacaoRH, Collection<CompetenciaRH> competencias) {
		for (CompetenciaRH competencia : competenciaRHDAO.obterPorRH(rh.getId())) {
			competenciaRHDAO.excluir(competencia);	
		}
		
		if(atuacaoRH != null) {
			for (CompetenciaRH competencia : competencias) {
				competencia.setAtuacaoRH(atuacaoRH);
				competenciaRHDAO.salvar(competencia);
			}
			
			if (atuacaoRH.isPersistente()) {
				atuacaoRHDAO.atualizar(atuacaoRH);
			} else {
				atuacaoRHDAO.salvar(atuacaoRH);
			}
		}
		else {
			AtuacaoRH atuacaoAnterior = atuacaoRHDAO.recuperarAtuacaoRHPorRH(rh.getId());
			if(atuacaoAnterior != null) atuacaoRHDAO.excluir(atuacaoAnterior);
		}
		
		if (rh.isPersistente()) {
			recursoHumanoDAO.atualizar(rh);
		} else {
			recursoHumanoDAO.salvar(rh);
		}	
	}

	public AtuacaoRH recuperarAtuacaoRHPorRH(RecursoHumano rh) {
		return atuacaoRHDAO.recuperarAtuacaoRHPorRH(rh.getId());
	}
	
	@Override
	public Collection<RecursoHumano> recuperarTodos() {
		return recursoHumanoDAO.recuperarTodosComOrdenacao("nome");
	}

	public Collection<CompetenciaRH> recuperarCompetenciasPorAtuacaoRH(AtuacaoRH atuacaoRH) {
		return atuacaoRHDAO.recuperarCompetenciasPorAtuacaoRH(atuacaoRH.getId());
	}
}
