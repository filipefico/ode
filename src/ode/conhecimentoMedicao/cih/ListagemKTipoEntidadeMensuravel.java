package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

@Component
public class ListagemKTipoEntidadeMensuravel extends
		ListagemSimples<KTipoEntidadeMensuravel> {

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","40%"));
		colunas.add(new NucleoListHeader("Descrição","descricao","60%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KTipoEntidadeMensuravel objeto) {
		return new String[]{objeto.getNome(),objeto.getDescricao()};
	}

}
