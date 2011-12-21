package ode.medicao.planejamentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cgd.DefinicaoOperacionalMedidaDAO;

@Service
public class AplCadastrarDefinicaoOperacionalMedida extends AplCRUD<DefinicaoOperacionalMedida>{

	@Autowired
	DefinicaoOperacionalMedidaDAO dao;
	
	@Override
	public DAOBase<DefinicaoOperacionalMedida> getNucleoDaoBase() {
		return dao;
	}

	public Collection<DefinicaoOperacionalMedida> recuperarRelacionadosMedida(KMedida medida){
		return medida.getDefinicoesMedida();
	}
}
