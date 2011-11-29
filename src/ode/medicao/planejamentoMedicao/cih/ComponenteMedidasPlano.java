package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoOrganizacao;

import org.apache.commons.collections.list.TreeList;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

public class ComponenteMedidasPlano extends Tree {

	private Treechildren arvore;
	private Collection<Treeitem> selecionaveis = new ArrayList<Treeitem>();

	public ComponenteMedidasPlano() {
		montar();
	}

	protected void montar(){
		this.setCheckmark(true);
		this.setMultiple(true);
		Treecols abas = new Treecols();
		Treecol nome = new Treecol();
		Treecol definicao = new Treecol();
		
		abas.appendChild(nome);
		abas.appendChild(definicao);
		this.appendChild(abas);
		
		nome.setLabel("Nome");
		definicao.setLabel("Detalhes");
		
		arvore = new Treechildren();
		this.appendChild(arvore);
		
		this.addEventListener("onSelect", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				for(Treeitem ti:selecionaveis){
					
				}
			}
		});
	}

	protected Treechildren adicionarLinha(Treechildren pai,
			KNecessidadeInformacao obj) {
		Treeitem item = new Treeitem();
		item.setCheckable(false);
		pai.appendChild(item);
		Treerow linha = new Treerow();
		item.appendChild(linha);

		Treecell nome = new Treecell(obj.getNome());
		nome.setParent(linha);

		Treecell descricao = new Treecell(obj.getDescricao());
		descricao.setParent(linha);

		Treechildren filhos = new Treechildren();
		item.appendChild(filhos);
		return filhos;
	}

	protected void adicionarSubLinha(Treechildren pai, KMedida medida) {
		Treeitem item = new Treeitem();
		pai.appendChild(item);
		Treerow linha = new Treerow();
		item.appendChild(linha);

		selecionaveis.add(item);
		
		Treecell nome = new Treecell(medida.getNome());
		linha.appendChild(nome);
		Treecell definicao = new Treecell();
		linha.appendChild(definicao);

		NucleoCombobox<KDefinicaoOperacionalMedida> lbdom = new NucleoCombobox<KDefinicaoOperacionalMedida>();
		lbdom.setObjetos(medida.getDefinicoesMedida());
		lbdom.setVisible(false);
		definicao.appendChild(lbdom);

	}

	public void atualizar(Collection<KNecessidadeInformacao> lista) {
		selecionaveis.clear();
		this.clear();
		Set<KMedida> medidas;
		Set<KNecessidadeInformacao> mergered = new HashSet<KNecessidadeInformacao>(
				lista);
		for (KNecessidadeInformacao obj : mergered) {
			Treechildren objtivoPai = adicionarLinha(arvore, obj);
			medidas = new HashSet<KMedida>();
			for (KMedida med : obj.getMedidas()) {
				adicionarSubLinha(objtivoPai, med);
			}
		}
	}

}
