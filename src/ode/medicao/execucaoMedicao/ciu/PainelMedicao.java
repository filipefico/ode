package ode.medicao.execucaoMedicao.ciu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.medicao.execucaoMedicao.cdp.ContextMedicao;
import ode.medicao.execucaoMedicao.cdp.Medicao;
import ode.medicao.execucaoMedicao.cdp.ValorMedido;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.api.Tabpanel;

public class PainelMedicao extends Vlayout{

	
	NucleoCombobox<KMedida> cbKMedida;
	NucleoCombobox<RecursoHumano> cbRecursoHumano ;
	NucleoCombobox<DefinicaoOperacionalMedida> cbDefinicao ;
	Textbox decbox ;
	Datebox datebox ;
	NucleoCombobox<Projeto> cbProjeto ;
	NucleoCombobox<KAtividade> cbAtividade;
	Textbox tbContexto = new Textbox();
	CtrlMedicao ctrl;
	
	Listbox listbox = new Listbox();
	Listitem listitemNovo = new Listitem("Novo Contexto");
	Listitem listitemLoad = new Listitem("Contexto Existente");
	
	public void setCtrl(CtrlMedicao ctrl){
		this.ctrl=ctrl;
	}
	
	private class OnMedidaSelecionada implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			cbDefinicao.getItems().clear();
			cbDefinicao.setObjetos(cbKMedida.getObjetoSelecionado().getDefinicoesMedida());
			cbDefinicao.selecionarPrimeiroElemento();
		}
		
	}
	
	private class OnOpcaoContexto implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			if(listbox.getSelectedItem() == listitemNovo){
				tbContexto.setDisabled(false);
			}else{
				tbContexto.setDisabled(true);
			}
		}
		
	}
	
	public void montar(){
		montarCabecalho();
		
		montarAbaDados();
		
		montarRodape();
	}
	public void montar(Medicao objeto) {
		montarCabecalho(objeto);
		
		montarAbaDados();
		
		objetoToPainel(objeto);
		
		montarRodape(objeto);
	}
	
	private void montarCabecalho(){
		Toolbar cabecalho = new Toolbar();
		cabecalho.setStyle("border:0px;background:white;");
		Toolbarbutton tbbtAbrir = new Toolbarbutton("Abrir Medição");
		tbbtAbrir.setImage("/imagens/fileopen.png");
		cabecalho.appendChild(tbbtAbrir);
		tbbtAbrir.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.abrirMedicoesPassadas();
			}
		});
		cabecalho.setParent(this);
	}
	
	private void montarCabecalho(Medicao med){
		Toolbar cabecalho = new Toolbar();
		cabecalho.setStyle("border:0px;background:white;");
		Toolbarbutton tbbtNovo = new Toolbarbutton("Novo");
		Toolbarbutton tbbtExcluir = new Toolbarbutton("Excluir");
		tbbtNovo.setImage("/imagens/filenew.png");
		tbbtExcluir.setImage("/imagens/editdelete.png");
		cabecalho.appendChild(tbbtNovo);
		cabecalho.appendChild(tbbtExcluir);
		tbbtNovo.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.fechar();
				ctrl.iniciar();
			}
		});
		tbbtExcluir.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				ctrl.excluir();
				try {
					Messagebox mbox = new Messagebox();
					mbox.show("Medição excluida(Lembrar de colocar pergunta sim/nao)");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ctrl.fechar();
			}
		});
		cabecalho.setParent(this);
	}
	
	private void montarRodape(){
		Button salvar = new Button("Salvar");
		Toolbar rodape = new Toolbar();
		salvar.setParent(rodape);
		rodape.setStyle("border:0px;background:white;");
		rodape.setAlign("end");
		salvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				try {
					ctrl.salvar();
					Messagebox mbox = new Messagebox();
					mbox.show("Medição salva");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		rodape.setParent(this);
	}
	private void montarRodape(Medicao med){
		Button salvar = new Button("Atualizar");
		Toolbar rodape = new Toolbar();
		salvar.setParent(rodape);
		rodape.setStyle("border:0px;background:white;");
		rodape.setAlign("end");
		salvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				try {
					ctrl.atualizar();
					Messagebox mbox = new Messagebox();
					mbox.show("Medição Alterada");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		rodape.setParent(this);
	}
	
	public void objetoToPainel(Medicao med){
		decbox.setValue(med.getValorMedido().toString());
		cbAtividade.setObjetoSelecionado(med.getAtividade());
		tbContexto.setValue(med.getContexto().toString());
		datebox.setValue(med.getData());
		cbKMedida.setObjetoSelecionado(med.getMedida());
		try {
			(new OnMedidaSelecionada()).onEvent(new Event("onSelect"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cbDefinicao.setObjetoSelecionado(med.getDefinicaoOperacional());
		cbRecursoHumano.setObjetoSelecionado(med.getExecutor());
		cbProjeto.setObjetoSelecionado(med.getProjeto());
	}
	
	public void painelToObjeto(Medicao med){
		try{
			med.setValorMedido(new ValorMedido(decbox.getValue()));
		}catch (NumberFormatException e) {
			try{
				Messagebox mbox = new Messagebox();
				mbox.show("Formator de valor incorreto");
			}catch (Exception p) {
				p.printStackTrace();
			}
		}
		med.setAtividade(cbAtividade.getObjetoSelecionado());
		med.setContexto(new ContextMedicao(tbContexto.getValue()));
		med.setData(datebox.getValue());
		med.setDefinicaoOperacional(cbDefinicao.getObjetoSelecionado());
		med.setExecutor(cbRecursoHumano.getObjetoSelecionado());
		med.setMedida(cbKMedida.getObjetoSelecionado());
		med.setProjeto(cbProjeto.getObjetoSelecionado());
	}
	
	private void montarAbaDados(){
		cbKMedida = new NucleoCombobox<KMedida>();
		cbKMedida.setWidth("100%");
		cbRecursoHumano = new NucleoCombobox<RecursoHumano>();
		cbRecursoHumano.setWidth("100%");
		cbDefinicao = new NucleoCombobox<DefinicaoOperacionalMedida>();
		cbDefinicao.setWidth("100%");
		decbox = new Textbox();
		decbox.setWidth("100%");
		datebox = new Datebox();
		cbProjeto = new NucleoCombobox<Projeto>();
		cbProjeto.setWidth("100%");
		cbAtividade = new NucleoCombobox<KAtividade>();
		cbAtividade.setWidth("100%");
		tbContexto = new Textbox();
		tbContexto.setWidth("100%");
		
		
		GridDados gd = new GridDados();
		
		cbKMedida.setObjetos(ctrl.getTodasMedidas());
		gd.adicionarLinha("Medida", cbKMedida);
		cbKMedida.addEventListener("onSelect", new OnMedidaSelecionada());
		cbKMedida.selecionarPrimeiroElemento();
		
		gd.adicionarLinha("Definição Operacional", cbDefinicao);
		cbDefinicao.setObjetos(cbKMedida.getObjetoSelecionado().getDefinicoesMedida());
		cbDefinicao.selecionarPrimeiroElemento();
		
		gd.adicionarLinha("Valor Medido", decbox);
		
		gd.adicionarLinha("Data da Medição", datebox);
		datebox.setValue(new Date());
		
		cbProjeto.setObjetos(ctrl.getTodosProjetos());
		gd.adicionarLinha("Projeto", cbProjeto);
		cbProjeto.selecionarPrimeiroElemento();
		
		cbRecursoHumano.setObjetos(ctrl.getTodosRecursosHumano());
		gd.adicionarLinha("Executor da Medição", cbRecursoHumano);
		cbRecursoHumano.selecionarPrimeiroElemento();
		
		cbAtividade.setObjetos(ctrl.getTodasAtividades());
		gd.adicionarLinha("Momento da Medição", cbAtividade);
		cbAtividade.selecionarPrimeiroElemento();
		
		Vbox vbox = new Vbox();
		vbox.appendChild(new Label("Contexto da Medição"));
		gd.adicionarLinhaUnica(vbox);
		/*listbox.setCheckmark(true);
		listbox.appendChild(listitemNovo);
		listbox.appendChild(listitemLoad);
		listbox.setSelectedItem(listitemNovo);
		listbox.setParent(vbox);*/
		tbContexto.setRows(4);
		tbContexto.setHflex("1");
		vbox.setHflex("1");
		tbContexto.setParent(vbox);
		/*listbox.addEventListener("onSelect", new OnOpcaoContexto());
		try {
			(new OnOpcaoContexto()).onEvent(new Event("onSelect"));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		gd.setParent(this);
	}
	
	
}
