package ode.processoProjeto.ciu;

import java.util.ArrayList;
import javax.swing.Box;
import java.util.Collection;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.processoPadrao.ciu.CtrlDefinirProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoProjeto.cdp.CompPPr;

import org.python.antlr.runtime.tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.form.LabelTag;
import org.zkoss.xel.taglib.Taglib;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vbox;


public class JanDefinirCompPPr extends JanCore {

	private static final long serialVersionUID = -5092667651513046609L;
	private JanelaSimples janela;
	private Listbox listaProcessoComplexo;		
	
	public JanDefinirCompPPr(CtrlDefinirProcessoProjeto ctrlDefinirProcessoProjeto, boolean setarCompPPEmArvore) {
		
		super(ctrlDefinirProcessoProjeto);
		
		janela = this;
		listaProcessoComplexo = new Listbox();
		
		try {
			janela.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}

		configuraElementosJanela();
		janela.mostrar();
	}

	@SuppressWarnings("deprecation")
	private void configuraElementosJanela() {
		janela.setTitle("Selecione o Processo Padrão base");
	
		//Collection<CompPPProcessoComplexo> listaCompPPComplexo = ctrlDefinirProcessoPadrao.getAllCompPPProcessoComplexo();
		// Criando as coleções:
				Collection<CompPP> listaCompPP = ctrl.getAllCompPPComOrdenacao("nome");
				Collection<CompPPProcessoComplexo> listaCompPPComplexo = new ArrayList<CompPPProcessoComplexo>();
				
				// Separando o collection geral (listaCompPP) por granularidade, para 3 novos collections criados acima
				if(listaCompPP != null){
					for (CompPP compPP : listaCompPP) {
						if(compPP instanceof CompPPProcessoComplexo){
							listaCompPPComplexo.add((CompPPProcessoComplexo)compPP);
						}
					}
				}
		
		
		//----------  trabalhando com processo complexo -------------
		
		Vbox vbox = new Vbox();
		vbox.setParent(janela);

		listaProcessoComplexo.setCheckmark(true);
		listaProcessoComplexo.setParent(vbox);
		
		Listitem itemLista = new Listitem();
		
		if(listaCompPPComplexo != null){     // Evitar segmentation fault
			
			if(listaCompPPComplexo.isEmpty()){  // Caso esteja vazio o collection 
				
				listaProcessoComplexo.setCheckmark(false);
				Listcell listcell = new Listcell();
				
				itemLista.setParent(listaProcessoComplexo);
				itemLista.appendChild(listcell);
				
				listcell.setLabel("Não existem Processos Padrão cadastrados. Por favor, cadastre por meio da ferramenta de Definição de Processo Padrão");
				
			}else{ // Caso não esteja
				
				for (CompPPProcessoComplexo compPP : listaCompPPComplexo) {
					itemLista = new Listitem();
					Listcell listcell = new Listcell();
			
					itemLista.setParent(listaProcessoComplexo);
					itemLista.appendChild(listcell);
			
					listcell.setLabel(compPP.getNome());
					listcell.setValue(compPP);
				}
			}
		}
		
		
		// ----- Criando o botão selecionar -----
		
		Button buttonSalvar = new Button();
		buttonSalvar.setLabel("Selecionar");

		buttonSalvar.setParent(janela);

		buttonSalvar.addEventListener("onClick", new EventListnerSalvar());

	}
	
	class EventListnerSalvar implements EventListener {

		public void onEvent(Event arg0) throws Exception {
			
			if (listaProcessoComplexo.getSelectedItem() != null) {
				
				CompPPr processoProjeto = new CompPPr();
				CompPPProcessoComplexo compPPrSelecionado = (CompPPProcessoComplexo) ((Listcell) listaProcessoComplexo.getSelectedItem().getChildren().get(0)).getValue();
				
				processoProjeto.setPadraoBase(compPPrSelecionado);
				
				ctrl.salvarCompPPr(processoProjeto);
				ctrl.setCompPPrSelecionado(processoProjeto);
				
				janela.onClose();
				
			}			
			
		}

	}
}

















