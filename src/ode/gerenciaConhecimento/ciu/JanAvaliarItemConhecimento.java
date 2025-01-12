package ode.gerenciaConhecimento.ciu;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import ode._infraestruturaBase.util.NucleoContexto;
import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanAvaliarItemConhecimento extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	ItemConhecimento itemConhecimento;
	
	BigDecimal notaCorrecao = new BigDecimal("0");
	BigDecimal notaCompletude = new BigDecimal("0");
	BigDecimal notaConsistencia = new BigDecimal("0");
	BigDecimal notaUtilidade = new BigDecimal("0");
	BigDecimal notaAplicabilidade = new BigDecimal("0");
	
	Decimalbox decimalboxCorrecao = new Decimalbox(notaCorrecao);
	Decimalbox decimalboxCompletude = new Decimalbox(notaCompletude);
	Decimalbox decimalboxConsistencia = new Decimalbox(notaConsistencia);
	Decimalbox decimalboxUtilidade = new Decimalbox(notaUtilidade);
	Decimalbox decimalboxAplicabilidade = new Decimalbox(notaAplicabilidade);
	Listcell listcellMediaNotasValor = new Listcell();
	Label media = new Label();
	Textbox textboxParecer = new Textbox("");
	Combobox comboboxResultadoFinal = new Combobox();
	
	JanAvaliarItemConhecimento(CtrlGerenciaConhecimento ctrl, ItemConhecimento itemConhecimento){
		
		ctrlGerenciaConhecimento = ctrl;
		this.itemConhecimento = itemConhecimento;
		
		criarJanAvaliarItemConhecimento();
		
	}
	
	public BigDecimal getNota(Decimalbox nota){
		BigDecimal n = nota.getValue();
		
		return n;
	}
	
	public String calculaMediaNotas(){
		double media = (notaAplicabilidade.doubleValue() + notaCompletude.doubleValue() 
				+ notaConsistencia.doubleValue() + notaCorrecao.doubleValue() 
				+ notaUtilidade.doubleValue())/5;
		
		//Label label = new Label(Double.toString(media));
		String label = Double.toString(media);
		
		return label;
	}
	
	public void criarJanAvaliarItemConhecimento(){
		
		this.setTitle("Avaliar Item de Conhecimento");
		this.setBorder("normal");
		
		//////////////////////////////////////////
		// Criar a Vbox Principal
		//////////////////////////////////////////
		
		Vbox vboxPrincipal = new Vbox();
		vboxPrincipal.setWidth("100%");
		vboxPrincipal.setParent(this);
		
		//titulo
		Hbox hboxTitulo = new Hbox();
		Label labelTitulo = new Label("T�tulo: ");
		Label labelTituloValor = new Label(itemConhecimento.getTitulo());
		labelTituloValor.setStyle("font-weight: bold; color: black;");
		labelTitulo.setParent(hboxTitulo);
		labelTituloValor.setParent(hboxTitulo);
		hboxTitulo.setParent(vboxPrincipal);
		
		//autor
		Hbox hboxAutor = new Hbox();
		Label labelAutor = new Label("Autor: ");
		Label labelAutorValor = new Label(itemConhecimento.getAutor().getNome());
		labelAutorValor.setStyle("font-weight: bold; color: black;");
		labelAutor.setParent(hboxAutor);
		labelAutorValor.setParent(hboxAutor);
		hboxAutor.setParent(vboxPrincipal);
		
		(new Separator()).setParent(vboxPrincipal);
		
		//avaliador
		Hbox hboxAvaliador = new Hbox();
		Label labelAvaliador = new Label("Avaliador: ");
		Label labelAvaliadorValor = new Label(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano().getNome());
		labelAvaliadorValor.setStyle("font-weight: bold; color: black;");
		labelAvaliador.setParent(hboxAvaliador);
		labelAvaliadorValor.setParent(hboxAvaliador);
		hboxAvaliador.setParent(vboxPrincipal);
		
		//data
		Hbox hboxData = new Hbox();
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		Label labelDataAvaliacao = new Label("Data da Avalia��o: ");
		Label labelDataAvaliacaoValor = new Label(formatador.format(data));
		labelDataAvaliacaoValor.setStyle("font-weight: bold; color: black;");
		labelDataAvaliacao.setParent(hboxData);
		labelDataAvaliacaoValor.setParent(hboxData);
		hboxData.setParent(vboxPrincipal);
		
		(new Separator()).setParent(vboxPrincipal);
		
		// Cria o hbox do meio
		Hbox hboxCentral = new Hbox();
		hboxCentral.setParent(vboxPrincipal);
		
		// Cria o vbox das notas
		Vbox vboxNotas = new Vbox();
		vboxNotas.setParent(hboxCentral);
		
		Label labelNotas= new Label("Notas");
		labelNotas.setParent(vboxNotas);
		
		// criar o listbox para as notas
		Listbox listboxNotas = new Listbox();
		listboxNotas.setSizedByContent(true);
		
		Listhead listheadNotas = new Listhead();
		Listheader listheaderCriterio = new Listheader("Crit�rio");
		Listheader listheaderNota = new Listheader("Nota");
		
		listheaderCriterio.setParent(listheadNotas);
		listheaderNota.setParent(listheadNotas);
		listheadNotas.setParent(listboxNotas);
		
		Listitem listitemCorrecao = new Listitem();
		Listcell listcellCorrecao = new Listcell("Corre��o:");
		Listcell listcellTextboxCorrecao = new Listcell();
		
		
		decimalboxCorrecao.setConstraint("no negative: A nota deve estar entre 0 e 10");
		decimalboxCorrecao.addEventListener("onBlur", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				if(decimalboxCorrecao.getValue() != null){
					notaCorrecao = getNota(decimalboxCorrecao);
					media.setValue(calculaMediaNotas());
				}
			}
		});
		decimalboxCorrecao.setParent(listcellTextboxCorrecao);
		listcellCorrecao.setParent(listitemCorrecao);
		listcellTextboxCorrecao.setParent(listitemCorrecao);
		listitemCorrecao.setParent(listboxNotas);
		
		Listitem listitemCompletude = new Listitem();
		Listcell listcellCompletude = new Listcell("Completude:");
		Listcell listcellTextboxCompletude = new Listcell();
				
		decimalboxCompletude.setParent(listcellTextboxCompletude);
	//	decimalboxCompletude.setConstraint("no empty");
		decimalboxCompletude.setConstraint("no negative: A nota deve estar entre 0 e 10");
		decimalboxCompletude.addEventListener("onBlur", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				if(decimalboxCompletude.getValue() != null){
					notaCompletude = getNota(decimalboxCompletude);
					media.setValue(calculaMediaNotas());
				}
			}
		});
		listcellCompletude.setParent(listitemCompletude);
		listcellTextboxCompletude.setParent(listitemCompletude);
		listitemCompletude.setParent(listboxNotas);
		
		Listitem listitemConsistencia = new Listitem();
		Listcell listcellConsistencia = new Listcell("Consist�ncia:");
		Listcell listcellTextboxConsistencia = new Listcell();
		
		decimalboxConsistencia.setParent(listcellTextboxConsistencia);
	//	decimalboxConsistencia.setConstraint("no empty");
		decimalboxConsistencia.setConstraint("no negative: A nota deve estar entre 0 e 10");
		decimalboxConsistencia.addEventListener("onBlur", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				if(decimalboxConsistencia.getValue() != null){
					notaConsistencia = getNota(decimalboxConsistencia);
					media.setValue(calculaMediaNotas());
				}
			}
		});
		listcellConsistencia.setParent(listitemConsistencia);
		listcellTextboxConsistencia.setParent(listitemConsistencia);
		listitemConsistencia.setParent(listboxNotas);
		
		Listitem listitemUtilidade = new Listitem();
		Listcell listcellUtilidade = new Listcell("Utilidade:");
		Listcell listcellTextboxUtilidade = new Listcell();
		
		decimalboxUtilidade.setParent(listcellTextboxUtilidade);
	//	decimalboxUtilidade.setConstraint("no empty");
		decimalboxUtilidade.setConstraint("no negative: A nota deve estar entre 0 e 10");
		decimalboxUtilidade.addEventListener("onBlur", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				if(decimalboxUtilidade.getValue() != null){
					notaUtilidade = getNota(decimalboxUtilidade);
					media.setValue(calculaMediaNotas());
				}
			}
		});
		listcellUtilidade.setParent(listitemUtilidade);
		listcellTextboxUtilidade.setParent(listitemUtilidade);
		listitemUtilidade.setParent(listboxNotas);
		
		Listitem listitemAplicabilidade = new Listitem();
		Listcell listcellAplicabilidade = new Listcell("Aplicabilidade:");
		Listcell listcellTextboxAplicabilidade = new Listcell();
		
		decimalboxAplicabilidade.setParent(listcellTextboxAplicabilidade);
	//	decimalboxAplicabilidade.setConstraint("no empty");
		decimalboxAplicabilidade.setConstraint("no negative: A nota deve estar entre 0 e 10");
		decimalboxAplicabilidade.addEventListener("onBlur", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				if(decimalboxAplicabilidade.getValue() != null){
					notaAplicabilidade = getNota(decimalboxAplicabilidade);
					media.setValue(calculaMediaNotas());
				}
			}
		});
		listcellAplicabilidade.setParent(listitemAplicabilidade);
		listcellTextboxAplicabilidade.setParent(listitemAplicabilidade);
		listitemAplicabilidade.setParent(listboxNotas);
		
		
		Listitem listitemMediaNotas = new Listitem();
		Listcell listcellMediaNotas = new Listcell("M�dia das Notas:");
		
		media.setParent(listcellMediaNotasValor);
		listcellMediaNotas.setParent(listitemMediaNotas);
		listcellMediaNotasValor.setParent(listitemMediaNotas);
		listitemMediaNotas.setParent(listboxNotas);
	
		
		listboxNotas.setParent(vboxNotas);
		
		Vbox vboxResultado = new Vbox();
		vboxResultado.setParent(hboxCentral);
		Label labelParecer = new Label("Parecer: ");
		textboxParecer.setRows(3);
		textboxParecer.setWidth("350px");
		
		labelParecer.setParent(vboxResultado);
		textboxParecer.setParent(vboxResultado);
		
		(new Separator()).setParent(vboxResultado);
		
		Label labelResultadoFinal = new Label("Resultado Final:");
		comboboxResultadoFinal.setWidth("180px");
		Comboitem comboitemAprovado = new Comboitem("Aprovado");
		Comboitem comboitemAprovadoComModificacoes = new Comboitem("Aprovado com Modifica��es");
		Comboitem comboitemNaoAprovado = new Comboitem("N�o Aprovado");
		Comboitem comboitemIndefinido = new Comboitem("Indefinido");
		
		comboitemAprovado.setParent(comboboxResultadoFinal);
		comboitemAprovadoComModificacoes.setParent(comboboxResultadoFinal);
		comboitemNaoAprovado.setParent(comboboxResultadoFinal);
		comboitemIndefinido.setParent(comboboxResultadoFinal);
		
		labelResultadoFinal.setParent(vboxResultado);
		comboboxResultadoFinal.setParent(vboxResultado);
		
		//////////////////////////////////////////
		// Criar buttons
		//////////////////////////////////////////
		
		Button botaoSalvar = new Button("Salvar");
		Button botaoCancelar = new Button("Cancelar");
		Toolbar toolbarInferior = new Toolbar();

		botaoSalvar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				
				String msg = "";
				
				if (decimalboxAplicabilidade.getValue() == null || decimalboxAplicabilidade.getValue().doubleValue() > 10)
					msg += "- Aplicabilidade deve ser informada com valores entre 0.0 e 10.0\n";
				if (decimalboxCompletude.getValue() == null || decimalboxCompletude.getValue().doubleValue() > 10)
					msg += "- Completude deve ser informada com valores entre 0.0 e 10.0\n";
				if (decimalboxConsistencia.getValue() == null || decimalboxConsistencia.getValue().doubleValue() > 10)
					msg += "- Consist�ncia deve ser informada com valores entre 0.0 e 10.0\n";
				if (decimalboxCorrecao.getValue() == null || decimalboxCorrecao.getValue().doubleValue() > 10)
					msg += "- Corre��o deve ser informada com valores entre 0.0 e 10.0\n";
				if (decimalboxUtilidade.getValue() == null || decimalboxUtilidade.getValue().doubleValue() > 10)
					msg += "- Utilitdade deve ser informada com valores entre 0.0 e 10.0\n";
				if (textboxParecer.getValue() == null)
					msg += "- Parecer deve ser informado.\n";
				if (comboboxResultadoFinal.getSelectedItem() == null)
					msg += "- Resultado final deve ser informado.\n";

				if (msg.isEmpty()) {
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setAutor(NucleoContexto.recuperarUsuarioLogado().getRecursoHumano());
					avaliacao.setDataAvaliacao(new Date());
					avaliacao.setNotaAplicabilidade(decimalboxAplicabilidade.getValue());
					avaliacao.setNotaCompletude(decimalboxCompletude.getValue());
					avaliacao.setNotaConsistencia(decimalboxConsistencia.getValue());
					avaliacao.setNotaCorrecao(decimalboxCorrecao.getValue());
					avaliacao.setNotaUtilidade(decimalboxUtilidade.getValue());
					avaliacao.setParecer(textboxParecer.getValue());
					avaliacao.setResultadoFinal((String)comboboxResultadoFinal.getSelectedItem().getLabel());
					ctrlGerenciaConhecimento.salvarAvaliacaoItemConhecimento(avaliacao, itemConhecimento);
					Messagebox messageboxSalvar = new Messagebox();
					Messagebox.show("Avalia��o salva com sucesso!", "Informa��o", Messagebox.OK, messageboxSalvar.INFORMATION, new EventListener() {
						
						@Override
						public void onEvent(Event arg0) throws Exception {
							// TODO Auto-generated method stub
							if(arg0.getName().equals("onOK")){
								ctrlGerenciaConhecimento.exibirJanelaItensPendentesAvaliacao();
							}
						}
					});
				}else{
					Messagebox.show(msg);
				}
				
				
			}
		});

		botaoCancelar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				Messagebox.show("Deseja realmente cancelar a Avalia��o?", "Aviso", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						// TODO Auto-generated method stub
						if (arg0.getName().equals("onYes")){
							Messagebox.show("Avalia��o cancelada!", "Informa��o", Messagebox.OK, Messagebox.INFORMATION);
							ctrlGerenciaConhecimento.exibirJanelaItensPendentesAvaliacao();
						}
					}
				});

			}
		});

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoSalvar);
		toolbarInferior.appendChild(botaoCancelar);

		toolbarInferior.setParent(this);
	}
	
	
}
