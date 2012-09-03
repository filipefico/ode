package ode.medicao.planejamentoMedicao.cih;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.event.EventDirContext;
import javax.print.attribute.standard.Media;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgd.KMedidaDAO;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.MedidaPlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cgd.MedidaPlanoMedicaoDAO;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicao;
import ode.medicao.planejamentoMedicao.cci.CtrlPlanoMedicaoOrganizacao;

import org.apache.commons.collections.list.TreeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.AbstractComponent;
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

@Component
public class ComponenteMedidasPlano extends Tree {

	@Autowired
	MedidaPlanoMedicaoDAO mpmDAO;
	
	private Treechildren arvore;
	private Collection<Treeitem> selecionaveis = new ArrayList<Treeitem>();
	private CtrlPlanoMedicao ctrl;

	public ComponenteMedidasPlano() {
		montar();
	}

	public void setControlador(CtrlPlanoMedicao ctrl) {
		this.ctrl = ctrl;
	}

	private class VisibleEvent implements EventListener {

		private NucleoCombobox<DefinicaoOperacionalMedida> obj;

		public VisibleEvent(NucleoCombobox<DefinicaoOperacionalMedida> obj) {
			this.obj = obj;
		}

		@Override
		public void onEvent(Event arg0) throws Exception {
			obj.setVisible(((Treeitem) arg0.getTarget()).isSelected());
		}

	}

	protected void montar() {
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

	}

	protected Treechildren adicionarLinha(Treechildren pai,
			NecessidadeInformacao obj) {
		Treeitem item = new Treeitem();
		item.setCheckable(false);
		item.setValue(obj);
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
		item.setValue(medida);
		Treerow linha = new Treerow();
		item.appendChild(linha);

		selecionaveis.add(item);

		Treecell nome = new Treecell(medida.getNome());
		linha.appendChild(nome);
		Treecell definicao = new Treecell();
		linha.appendChild(definicao);

		NucleoCombobox<DefinicaoOperacionalMedida> lbdom = new NucleoCombobox<DefinicaoOperacionalMedida>();
		lbdom.setObjetos(medida.getDefinicoesMedida());
		lbdom.setVisible(false);
		lbdom.selecionarPrimeiroElemento();
		definicao.appendChild(lbdom);

		item.addEventListener("onClick", new VisibleEvent(lbdom));

	}

	public void atualizar(Collection<NecessidadeInformacao> lista) {
		selecionaveis.clear();
		this.clear();
		Set<NecessidadeInformacao> mergered = new HashSet<NecessidadeInformacao>(
				lista);
		for (NecessidadeInformacao obj : mergered) {
			Treechildren objtivoPai = adicionarLinha(arvore, obj);
			for (KMedida med : ctrl.recuperaMedidas()) {
				adicionarSubLinha(objtivoPai, med);
			}
		}
	}
	
	public void mostraMpm(Set<MedidaPlanoMedicao> objetos){
		MedidaPlanoMedicao temp;
		NucleoCombobox<DefinicaoOperacionalMedida> ncb;
		for (Treeitem itemNecInfo : (Collection<Treeitem>) arvore.getChildren()) {
			for (Treeitem itemMedida : ((Collection<Treeitem>) itemNecInfo.getLastChild().getChildren())) {
				temp = contem((NecessidadeInformacao)itemNecInfo.getValue(), (KMedida)itemMedida.getValue(), objetos);
				if(temp != null){
					itemMedida.setSelected(true);
					ncb = getBoxDefinicaoPorItemMedida(itemMedida);
					ncb.setVisible(true);
					ncb.setObjetoSelecionado(temp.getDefinicaoOperacional());
				}
			}
		}
	}

	public Set<MedidaPlanoMedicao> confereMpm(Set<MedidaPlanoMedicao> objetos) {
		if (objetos == null) {
			objetos = new HashSet<MedidaPlanoMedicao>();
		}
		MedidaPlanoMedicao temp;
		for (Treeitem itemNecInfo : (Collection<Treeitem>) arvore.getChildren()) {
			for (Treeitem itemMedida : ((Collection<Treeitem>) itemNecInfo.getLastChild().getChildren())) {
				temp = contem((KMedida)itemMedida.getValue(), objetos);
				if(itemMedida.isSelected()){ 
					if( temp == null ){ //nao contem
						temp = new MedidaPlanoMedicao();
						temp.setNecessidadesInformacao(new HashSet<NecessidadeInformacao>());
						temp.setMedida((KMedida)itemMedida.getValue());
						objetos.add(temp);
					}
					temp.getNecessidadesInformacao().add((NecessidadeInformacao)itemNecInfo.getValue());
					temp.setDefinicaoOperacional(getDefinicaoPorTreeItem(itemMedida));
				}else{
					if( temp != null ){
						temp.getNecessidadesInformacao().remove((NecessidadeInformacao)itemNecInfo.getValue());
					}
				}
			}
		}
		for(MedidaPlanoMedicao mpmExcluidos:objetos){
			if( mpmExcluidos.getNecessidadesInformacao().isEmpty()){
				objetos.remove(mpmExcluidos);
				mpmDAO.excluir(mpmExcluidos);
			}
		}
		return objetos;
	}
	
	private MedidaPlanoMedicao contem(KMedida med, Set<MedidaPlanoMedicao> objetos){
		for(MedidaPlanoMedicao mpm:objetos){
			if(mpm.getMedida().equals(med)){
				return mpm;
			}
		}
		return null;
	}
	
	private MedidaPlanoMedicao contem(NecessidadeInformacao nec, KMedida med, Set<MedidaPlanoMedicao> objetos){
		MedidaPlanoMedicao mpm = contem(med,objetos);
		if(mpm == null) return null;
		for(NecessidadeInformacao ni:mpm.getNecessidadesInformacao()){
			if(ni.equals(nec)){
				return mpm;
			}
		}
		return null;
	}
	
	private NucleoCombobox<DefinicaoOperacionalMedida> getBoxDefinicaoPorItemMedida(Treeitem item){
		return ((NucleoCombobox<DefinicaoOperacionalMedida>)item.getFirstChild().getLastChild().getFirstChild());
	}
	private DefinicaoOperacionalMedida getDefinicaoPorTreeItem(Treeitem item){
		return (DefinicaoOperacionalMedida)getBoxDefinicaoPorItemMedida(item).getSelectedItem().getValue();
	}

	public void decelecionaTudo() {
		for (Treeitem itemNecInfo : (Collection<Treeitem>) arvore.getChildren()) {
			for (Treeitem itemMedida : ((Collection<Treeitem>) itemNecInfo.getLastChild().getChildren())) {
				itemMedida.setSelected(false);
				NucleoCombobox<DefinicaoOperacionalMedida> ncb = getBoxDefinicaoPorItemMedida(itemMedida);
				ncb.selecionarPrimeiroElemento();
				ncb.setVisible(false);
			}
		}
	}
}
