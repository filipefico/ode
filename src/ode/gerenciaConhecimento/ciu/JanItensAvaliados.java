package ode.gerenciaConhecimento.ciu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import ode.gerenciaConhecimento.cdp.Avaliacao;
import ode.gerenciaConhecimento.cdp.ConhecimentoRelativoDiscussao;
import ode.gerenciaConhecimento.cdp.ItemConhecimento;
import ode.gerenciaConhecimento.cdp.LicaoAprendida;
import ode.gerenciaConhecimento.cdp.Valoracao;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

public class JanItensAvaliados extends Window {

	CtrlGerenciaConhecimento ctrlGerenciaConhecimento;
	
	int qtdeItens = 0; //acho q nem vai precisar desses atributos, pois é só fazer um get
	Label labelTitulo;
	Label labelResumo;
	Label labelCriadoEm;  
	Label labelAutorValor;
	Label labelTipoValor;
	Label labelQtdeAcessosValor;
	Label labelQtdeItensEncontradosValor = new Label();
	Listitem listitem = new Listitem();
	Listbox listboxBuscarItensConhecimento = new Listbox();
	Collection<ItemConhecimento> itens;

	int tamanho = 0;
	double somaCorrecao = 0;
	double somaCompletude = 0;
	double somaConsistencia = 0;
	double somaUtilidade = 0;
	double somaAplicabilidade = 0;
	
	Label labelQtdeAvaliacoes;
	Label labelMediaCorrecaoValor;
	Label labelMediaCompletudeValor;
	Label labelMediaConsistenciaValor;
	Label labelMediaUtilidadeValor;
	Label labelMediaAplicabilidadeValor;
	Label labelMediaTotalNotas;
	
	
	Label labelPercente;
	Label labelCriadoEmValor;
	
	public JanItensAvaliados(CtrlGerenciaConhecimento ctrl) {
		// TODO Auto-generated constructor stub
		
		ctrlGerenciaConhecimento = ctrl;
		
		this.itens = ctrlGerenciaConhecimento.recuperarItensConhecimentoAvaliados();
		
		criarJanItensAvaliados();
	}
	
	public void preencherListboxItensAvaliados(){
				
		for (ItemConhecimento item : this.itens){
			listitem = new Listitem();
			listitem.setValue(item);
			preencherLinhaListbox(item);
			listitem.setParent(listboxBuscarItensConhecimento);
		}
	}	
	
	public void preencherLinhaListbox(ItemConhecimento item){
		
		Listcell listcellRadio = new Listcell("");
		
		//coluna conteudo
		Listcell listcellConteudo = new Listcell();
		Vbox vboxConteudo = new Vbox();
		labelTitulo = new Label(item.getTitulo());
		labelTitulo.setStyle("font-weight: bold; font-style: italic;color: black;");
		labelTitulo.setParent(vboxConteudo);
		labelResumo = new Label(item.getResumo());
		labelResumo.setMultiline(true);
		labelResumo.setParent(vboxConteudo);
		vboxConteudo.setParent(listcellConteudo);
		
		//coluna valorações
		Listcell listcellAvaliacoes = new Listcell();
		Vbox vboxAvaliacoes = new Vbox();
		
		Collection<Avaliacao> itens = item.getAvaliacoes();
		
		//quantidade de avaliacoes
		Hbox hboxQtdeAvaliacoes = new Hbox();
		Hbox hboxMediaCorrecao = new Hbox();
		Hbox hboxMediaConsistencia = new Hbox();
		Hbox hboxMediaCompletude = new Hbox();
		Hbox hboxMediaUtilidade = new Hbox();
		Hbox hboxMediaAplicabilidade = new Hbox();
		Hbox hboxMediaTotal = new Hbox();
		tamanho = itens.size();
		
		DecimalFormat fmt = new DecimalFormat("0.0");    //limita o número de casas decimais     
		if(tamanho > 0){
			//quantidade de avaliacoes
			labelQtdeAvaliacoes = new Label();
			labelQtdeAvaliacoes.setValue(Integer.toString(tamanho));
			labelQtdeAvaliacoes.setStyle("font-weight: bold; color: black;");
			Label labelQuantidade = new Label("Quantidade: ");
			labelQuantidade.setParent(hboxQtdeAvaliacoes);
			labelQtdeAvaliacoes.setParent(hboxQtdeAvaliacoes);
			hboxQtdeAvaliacoes.setParent(vboxAvaliacoes);
			
			//media das notas correcao
			for (Avaliacao avaliacao : itens){
				somaCorrecao += avaliacao.getNotaCorrecao().doubleValue();
			}
			double mediaCorrecao = somaCorrecao/tamanho;
			labelMediaCorrecaoValor = new Label();
			labelMediaCorrecaoValor.setValue(fmt.format(mediaCorrecao));
			labelMediaCorrecaoValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaCorrecao = new Label("Média de correções: ");
			labelMediaCorrecao.setParent(hboxMediaCorrecao);
			labelMediaCorrecaoValor.setParent(hboxMediaCorrecao);
			hboxMediaCorrecao.setParent(vboxAvaliacoes);
			
			//media das notas completude
			for (Avaliacao avaliacao : itens){
				somaCompletude += avaliacao.getNotaCompletude().doubleValue();
			}
			double mediaCompletude = somaCompletude/tamanho;
			labelMediaCompletudeValor = new Label();
			labelMediaCompletudeValor.setValue(fmt.format(mediaCompletude));
			labelMediaCompletudeValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaCompletude = new Label("Média de completude: ");
			labelMediaCompletude.setParent(hboxMediaCompletude);
			labelMediaCompletudeValor.setParent(hboxMediaCompletude);
			hboxMediaCompletude.setParent(vboxAvaliacoes);
			
			//media das notas consistencia
			for (Avaliacao avaliacao : itens){
				somaConsistencia += avaliacao.getNotaConsistencia().doubleValue();
			}
			double mediaConsistencia = somaConsistencia/tamanho;
			labelMediaConsistenciaValor = new Label();
			labelMediaConsistenciaValor.setValue(fmt.format(mediaConsistencia));
			labelMediaConsistenciaValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaConsistencia = new Label("Média de consistência: ");
			labelMediaConsistencia.setParent(hboxMediaConsistencia);
			labelMediaConsistenciaValor.setParent(hboxMediaConsistencia);
			hboxMediaConsistencia.setParent(vboxAvaliacoes);
			
			//media das notas utilidade
			for (Avaliacao avaliacao : itens){
				somaUtilidade += avaliacao.getNotaUtilidade().doubleValue();
			}
			double mediaUtilidade = somaUtilidade/tamanho;
			labelMediaUtilidadeValor = new Label();
			labelMediaUtilidadeValor.setValue(fmt.format(mediaUtilidade));
			labelMediaUtilidadeValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaUtilidade = new Label("Média de utilidade: ");
			labelMediaUtilidade.setParent(hboxMediaUtilidade);
			labelMediaUtilidadeValor.setParent(hboxMediaUtilidade);
			hboxMediaUtilidade.setParent(vboxAvaliacoes);
			
			//media das notas aplicabilidade
			for (Avaliacao avaliacao : itens){
				somaAplicabilidade += avaliacao.getNotaAplicabilidade().doubleValue();
			}
			double mediaAplicabilidade = somaAplicabilidade/tamanho;
			labelMediaAplicabilidadeValor = new Label();
			labelMediaAplicabilidadeValor.setValue(fmt.format(mediaAplicabilidade));
			labelMediaAplicabilidadeValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaAplicabilidade = new Label("Média de aplicabilidade: ");
			labelMediaAplicabilidade.setParent(hboxMediaAplicabilidade);
			labelMediaAplicabilidadeValor.setParent(hboxMediaAplicabilidade);
			hboxMediaAplicabilidade.setParent(vboxAvaliacoes);
			
			//media total das notas
			double mediaTotal = (mediaAplicabilidade + mediaUtilidade + mediaCompletude + mediaConsistencia + mediaCorrecao)/5;
			labelMediaTotalNotas = new Label();
			labelMediaTotalNotas.setValue(fmt.format(mediaTotal));
			labelMediaTotalNotas.setStyle("font-weight: bold; color: black;");
			Label labelMediaTotal = new Label("Média total das notas: ");
			labelMediaTotal.setParent(hboxMediaTotal);
			labelMediaTotalNotas.setParent(hboxMediaTotal);
			hboxMediaTotal.setParent(vboxAvaliacoes);
			
			somaCorrecao = 0;
			somaCompletude = 0;
			somaConsistencia = 0;
			somaUtilidade = 0;
			somaAplicabilidade = 0;
			
		}else{
			
			//quantidade de avaliacoes
			labelQtdeAvaliacoes = new Label();
			labelQtdeAvaliacoes.setValue("0");
			labelQtdeAvaliacoes.setStyle("font-weight: bold; color: black;");
			Label labelQuantidade = new Label("Quantidade: ");
			labelQuantidade.setParent(hboxQtdeAvaliacoes);
			labelQtdeAvaliacoes.setParent(hboxQtdeAvaliacoes);
			hboxQtdeAvaliacoes.setParent(vboxAvaliacoes);
			
			//media das notas correcao
			labelMediaCorrecaoValor = new Label();
			labelMediaCorrecaoValor.setValue("0");
			labelMediaCorrecaoValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaCorrecao = new Label("Média de correções: ");
			labelMediaCorrecao.setParent(hboxMediaCorrecao);
			labelMediaCorrecaoValor.setParent(hboxMediaCorrecao);
			hboxMediaCorrecao.setParent(vboxAvaliacoes);
			
			//media das notas completude
			labelMediaCompletudeValor = new Label();
			labelMediaCompletudeValor.setValue("0");
			labelMediaCompletudeValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaCompletude = new Label("Média de completude: ");
			labelMediaCompletude.setParent(hboxMediaCompletude);
			labelMediaCompletudeValor.setParent(hboxMediaCompletude);
			hboxMediaCompletude.setParent(vboxAvaliacoes);
			
			//media das notas consistencia
			labelMediaConsistenciaValor = new Label();
			labelMediaConsistenciaValor.setValue("0");
			labelMediaConsistenciaValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaConsistencia = new Label("Média de consistência: ");
			labelMediaConsistencia.setParent(hboxMediaConsistencia);
			labelMediaConsistenciaValor.setParent(hboxMediaConsistencia);
			hboxMediaConsistencia.setParent(vboxAvaliacoes);
			
			//media das notas utilidade
			labelMediaUtilidadeValor = new Label();
			labelMediaUtilidadeValor.setValue("0");
			labelMediaUtilidadeValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaUtilidade = new Label("Média de utilidade: ");
			labelMediaUtilidade.setParent(hboxMediaUtilidade);
			labelMediaUtilidadeValor.setParent(hboxMediaUtilidade);
			hboxMediaUtilidade.setParent(vboxAvaliacoes);
			
			//media das notas aplicabilidade
			labelMediaAplicabilidadeValor = new Label();
			labelMediaAplicabilidadeValor.setValue("0");
			labelMediaAplicabilidadeValor.setStyle("font-weight: bold; color: black;");
			Label labelMediaAplicabilidade = new Label("Média de aplicabilidade: ");
			labelMediaAplicabilidade.setParent(hboxMediaAplicabilidade);
			labelMediaAplicabilidadeValor.setParent(hboxMediaAplicabilidade);
			hboxMediaAplicabilidade.setParent(vboxAvaliacoes);
			
			//media total das notas
			labelMediaTotalNotas = new Label();
			labelMediaTotalNotas.setValue("0");
			labelMediaTotalNotas.setStyle("font-weight: bold; color: black;");
			Label labelMediaTotal = new Label("Média total das notas: ");
			labelMediaTotal.setParent(hboxMediaTotal);
			labelMediaTotalNotas.setParent(hboxMediaTotal);
			hboxMediaTotal.setParent(vboxAvaliacoes);
		}
		
		tamanho = 0;
		vboxAvaliacoes.setParent(listcellAvaliacoes);
		
		//coluna informações
		Listcell listcellInformacoes = new Listcell();
		Vbox vboxInformacoes = new Vbox();
		
		//Data de criação
		Hbox hboxDataCriacao = new Hbox();
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		labelCriadoEmValor = new Label(out.format(item.getDataCriacao()));
		labelCriadoEmValor.setStyle("font-weight: bold; color: black;");
		Label labelCriadoEm = new Label("Criado em: ");
		
		labelCriadoEm.setParent(hboxDataCriacao);
		labelCriadoEmValor.setParent(hboxDataCriacao);
		hboxDataCriacao.setParent(vboxInformacoes);
	
		
		//autor
		Hbox hboxAutor = new Hbox();
		labelAutorValor = new Label(item.getAutor().getNome());
		labelAutorValor.setStyle("font-weight: bold; color: black;");
		Label labelAutor = new Label("Autor: ");
		
		labelAutor.setParent(hboxAutor);
		labelAutorValor.setParent(hboxAutor);
		hboxAutor.setParent(vboxInformacoes);
		
		Hbox hboxTipo = new Hbox();		
		
		if (item instanceof LicaoAprendida) {
			labelTipoValor = new Label("Lição Aprendida");
			labelTipoValor.setStyle("font-weight: bold; color: black;");
			Label labelTipo = new Label("Tipo: ");
			
			labelTipo.setParent(hboxTipo);
			labelTipoValor.setParent(hboxTipo);
			hboxTipo.setParent(vboxInformacoes);
		} 
		if (item instanceof ConhecimentoRelativoDiscussao) {
			labelTipoValor = new Label("Conhecimento Relativo a uma Discussão");
			labelTipoValor.setStyle("font-weight: bold; color: black;");
			Label labelTipo = new Label("Tipo: ");
			
			labelTipo.setParent(hboxTipo);
			labelTipoValor.setParent(hboxTipo);
			hboxTipo.setParent(vboxInformacoes);
		} 
	
		//quantidade de acessos
		Hbox hboxQtdeAcessos = new Hbox();
		labelQtdeAcessosValor = new Label(item.getQuantidadeAcessos().toString());
		labelQtdeAcessosValor.setStyle("font-weight: bold; color: black;");
		Label labelQtdeAcessos = new Label("Quantidade de Acessos: ");
		
		labelQtdeAcessos.setParent(hboxQtdeAcessos);
		labelQtdeAcessosValor.setParent(hboxQtdeAcessos);
		hboxQtdeAcessos.setParent(vboxInformacoes);
		
		vboxInformacoes.setParent(listcellInformacoes);
		
		listcellRadio.setParent(listitem);
		listcellConteudo.setParent(listitem);
		listcellAvaliacoes.setParent(listitem);
		listcellInformacoes.setParent(listitem);
		
		
	}

	public void criarJanItensAvaliados(){
		
		this.setTitle("Itens Avaliados");
		this.setBorder("normal");
		
		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		labelQtdeItensEncontradosValor.setValue(String.valueOf(itens.size()));
		Label labelQtdeItensEncontrados = new Label("Quantidade de Itens Encontrados: " + labelQtdeItensEncontradosValor.getValue());
		labelQtdeItensEncontrados.setStyle("font-weight: bold;font-style: italic;");
		
		labelQtdeItensEncontrados.setParent(vbox);
		
		//Criar as colunas do Listbox
		listboxBuscarItensConhecimento.setMultiple(false);
		listboxBuscarItensConhecimento.setCheckmark(true);
		listboxBuscarItensConhecimento.setSizedByContent(true);
		listboxBuscarItensConhecimento.setHeight("350px");
		
		Listhead listheadBuscarItensConhecimento = new Listhead();
		Listheader listheaderRadio = new Listheader(" ");
		listheaderRadio.setWidth("25px");
		Listheader listheaderConteudo = new Listheader("Conteúdo");
		listheaderConteudo.setWidth("185px");
		Listheader listheaderValoracoes = new Listheader("Avaliações");
		listheaderValoracoes.setWidth("183px");
		Listheader listheaderInformacoes = new Listheader("Informações");
		listheaderInformacoes.setWidth("100%");
		
		listheaderRadio.setParent(listheadBuscarItensConhecimento);
		listheaderConteudo.setParent(listheadBuscarItensConhecimento);
		listheaderValoracoes.setParent(listheadBuscarItensConhecimento);
		listheaderInformacoes.setParent(listheadBuscarItensConhecimento);
		listheadBuscarItensConhecimento.setParent(listboxBuscarItensConhecimento);
		
		preencherListboxItensAvaliados();

		listboxBuscarItensConhecimento.setParent(vbox);
		
		Button botaoVisualizar = new Button("Visualizar");
		Button botaoAvaliar = new Button("Avaliar");
		Toolbar toolbarInferior = new Toolbar();

		botaoVisualizar.addEventListener("onClick", new EventListener() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub

				if(listboxBuscarItensConhecimento.getSelectedIndex() == -1){
					Messagebox messageboxInformar = new Messagebox();
					messageboxInformar.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, messageboxInformar.INFORMATION);
				}else{
					Object objeto = new Object();
					if (listboxBuscarItensConhecimento.getSelectedItem() != null) {
						objeto =  listboxBuscarItensConhecimento.getSelectedItem().getValue();
					}
					
					if(objeto != null){
						((ItemConhecimento)objeto).setQuantidadeAcessos(((ItemConhecimento)objeto).getQuantidadeAcessos() + 1);
						ctrlGerenciaConhecimento.aplCadastrarItemConhecimento.salvar((ItemConhecimento)objeto);
						ctrlGerenciaConhecimento.exibirJanelaVisualizarItemConhecimento((ItemConhecimento)objeto);
					}
				}
				
			}
		});
		
		botaoAvaliar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				// TODO Auto-generated method stub
				
				if(listboxBuscarItensConhecimento.getSelectedIndex() == -1){
					Messagebox messageboxInformar = new Messagebox();
					messageboxInformar.show("Por favor, selecione um Item de Conhecimento", "Informação", Messagebox.OK, messageboxInformar.INFORMATION);
				}else{
					Object objeto = new Object();
					if (listboxBuscarItensConhecimento.getSelectedItem() != null) {
						objeto =  listboxBuscarItensConhecimento.getSelectedItem().getValue();
					}
					
					if(objeto != null){
						//chamar funcao para avaliar o item de conhecimento
					}
				}
			}
		});

		

		toolbarInferior.setStyle("border:0px;background:white;");
		toolbarInferior.setAlign("end");

		toolbarInferior.appendChild(botaoVisualizar);
		toolbarInferior.appendChild(botaoAvaliar);

		toolbarInferior.setParent(vbox);
		
		vbox.setParent(this);
		
	}
	
}
