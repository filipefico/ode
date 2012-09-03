package ode.medicao.analiseMedicao.cgt;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.medicao.analiseMedicao.cdp.AcaoCorretiva;
import ode.medicao.analiseMedicao.cdp.Resultado;
import ode.medicao.analiseMedicao.cgd.AcaoCorretivaDAO;
import ode.medicao.analiseMedicao.cgd.AnaliseMedicaoDAO;
import ode.medicao.analiseMedicao.cgd.MonitoramentoObjetivoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplAcaoCorretiva extends AplCRUD<AcaoCorretiva>{

	@Autowired
	AcaoCorretivaDAO dao;
	
	@Autowired
	MonitoramentoObjetivoDAO monitoramentoDao;
	
	@Autowired
	AnaliseMedicaoDAO analiseDao;
	
	@Override
	public DAOBase<AcaoCorretiva> getNucleoDaoBase() {
		return dao;
	}

	public List<Resultado> getResultados(){
		List<Resultado> resultados = new ArrayList<Resultado>();
		resultados.addAll(monitoramentoDao.recuperarTodos());
		resultados.addAll(analiseDao.recuperarTodos());
		return  resultados;
	}
}
