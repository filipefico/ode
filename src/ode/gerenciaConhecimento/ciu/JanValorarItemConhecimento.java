package ode.gerenciaConhecimento.ciu;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import ode._infraestruturaBase.util.NucleoContexto;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.Valoracao;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
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
	Listcell listcellClassificacaoValor = new Listcell();
	Label classificacao = new Label();
	Decimalbox decimalboxGrauUtilidade;
	Textbox textboxComentario;
	
	ItemConhecimento itemConhecimento;
	
	public static String VALORACAO_POSITIVA = "Positiva";
	public static String VALORACAO_NEGATIVA = "Negativa";
	public static String VALORACAO_NEUTRA = "Neutra";
	
	public JanValorarItemConhecimento(CtrlGerenciaConhecimento ctrl, ItemConhecimento itemConhecimento) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		this.itemConhecimento = itemConhecimento;
		
		criarJanValorarItemConhecimento();
	}
	
	public Label getClassificacao(){
		
		
		BigDecimal bigDecimalPositiva1 = new BigDecimal("10.0");
		BigDecimal bigDecimalPositiva2 = new BigDecimal("0.01");
		
		BigDecimal bigDecimalNegativa1 = new BigDecimal("-10.0");
		BigDecimal bigDecimalNegativa2 = new BigDecimal("-0.01");
		
		BigDecimal bigDecimalNeutra = new BigDecimal("0.00");
		
		BigDecimal valoracao;
		valoracao = decimalboxGrauUtilidade.getValue();

		
		if(valoracao.doubleValue() >= bigDecimalNegativa1.doubleValue() && valoracao.doubleValue() <= bigDecimalNegativa2.doubleValue()){
			classificacao.setValue(VALORACAO_NEGATIVA);
		}
		
		if(valoracao.doubleValue() == bigDecimalNeutra.doubleValue()){
			classificacao.setValue(VALORACAO_NEUTRA);
		}
		
		if(valoracao.doubleValue() >= bigDecimalPositiva2.doubleValue() && valoracao.doubleValue() <= bigDecimalPositiva1.doubleValue()){
			classificacao.setValue(VALORACAO_POSITIVA);
		}
		
		return classificacao;
		
	}
	
	
	
	public void preencherListbox(){
		
		//linha Autor
		Listitem listitemAutor = new Listitem();
		Listcell listcellAutor = new Listcell("Autor: ");
		listcellAutorValor = new Listcell(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano().getNome()); //
		
		listcellAutor.setParent(listitemAutor);
		listcellAutorValor.setParent(listitemAutor);
		listitemAutor.setParent(listboxValoracao);
			
		//linha Data da Valoração
		Listitem listitemDataValoracao = new Listitem();
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		Listcell listcellDataValoracao = new Listcell("Data da Valoração: ");
		listcellDataValoracaoValor = new Listcell(formatador.format(data)); //
		
		listcellDataValoracao.setParent(listitemDataValoracao);
		listcellDataValoracaoValor.setParent(listitemDataValoracao);
		listitemDataValoracao.setParent(listboxValoracao);
		
		//linha classificaçao
		Listitem listitemClassificacao = new Listitem();
		Listcell listcellClassificacao = new Listcell("Classificação:");
		
		listcellClassificacao.setParent(listitemClassificacao);
		listcellClassificacaoValor.setParent(listitemClassificacao);
		
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
		decimalboxGrauUtilidade.setConstraint("no empty"); // não permitirá vazio
		decimalboxGrauUtilidade.addEventListener("onBlur", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				classificacao = getClassificacao();
				classificacao.setParent(listcellClassificacaoValor);
			}
		});
		
		vboxGrauUtilidade.setParent(listcellGrauUtilidadeValor);
		
		listcellGrauUtilidade.setParent(listitemGrauUtilidade);
		listcellGrauUtilidadeValor.setParent(listitemGrauUtilidade);
		listitemGrauUtilidade.setParent(listboxValoracao);
		
		listitemClassificacao.setParent(listboxValoracao);
		
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
		
		botaoCancelar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimentoUsuarioComum(itemConhecimento);
			}
		});
		
		botaoSalvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				Valoracao valoracao = new Valoracao();
				
				if(decimalboxGrauUtilidade.getValue().doubleValue() >= -10.0 && decimalboxGrauUtilidade.getValue().doubleValue() <= 10.0){
			
					valoracao.setGrauUtilidade(decimalboxGrauUtilidade.getValue());
					valoracao.setComentario(textboxComentario.getValue());
					valoracao.setAutor(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
					valoracao.setDataValoracao(new Date());
					ctrlGerenciaConhecimento.adicionarValoracao(valoracao, itemConhecimento);
					/*ItemConhecimento item = new ItemConhecimento();
					item = itemConhecimento;*/
					
					Messagebox messageboxSalvar = new Messagebox();
					Messagebox.show("Valoração salva com sucesso!", "Informação", Messagebox.OK, messageboxSalvar.INFORMATION, new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							if(arg0.getName().equals("onOK")){
								ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimentoUsuarioComum(itemConhecimento);
							}
						}
					});
					
					
					
				}else{
					Messagebox.show("O Grau de Utilidade deve estar entre -10.0 e 10.0!", "Informação", Messagebox.OK, Messagebox.INFORMATION);
				}
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
