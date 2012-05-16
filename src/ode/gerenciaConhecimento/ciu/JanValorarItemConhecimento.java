package ode.gerenciaConhecimento.ciu;

import java.math.BigDecimal;

import org.zkoss.math.BigDecimals;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanValorarItemConhecimento extends Window {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	Listbox listboxValoracao = new Listbox();
	Listcell listcellAutorValor;
	Listcell listcellDataValoracaoValor;
	Listcell listcellClassificacaoValor;
	Decimalbox decimalboxGrauUtilidade;
	Textbox textboxComentario;
	
	public JanValorarItemConhecimento(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		criarJanValorarItemConhecimento();
	}
	
	public void preencherListbox(){
		
		//linha Autor
		Listitem listitemAutor = new Listitem();
		Listcell listcellAutor = new Listcell("Autor: ");
		listcellAutorValor = new Listcell(""); //
		
		listcellAutor.setParent(listitemAutor);
		listcellAutorValor.setParent(listitemAutor);
		listitemAutor.setParent(listboxValoracao);
		
		//linha Data da Valoração
		Listitem listitemDataValoracao = new Listitem();
		Listcell listcellDataValoracao = new Listcell("Data da Valoração:");
		listcellDataValoracaoValor = new Listcell(""); //
		
		listcellDataValoracao.setParent(listitemDataValoracao);
		listcellDataValoracaoValor.setParent(listitemDataValoracao);
		listitemDataValoracao.setParent(listboxValoracao);
		
		//linha Grau de Utilidade
		Listitem listitemGrauUtilidade = new Listitem();
		Listcell listcellGrauUtilidade = new Listcell("Grau de Utilidade:");
		Listcell listcellGrauUtilidadeValor = new Listcell();
		
		Vbox vboxGrauUtilidade = new Vbox();
		Label label_1 = new Label("Uma nota de -10 a 10 deve ser informada.");
		Label label_2 = new Label("Valoração com nota de -10,00 a -0,01 é classificada como negativa.");
		Label label_3 = new Label("Valoração com nota igual a 0 (zero) é classificada como neutra.");
		Label label_4 = new Label("Valoração com nota de 0,01 a 10,00 é classificada como positiva.");
		decimalboxGrauUtilidade = new Decimalbox();
		
		label_1.setParent(vboxGrauUtilidade);
		label_2.setParent(vboxGrauUtilidade);
		label_3.setParent(vboxGrauUtilidade);
		label_4.setParent(vboxGrauUtilidade);
		decimalboxGrauUtilidade.setParent(vboxGrauUtilidade);
		
		vboxGrauUtilidade.setParent(listcellGrauUtilidadeValor);
		
		listcellGrauUtilidade.setParent(listitemGrauUtilidade);
		listcellGrauUtilidadeValor.setParent(listitemGrauUtilidade);
		listitemGrauUtilidade.setParent(listboxValoracao);
		
		//linha Classificação
	/*	Listitem listitemClassificacao = new Listitem();
		Listcell listcellClassificacao = new Listcell("Classificação:");

		
		BigDecimal limiteInferior_1 = new BigDecimal(-10.00);
		BigDecimal limiteInferior_2 = new BigDecimal(-0.01);
		BigDecimal limiteNeutro = new BigDecimal(0.00);
		BigDecimal limiteSuperior_1 = new BigDecimal(0.01);
		BigDecimal limiteSuperior_2 = new BigDecimal(10.00);

		if(decimalboxGrauUtilidade.getValue().compareTo(limiteInferior_2) == -1 &&
				decimalboxGrauUtilidade.getValue().compareTo(limiteInferior_1) == 1){
			listcellClassificacaoValor = new Listcell("Negativa");
			listcellClassificacaoValor.setStyle("font-weight: bold; color: red;");
		}*/
		
		
	/*	if(decimalboxGrauUtilidade.getValue().longValue() == limiteNeutro){
			listcellClassificacaoValor = new Listcell("Neutro");
			listcellClassificacaoValor.setStyle("font-weight: bold; color: black;");
		}
		if(decimalboxGrauUtilidade.getValue().longValue() >= limiteSuperior_1 &&
				decimalboxGrauUtilidade.getValue().longValue() <= limiteSuperior_2){
			listcellClassificacaoValor = new Listcell("Positiva");
			listcellClassificacaoValor.setStyle("font-weight: bold; color: blue;");
		}
		
		listcellClassificacao.setParent(listitemClassificacao);
		listcellClassificacaoValor.setParent(listitemClassificacao);
		listitemClassificacao.setParent(listboxValoracao);*/
		
		//linha Comentário
		Listitem listitemComentario = new Listitem();
		Listcell listcellComentario = new Listcell("Comentário:");
		Listcell listcellComentarioValor = new Listcell();
		
		textboxComentario = new Textbox();

		textboxComentario.setText("");
		textboxComentario.setHeight("100px");
		textboxComentario.setWidth("330px");
		textboxComentario.setRows(3);

		listcellComentario.setParent(listitemComentario);
		textboxComentario.setParent(listcellComentarioValor);
		listcellComentarioValor.setParent(listitemComentario);
		listitemComentario.setParent(listboxValoracao);
		
		
	}
	
	public void criarJanValorarItemConhecimento(){
		
		this.setTitle("Valorar Item de Conhecimento");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		
		Tabbox tabboxValorItemConhecimento = new Tabbox();

		Tabs tabsValorarItemConhecimento = new Tabs();		
		Tab tabValoracao = new Tab("Valoração");

		tabValoracao.setParent(tabsValorarItemConhecimento);
		tabsValorarItemConhecimento.setParent(tabboxValorItemConhecimento);
		
		Tabpanels tabpanels = new Tabpanels();
		Tabpanel tabpanel = new Tabpanel();
		
		listboxValoracao.setSizedByContent(true);
		listboxValoracao.setWidth("560px");
		
		preencherListbox();
		
		listboxValoracao.setParent(tabpanel);
		tabpanel.setParent(tabpanels);
		tabpanels.setParent(tabboxValorItemConhecimento);
		
		tabboxValorItemConhecimento.setParent(vbox);
		
		
		//Botões
		Button botaoSalvar = new Button("Salvar");
		Button botaoCancelar = new Button("Cancelar");
		Toolbar toolbarInferior = new Toolbar();
		
		botaoSalvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
			}
		});
		
		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");
			
		toolbarInferior.appendChild(botaoSalvar);
		toolbarInferior.appendChild(botaoCancelar);
		vbox.appendChild(toolbarInferior);

		vbox.setParent(this);
		
		
		
	}

}
