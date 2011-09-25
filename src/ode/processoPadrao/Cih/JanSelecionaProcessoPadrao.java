package ode.processoPadrao.Cih;

import java.util.Collection;

import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;

import ode.nucleo.crud.cih.JanelaSimples;
import ode.processoPadrao.Cci.CtrlDefinirProcessoPadrao;
import ode.processoPadrao.Cdp.CompPP;

public class JanSelecionaProcessoPadrao {
	private CtrlDefinirProcessoPadrao ctrl;
	private JanelaSimples janela;
	
	public JanSelecionaProcessoPadrao (CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao, JanelaSimples JanelaSimples){
		ctrl = ctrlDefinirProcessoPadrao;
		janela = JanelaSimples;
		
		configuracaoBasica();
		configuraElementosJanela();
		janela.mostrar();
	}

	private void configuraElementosJanela() {
		Vbox vbox = new Vbox();
		Label labelDefinicao = new Label();
		
		labelDefinicao .setValue("Escolha o Processo Padrão que deseja abrir:");
		
		vbox.setParent(janela);
		labelDefinicao.setParent(vbox);
		
		Listbox listaProcessoPadrao = new Listbox();
		Listitem itemLista;

		listaProcessoPadrao.setParent(vbox);
		Listhead listHead = new Listhead();
		
		Collection<CompPP> listaCompPP = ctrl.getAllCompPP();
		for (CompPP compPP : listaCompPP) {
			itemLista = new Listitem();
			Listcell listcell = new Listcell();

			itemLista.setParent(listaProcessoPadrao);
			itemLista.appendChild(listcell);

			listcell.setLabel(compPP.getNome());
		}		
	}

	private void configuracaoBasica() {
		janela.setTitle("Lista de Processos Padrão");
		janela.setWidth("450px");
		janela.setBorder("normal");
		janela.setClosable(true);
		janela.setPosition("&quot;center;&quot;;");
		janela.setSizable(true);
		janela.setMaximizable(true);	
	}
}
