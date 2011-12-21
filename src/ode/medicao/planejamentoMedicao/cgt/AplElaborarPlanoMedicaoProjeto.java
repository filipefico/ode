package ode.medicao.planejamentoMedicao.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoProjeto;
import ode.medicao.planejamentoMedicao.cgd.PlanoMedicaoOrganizacaoDAO;
import ode.medicao.planejamentoMedicao.cgd.PlanoMedicaoProjetoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplElaborarPlanoMedicaoProjeto{

	@Autowired
	PlanoMedicaoProjetoDAO dao;
	
	public PlanoMedicaoProjetoDAO getDAO(){
		return dao;
	}
	
	public void salvar(PlanoMedicaoProjeto org){
		if(org.isPersistente()){
			dao.atualizar(org);
		}else{
			dao.salvar(org);
		}
	}
	
	public PlanoMedicaoProjeto recuperarPorId(Long id){
		return dao.recuperarPorId(id);
	}
	
	public Collection<PlanoMedicaoProjeto> recuperarTodos(){
		return dao.recuperarTodos();
	}
	
}
