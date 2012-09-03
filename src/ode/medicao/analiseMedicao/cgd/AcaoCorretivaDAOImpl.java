package ode.medicao.analiseMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.analiseMedicao.cdp.AcaoCorretiva;

@Repository
public class AcaoCorretivaDAOImpl extends DAOBaseImpl<AcaoCorretiva> implements AcaoCorretivaDAO{
	@Override
	@Transactional
	public AcaoCorretiva atualizar(AcaoCorretiva objeto) {
		// TODO Auto-generated method stub
		return super.atualizar(objeto);
	}
	
	@Override
	@Transactional
	public void excluir(AcaoCorretiva objeto) {
		// TODO Auto-generated method stub
		super.excluir(objeto);
	}
	
	@Override
	@Transactional
	public Collection<AcaoCorretiva> recuperarTodos() {
		// TODO Auto-generated method stub
		return super.recuperarTodos();
	}
	
	@Override
	@Transactional
	public void salvar(AcaoCorretiva objeto) {
		// TODO Auto-generated method stub
		super.salvar(objeto);
	}
}
