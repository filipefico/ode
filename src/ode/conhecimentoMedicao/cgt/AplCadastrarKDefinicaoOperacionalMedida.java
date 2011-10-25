package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgd.KDefinicaoOperacionalMedidaDAO;

@Service
public class AplCadastrarKDefinicaoOperacionalMedida extends AplCRUD<KDefinicaoOperacionalMedida>{

	@Autowired
	KDefinicaoOperacionalMedidaDAO dao;
	
	@Override
	public DAOBase<KDefinicaoOperacionalMedida> getNucleoDaoBase() {
		return dao;
	}

	public Collection<KDefinicaoOperacionalMedida> recuperarRelacionadosMedida(KMedida medida){
		return medida.getDefinicoesMedida();
	}
}
