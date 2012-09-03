package ode.medicao.analiseMedicao.cgt;

import java.util.Collection;
import java.util.Set;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgd.KMedidaDAO;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.analiseMedicao.cgd.AnaliseMedicaoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplAnaliseMedicao {

	@Autowired
	AnaliseMedicaoDAO dao;
	@Autowired
	ProjetoDAO pDao;
	@Autowired
	KMedidaDAO mDao;
	
	public Collection<Projeto> recuperarTodosProjetos(){
		return pDao.recuperarTodos();
	}

	public Iterable<KMedida> recuperarTodasMedidas() {
		return mDao.recuperarTodos();
	}

	public void excluir(AnaliseMedicao objeto) {
		dao.excluir(objeto);
	}
	
	public Collection<AnaliseMedicao> recuperarTodos(){
		return dao.recuperarTodos();
	}

	public void salvar(AnaliseMedicao objeto) {
		if(objeto.isPersistente()){
			dao.atualizar(objeto);
		}else{
			dao.salvar(objeto);
		}
	}
}
