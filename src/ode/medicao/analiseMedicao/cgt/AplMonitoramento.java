package ode.medicao.analiseMedicao.cgt;

import java.util.Collection;
import java.util.Set;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgd.KMedidaDAO;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.analiseMedicao.cdp.MonitoramentoObjetivo;
import ode.medicao.analiseMedicao.cgd.AnaliseMedicaoDAO;
import ode.medicao.analiseMedicao.cgd.MonitoramentoObjetivoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplMonitoramento {

	@Autowired
	MonitoramentoObjetivoDAO dao;
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

	public void excluir(MonitoramentoObjetivo objeto) {
		dao.excluir(objeto);
	}
	
	public Collection<MonitoramentoObjetivo> recuperarTodos(){
		return dao.recuperarTodos();
	}

	public void salvar(MonitoramentoObjetivo objeto) {
		if(objeto.isPersistente()){
			dao.atualizar(objeto);
		}else{
			dao.salvar(objeto);
		}
	}
}
