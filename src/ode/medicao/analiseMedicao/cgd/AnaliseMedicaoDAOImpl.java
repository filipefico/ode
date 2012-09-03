package ode.medicao.analiseMedicao.cgd;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ode._infraestruturaBase.cgd.DAOBaseImpl;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.execucaoMedicao.cdp.Medicao;

@Repository
public class AnaliseMedicaoDAOImpl extends DAOBaseImpl<AnaliseMedicao> implements AnaliseMedicaoDAO{
	@Override
	@Transactional
	public void salvar(AnaliseMedicao objeto) {
		super.salvar(objeto);
	}
	
	@Override
	@Transactional
	public AnaliseMedicao atualizar(AnaliseMedicao objeto) {
		return super.atualizar(objeto);
	}
	
	@Override
	@Transactional
	public void excluir(AnaliseMedicao objeto) {
		super.excluir(objeto);
	}
	
	@Override
	@Transactional
	public Collection<AnaliseMedicao> recuperarTodos() {
		return super.recuperarTodos();
	}
}
