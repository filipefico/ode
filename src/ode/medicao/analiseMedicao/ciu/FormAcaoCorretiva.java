package ode.medicao.analiseMedicao.ciu;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode.medicao.analiseMedicao.cdp.AcaoCorretiva;
import ode.medicao.analiseMedicao.cdp.Resultado;
import ode.medicao.analiseMedicao.cgt.AplAcaoCorretiva;

public class FormAcaoCorretiva extends FormularioDadosCRUD<AcaoCorretiva>{

	private Textbox nome;
	private Textbox descricao;
	private NucleoMultipleListBox<Resultado> ncResultados;
	
	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> tabs = new ArrayList<NucleoTab>();
		
		NucleoTab tabCadastro = new NucleoTab();
		
		tabCadastro.setNomeTab("Dados Cadastrais");
		GridDados gridDados = new GridDados();
		tabCadastro.setConteudoTab(gridDados);
		nome = new Textbox();
		descricao = new Textbox();
		nome.setWidth("100%");
		descricao.setWidth("100%");
		descricao.setRows(3);
		descricao.setMultiline(true);
		gridDados.adicionarLinha("Nome", nome);
		gridDados.adicionarLinha("Descrição", descricao);
		
		tabs.add(tabCadastro);
		
		
		
		NucleoTab tabResultados = new NucleoTab();
		
		tabResultados.setNomeTab("Causas das Ações");
		GridDados gridResultados = new GridDados();
		tabResultados.setConteudoTab(gridResultados);
		ncResultados = new NucleoMultipleListBox<Resultado>();
		ncResultados.setObjetos(((AplAcaoCorretiva)getControlador().getAplCRUD()).getResultados());
		gridResultados.adicionarLinhaUnica(ncResultados);
		ncResultados.setCheckmark(true);
		
		tabs.add(tabResultados);
		
		return tabs;
	}

	@Override
	protected void preencherDadosTela(AcaoCorretiva objeto)
			throws NucleoRegraNegocioExcecao {
		nome.setText(objeto.getNome());
		descricao.setText(objeto.getDescricao());
		ncResultados.setObjetosSelecionados(objeto.getCausas());
	}

	@Override
	protected void preencherDadosObjeto(AcaoCorretiva objeto) {
		objeto.setNome(nome.getText());
		objeto.setDescricao(descricao.getText());
		objeto.setCausas(ncResultados.getObjetosSelecionados());
	}

}
