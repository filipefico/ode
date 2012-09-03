package ode.medicao.analiseMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.analiseMedicao.cdp.MonitoramentoObjetivo;

@Repository
public class MonitoramentoObjetivoDAOImpl extends DAOBaseImpl<MonitoramentoObjetivo> implements MonitoramentoObjetivoDAO{
	
	@Override
	@Transactional
	public MonitoramentoObjetivo atualizar(MonitoramentoObjetivo objeto) {
		// TODO Auto-generated method stub
		return super.atualizar(objeto);
	}

	@Override
	@Transactional
	public Collection<MonitoramentoObjetivo> recuperarTodos() {
		// TODO Auto-generated method stub
		return super.recuperarTodos();
	}
	
	@Override
	@Transactional
	public void salvar(MonitoramentoObjetivo objeto) {
		// TODO Auto-generated method stub
		super.salvar(objeto);
	}
	@Override
	@Transactional
	public void excluir(MonitoramentoObjetivo objeto) {
		// TODO Auto-generated method stub
		super.excluir(objeto);
	}
}
