package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.List;

import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.KMetodoAnalitico;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

public class ListagemKMedida extends ListagemSimples<KMedida> {

	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Nome","nome","30%"));
		colunas.add(new NucleoListHeader("Mnemonico", "mnemonico", "25%"));
		colunas.add(new NucleoListHeader("Natureza", "naturezaMedida", "15%"));
		colunas.add(new NucleoListHeader("Descrição","descricao","30%"));
		return colunas;
	}

	@Override
	protected String[] recuperarDadosObjeto(KMedida objeto) {
		return new String[]{objeto.getNome(),objeto.getMnemonico(),objeto.getNaturezaMedida().toString(),objeto.getDescricao()};
	}
	
	@Override
	public void configurarComponentes(){
		super.configurarComponentes();
		listBox.setHeight("450px");
	}

}
