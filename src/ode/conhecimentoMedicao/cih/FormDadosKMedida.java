package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Listitem;

import ode.conhecimentoMedicao.cci.CtrlKDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cci.CtrlKMedidaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKValorEscalaCRUD;
import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.KMetodoAnalitico;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cdp.KUnidadeMedida;
import ode.conhecimentoMedicao.cdp.NaturezaMedida;
import ode.conhecimentoMedicao.cdp.TipoEscala;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

public class FormDadosKMedida extends FormularioDadosCRUD<KMedida> {

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Textbox tbMnemonico = new Textbox();
	private NucleoListbox<KUnidadeMedida> lbUnidadeMedida = new NucleoListbox<KUnidadeMedida>();
	private NucleoListbox<NaturezaMedida> lbNaturezaMedida = new NucleoListbox<NaturezaMedida>();
	private NucleoMultipleListBox<KTipoEntidadeMensuravel> lbTipoMensuravel = new NucleoMultipleListBox<KTipoEntidadeMensuravel>();
	private NucleoMultipleListBox<KNecessidadeInformacao> lbNecessidadeInformacao = new NucleoMultipleListBox<KNecessidadeInformacao>();
	private NucleoListbox<KElementoMensuravel> lbElementoMensuravel = new NucleoListbox<KElementoMensuravel>();
	private NucleoListbox<TipoEscala> lbTipoEscala = new NucleoListbox<TipoEscala>();
	private NucleoListbox<KEscala> lbEscala = new NucleoListbox<KEscala>();
	private NucleoMultipleListBox<KMedida> lbCorrelata = new NucleoMultipleListBox<KMedida>();
	private NucleoMultipleListBox<KMedida> lbDerivada = new NucleoMultipleListBox<KMedida>();
	private CtrlKMedidaCRUD ctrl;
	private Groupbox gbElementoMensuravel;
	private Groupbox gbEscala;
	private EventListener onSelectetTipoMensuravel = new EventListener() {
		
		@Override
		public void onEvent(Event arg0) throws Exception {
			KTipoEntidadeMensuravel tipo = lbTipoMensuravel.getObjetoSelecionado();
			Collection<KElementoMensuravel> eleMens = ctrl.getAplKTipoEntidadeMensuravel().recuperarPorTipo(tipo);
			lbElementoMensuravel.detach();
			lbElementoMensuravel = new NucleoMultipleListBox<KElementoMensuravel>();
			gbElementoMensuravel.appendChild(lbElementoMensuravel);
			lbElementoMensuravel.setCheckmark(true);
			lbElementoMensuravel.setObjetos(eleMens);
			if(lbElementoMensuravel.getItemCount()>0){lbElementoMensuravel.selecionarPrimeiroElemento();}
		}
	};
	private EventListener onSelectTipoEscala = new EventListener() {
		
		@Override
		public void onEvent(Event arg0) throws Exception {
			TipoEscala tipo = lbTipoEscala.getObjetoSelecionado();
			Collection<KEscala> eleMens = ctrl.getAplKEscala().recuperarPorTipo(tipo);
			lbEscala.detach();
			lbEscala = new NucleoListbox<KEscala>();
			gbEscala.appendChild(lbEscala);
			lbEscala.setCheckmark(true);
			lbEscala.setObjetos(eleMens);
			if(lbEscala.getItemCount()>0){lbEscala.selecionarPrimeiroElemento();}
		}
	};
	private EventListener onSelectNatureza = new EventListener() {
		
		@Override
		public void onEvent(Event arg0) throws Exception {
			NaturezaMedida nm = lbNaturezaMedida.getObjetoSelecionado();
			switch(nm){
			case BASE:
				lbDerivada.setCheckable(false);
				lbDerivada.clearSelection();
				break;
			case DERIVADA:
				lbDerivada.setCheckable(true);
				break;
			}
		}
	};
	
	@Override
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		ctrl = (CtrlKMedidaCRUD) this.getControlador();
		// ////////////////////////////
		// Dados Cadastro
		// ////////////////////////////
		NucleoTab tabDadosCadastro = new NucleoTab();

		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("385px");
		tbNome.setMaxlength(300);
		tbNome.setHeight("35px");
		tbNome.setMultiline(true);
		gridDadosCadastro
				.adicionarLinhaObrigatoria(
						NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
						tbNome);

		tbDescricao.setWidth("385px");
		tbDescricao.setMaxlength(400);
		tbDescricao.setHeight("65px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),
				tbDescricao);
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		tbMnemonico.setWidth("285px");
		gridDadosCadastro.adicionarLinhaObrigatoria(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_MNEMONICO),
				tbMnemonico);
		
		lbNaturezaMedida.setObjetos(Arrays.asList(NaturezaMedida.values()));
		lbNaturezaMedida.selecionarPrimeiroElemento();
		lbNaturezaMedida.setCheckmark(true);
		lbNaturezaMedida.addEventListener("onSelect", onSelectNatureza);
		gridDadosCadastro.adicionarLinhaObrigatoria("Natureza da Medida", lbNaturezaMedida);
		
		lbUnidadeMedida.setObjetos((Collection<KUnidadeMedida>) ctrl
				.getAplKUnidadeMedida().recuperarTodos());
		lbUnidadeMedida.setCheckmark(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_UNIDADE_MEDIDA),
				lbUnidadeMedida);
		
		listaTabs.add(tabDadosCadastro);
		
		
		//////////////////
		//Necessidade de informacao
		/////////////////
		NucleoTab necessidadeInformacaoTab = new NucleoTab();
		
		necessidadeInformacaoTab.setNomeTab("Necessidade de Informacao");
		
		lbNecessidadeInformacao.setObjetos((Collection<KNecessidadeInformacao>)ctrl.getAplNecessidadeInformacao().recuperarTodos());
		
		necessidadeInformacaoTab.setConteudoTab(lbNecessidadeInformacao);
		
		listaTabs.add(necessidadeInformacaoTab);
		//////////////////
		//Tipo e entidade mensuravel
		/////////////////
		NucleoTab tipoEntidadeMensuravelTab = new NucleoTab();
		
		tipoEntidadeMensuravelTab.setNomeTab("Elemento Mensuravel");
		
		Groupbox gbTipoEntidade = new Groupbox();
		
		gbTipoEntidade.appendChild(new Caption("Tipo Entidade Mensuravel"));
		
		lbTipoMensuravel.setObjetos(ctrl.getAplKTipoEntidadeMensuravel().recuperarTodos());
		lbTipoMensuravel.addEventListener("onSelect", onSelectetTipoMensuravel);
		lbTipoMensuravel.selecionarPrimeiroElemento();
		gbTipoEntidade.appendChild(lbTipoMensuravel);
		
		gbElementoMensuravel = new Groupbox();
		
		gbElementoMensuravel.appendChild(new Caption("Elemento Mensuravel"));
		
		gbElementoMensuravel.appendChild(lbElementoMensuravel);
		
		
		Vbox boxEntidade = new Vbox();
		boxEntidade.appendChild(gbTipoEntidade);
		boxEntidade.appendChild(gbElementoMensuravel);
		
		tipoEntidadeMensuravelTab.setConteudoTab(boxEntidade);
		try{onSelectetTipoMensuravel.onEvent(null);}catch (Exception e) {}
		
		listaTabs.add(tipoEntidadeMensuravelTab);
		
		//////////////////
		//Escala
		/////////////////
		NucleoTab escalaTab = new NucleoTab();
		
		escalaTab.setNomeTab("Escala");
		
		Groupbox gbTipoEscala = new Groupbox();
		
		gbTipoEscala.appendChild(new Caption("Tipo Escala"));
		
		lbTipoEscala.setObjetos(Arrays.asList(TipoEscala.values()));
		lbTipoEscala.addEventListener("onSelect", onSelectTipoEscala);
		lbTipoEscala.setCheckmark(true);
		lbTipoEscala.selecionarPrimeiroElemento();
		gbTipoEscala.appendChild(lbTipoEscala);
		
		gbEscala = new Groupbox();
		
		gbEscala.appendChild(new Caption("Escala"));
		try{onSelectTipoEscala.onEvent(null);}catch (Exception e) {}
		
		gbEscala.appendChild(lbEscala);
		
		
		Vbox boxEscala = new Vbox();
		boxEscala.appendChild(gbTipoEscala);
		boxEscala.appendChild(gbEscala);
		
		escalaTab.setConteudoTab(boxEscala);
		
		listaTabs.add(escalaTab);
		
		/////////////////
		////DerivaDe
		//////////////////
		NucleoTab derivadaTab = new NucleoTab();
		
		derivadaTab.setNomeTab("Medidas Derivadas");
		
		lbDerivada.setObjetos(ctrl.definirAplCRUD().recuperarTodos());
		
		lbDerivada.addEventListener("onSelect", onSelectNatureza);
		
		try{onSelectNatureza.onEvent(null);}catch(Exception e){};
		
		derivadaTab.setConteudoTab(lbDerivada);
		
		listaTabs.add(derivadaTab);
		/////////////////
		////MedidasCorrelatas
		//////////////////

		NucleoTab correlatasTab = new NucleoTab();
		
		correlatasTab.setNomeTab("Medidas Correlatas");
		
		lbCorrelata.setObjetos(ctrl.definirAplCRUD().recuperarTodos());
		
		correlatasTab.setConteudoTab(lbCorrelata);
		
		listaTabs.add(correlatasTab);
		
		/////////////////////////
		///Definicao Operacional Medida
		////////////////////////
		
		NucleoTab defOperMed = new NucleoTab();
		
		defOperMed.setNomeTab("Definição Operacional");
		
		((CtrlKMedidaCRUD)this.getControlador()).getCtrlKDefinicaoOperacional().definirAba(defOperMed);
		((CtrlKMedidaCRUD)this.getControlador()).getCtrlKDefinicaoOperacional().iniciar();
		
		listaTabs.add(defOperMed);
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosTela(KMedida objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		tbMnemonico.setText(objeto.getMnemonico());
		lbNaturezaMedida.setObjetoSelecionado(objeto.getNaturezaMedida());
		lbUnidadeMedida.setObjetoSelecionado(objeto.getUnidadeMedida());
		lbTipoEscala.setObjetoSelecionado(objeto.getEscala().getTipo());
		lbEscala.setObjetoSelecionado(objeto.getEscala());
		lbTipoMensuravel.setObjetosSelecionados(objeto.getTiposEntidadeMensuraveis());
		lbElementoMensuravel.setObjetoSelecionado(objeto.getPropriedadeMedida());
		lbDerivada.setObjetosSelecionados(objeto.getDerivadaDe());
		lbCorrelata.setObjetosSelecionados(objeto.getMedidasCorrelatas());
		lbNecessidadeInformacao.setObjetosSelecionados(objeto.getNecessidadesInformacao());
		((CtrlKMedidaCRUD)this.getControlador()).getCtrlKDefinicaoOperacional().atualizarPesquisa(objeto);
	}

	@Override
	protected void preencherDadosObjeto(KMedida objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setMnemonico(tbMnemonico.getText());
		objeto.setNaturezaMedida(lbNaturezaMedida.getObjetoSelecionado());
		objeto.setUnidadeMedida(lbUnidadeMedida.getObjetoSelecionado());
		objeto.setEscala(lbEscala.getObjetoSelecionado());
		objeto.setTiposEntidadeMensuraveis(lbTipoMensuravel.getObjetosSelecionados());
		objeto.setPropriedadeMedida(lbElementoMensuravel.getObjetoSelecionado());
		if(lbNaturezaMedida.getObjetoSelecionado()==NaturezaMedida.BASE){
			lbDerivada.clearSelection();
		}
		objeto.setDerivadaDe(lbDerivada.getObjetosSelecionados());
		objeto.setMedidasCorrelatas(lbCorrelata.getObjetosSelecionados());
		objeto.setNecessidadesInformacao(lbNecessidadeInformacao.getObjetosSelecionados());
		objeto.setDefinicoesMedida(((CtrlKMedidaCRUD)this.getControlador()).getListagemKDefinicaoOperacional());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");
		tbDescricao.setConstraint("no empty");
	}
	
	@Override
	protected void configurarComponentesExtensao(){
		this.setWidth("900px");
		((JanelaSimples)this.getParent()).setWidth("auto");
		((JanelaSimples)this.getParent()).setHeight("auto");
	}

}
