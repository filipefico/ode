package ode.resolucaoProblema.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

public class ListagemOcorrenciaProblema extends ListagemSimples<OcorrenciaProblema> {

	
	private static final long serialVersionUID = -1117918269894276296L;

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","155px"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(OcorrenciaProblema objeto) {
		// TODO Auto-generated method stub
		return new String[]{objeto.getNome()};
		
	}

}
