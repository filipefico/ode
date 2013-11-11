package ode.processoPadrao.ciu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.principal.cdp.Conhecimento;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.processoPadrao.cdp.AtividadeProcessoPadrao;
import ode.processoPadrao.cdp.CompPP;
import ode.processoPadrao.cdp.CompPPMacroatividade;
import ode.processoPadrao.cdp.CompPPProcessoComplexo;
import ode.processoPadrao.cdp.CompPPProcessoSimples;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Space;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

public class JanEditarPropriedadesAtividadePadrao extends JanCore{

	private JanelaSimples janela;
	private AtividadeProcessoPadrao Atividade;
	private Listbox listaMarcoSimOuNao;
	
	public JanEditarPropriedadesAtividadePadrao(CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao, AtividadeProcessoPadrao Atv) {
		super(ctrlDefinirProcessoPadrao);
		janela = this;
		Atividade = Atv;
		listaMarcoSimOuNao = new Listbox();
		
		configuraElementosJanela();
		janela.mostrar();
	}
	
	private Label labelNome = new Label("Nome");
	private Label labelDescricao = new Label("Descrição");
	private Label labelMarco = new Label("Esta atividade é um marco?");
	private Textbox textBoxNome = new Textbox();
	private Textbox textBoxDescricao = new Textbox();
		
	private void configuraElementosJanela() {
		janela.setTitle("Editar propriedades básicas da Atividade Padrão");

		configuraPropriedades();

		botaoSalvar();
	}
	
	private void configuraPropriedades() {

		Vbox vbox = new Vbox();
		vbox.setParent(janela);
		vbox.setWidth("100%");

		labelNome.setParent(vbox);
		textBoxNome.setParent(vbox);
		textBoxNome.setWidth("100%");
		new Space().setParent(vbox);
		
		labelDescricao.setParent(vbox);
		textBoxDescricao.setParent(vbox);
		textBoxDescricao.setWidth("100%");
		textBoxDescricao.setRows(3);
		textBoxDescricao.setMultiline(true);
		new Space().setParent(vbox);
		
		
		labelMarco.setParent(vbox);
		
		Listcell listcellSim = new Listcell("Sim");
		Listcell listcellNao = new Listcell("Não");
		Listitem itemListaSim = new Listitem();
		Listitem itemListaNao = new Listitem();
		
		itemListaSim.setParent(listaMarcoSimOuNao);
		itemListaNao.setParent(listaMarcoSimOuNao);
		
		itemListaSim.setValue(new String("Sim"));
		itemListaNao.setValue(new String("Não"));
		
		itemListaSim.appendChild(listcellSim);
		itemListaNao.appendChild(listcellNao);
		
		listaMarcoSimOuNao.setCheckmark(true);
		listaMarcoSimOuNao.setParent(vbox);
		
		
		carregaDadosPropriedades();
	}
	
	protected void botaoSalvar() {
		Button buttonSalvar = new Button();
		buttonSalvar.setLabel("salvar");
		buttonSalvar.addEventListener("onClick", new EventListener() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				salvarDados();
				janela.onClose();
			}
		});
		buttonSalvar.setParent(janela);
	}

	private void salvarDados() {
		Atividade.setNome(textBoxNome.getText());
		Atividade.setDescricao(textBoxDescricao.getText());
		
		if(listaMarcoSimOuNao.getSelectedItem().getIndex() != -1){
			if(listaMarcoSimOuNao.getSelectedItem().getValue().toString().compareTo("Sim") == 0){
				Atividade.setEhMarco(true);
				System.out.println(listaMarcoSimOuNao.getSelectedItem().getValue().toString());
			}else{
				Atividade.setEhMarco(false);
				System.out.println(listaMarcoSimOuNao.getSelectedItem().getValue().toString());
			}
		}
		
		ctrl.atualizarCompPP(ctrl.getcompPPSelecionado());
		
		try {
			Messagebox.show("Propriedades alteradas com sucesso !", "Informação", Messagebox.OK, Messagebox.INFORMATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void carregaDadosPropriedades() {
		textBoxNome.setText(Atividade.getNome());
		textBoxDescricao.setText(Atividade.getDescricao());
	}



}
