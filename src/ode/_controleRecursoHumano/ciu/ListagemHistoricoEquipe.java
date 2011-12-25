package ode._controleRecursoHumano.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._controleRecursoHumano.cdp.ParticipacaoEquipe;
import ode._infraestruturaBase.util.NucleoUtil;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;

public class ListagemHistoricoEquipe extends ListagemSimples<ParticipacaoEquipe> {

	private static final long serialVersionUID = 1L;

	@Override
	protected String[] recuperarDadosObjeto(ParticipacaoEquipe objeto) {
		return new String[]{objeto.getRecursoHumano().getNome(), NucleoUtil.formataData(objeto.getDtInicio()),NucleoUtil.formataData(objeto.getDtFim())};
	}
		
	@Override
	public List<NucleoListHeader> definirColunasTabela() {
		List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
		colunas.add(new NucleoListHeader("Recurso Humano","nome","50%"));
		colunas.add(new NucleoListHeader("Data Início","dtInicio","25%"));
		colunas.add(new NucleoListHeader("Data Fim","dtFim","25%"));
		return colunas;
	}
	
	public ListagemHistoricoEquipe() {
		super();
	}
	
	protected void configurarComponentesExtensao() {
		listBox.setCheckmark(false);
	}
}
