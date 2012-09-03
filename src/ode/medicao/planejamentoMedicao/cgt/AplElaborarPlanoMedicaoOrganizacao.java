package ode.medicao.planejamentoMedicao.cgt;

import java.util.Collection;

import ode._infraestruturaBase.cgd.DAOBase;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgd.PlanoMedicaoOrganizacaoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplElaborarPlanoMedicaoOrganizacao{

	@Autowired
	PlanoMedicaoOrganizacaoDAO dao;
	
	public PlanoMedicaoOrganizacaoDAO getDAO(){
		return dao;
	}
	
	public void salvar(PlanoMedicaoOrganizacao org){
		if(org.isPersistente()){
			getDAO().atualizar(org);
		}else{
			getDAO().salvar(org);
		}
	}
	
	public PlanoMedicaoOrganizacao recuperarPorId(Long id){
		return getDAO().recuperarPorId(id);
	}
	
	public Collection<PlanoMedicaoOrganizacao> recuperarTodos(){
		return getDAO().recuperarTodosComOrdenacao("versao");
	}

	public void delete(PlanoMedicaoOrganizacao pmo) {
		dao.excluir(pmo);
	}
	
}
