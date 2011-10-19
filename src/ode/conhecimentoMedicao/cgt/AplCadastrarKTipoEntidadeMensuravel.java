package ode.conhecimentoMedicao.cgt;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgd.KTipoEntidadeMensuravelDAO;
import ode._infraestruturaBase.cgd.DAOBase;
import ode._infraestruturaCRUD.cgt.AplCRUD;

@Service
public class AplCadastrarKTipoEntidadeMensuravel extends AplCRUD<KTipoEntidadeMensuravel> {

	@Autowired
	KTipoEntidadeMensuravelDAO dao;
	
	@Override
	public DAOBase<KTipoEntidadeMensuravel> getNucleoDaoBase() {
		return dao;
	}
	
	public Collection<KElementoMensuravel> recuperarPorTipo(
			KTipoEntidadeMensuravel tipo) {
		return dao.recuperarPorTipo(tipo);
	}
	
}
