package ode.medicao.execucaoMedicao.cgt;

import ode.medicao.execucaoMedicao.cdp.Medicao;
import ode.medicao.execucaoMedicao.cgd.MedicaoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplRegistrarMedicao {

	@Autowired
	MedicaoDAO mdao;
	
	public void salvar(Medicao med) {
		mdao.salvar(med);
	}

	public Iterable<Medicao> recuperarTodos() {
		return mdao.recuperarTodos();
	}

	public void atualizar(Medicao atual) {
		mdao.atualizar(atual);
	}

	public void excluir(Medicao atual) {
		mdao.excluir(atual);
	}

}
