package ode.controleCaracteristica.ciu;

import java.awt.Checkbox;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

import ode._controleRecursoHumano.ciu.CtrlRecursoHumanoCRUD;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode.conhecimento.organizacao.cdp.NivelKCompetencia;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgd.KAtividadeDAO;
import ode.conhecimento.processo.cgd.KProcessoDAO;
import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.EnumNivelImportancia;
import ode.controleCaracteristica.cdp.EnumTipoItemSoftware;
import ode.controleCaracteristica.cdp.Importancia;
import ode.controleCaracteristica.cdp.PerspectivaAnalise;
import ode.controleCaracteristica.cgd.CaracteristicaDAO;
import ode.controleCaracteristica.cgd.ImportanciaDAO;

public class FormDadosPerspectivaAnalise extends FormularioDadosCRUD<PerspectivaAnalise>{
	
	private static final long serialVersionUID = 1L;
	
	ImportanciaDAO dao = (ImportanciaDAO) SpringUtil.getApplicationContext().getBean(ImportanciaDAO.class);
	
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	
	private GridDados gridDadosCadastro = new GridDados();
	private GridDados gridDadosCaracteristicas = new GridDados();
	private GridDados gridKAtividades = new GridDados();
	private GridDados gridKProcessos = new GridDados();
	
	private NucleoListbox<EnumTipoItemSoftware>  listboxTiposSoftware = new NucleoListbox<EnumTipoItemSoftware>();
	private NucleoMultipleListBox<Listitem> listboxCaracteristicas = new NucleoMultipleListBox<Listitem>();
	private NucleoMultipleListBox<KAtividade> listKatividades = new NucleoMultipleListBox<KAtividade>();
	private NucleoMultipleListBox<KProcesso> listKprocessos = new NucleoMultipleListBox<KProcesso>();

	private Listhead listHead = new Listhead();
	
	
	public  void carregaComboboxImportancia (NucleoListbox<EnumNivelImportancia> listboxNivelImportancia){
			Collection<EnumNivelImportancia> listaImportancia = Arrays.asList(EnumNivelImportancia.values());

			for (EnumNivelImportancia  P : listaImportancia ) {
					Listitem item = new Listitem(P.toString(), P);
					listboxNivelImportancia.appendChild(item);
			}
		}

	
	public  void carregaKAtividadesListbox (NucleoMultipleListBox<KAtividade> listKatividades){
		
		KAtividadeDAO Req = (KAtividadeDAO) SpringUtil.getApplicationContext().getBean(KAtividadeDAO.class);
		Collection<KAtividade> listaAllAtividades = Req.recuperarTodos();


		for (KAtividade  A : listaAllAtividades ) {
						
			Listitem item = new Listitem();
			item.setValue(A);
			item.setLabel(A.getNome());
			
			listKatividades.appendChild(item);
		}
	}

	
	
	public  void carregaKProcessosListbox (NucleoMultipleListBox<KProcesso> Kprocessos){
		
		KProcessoDAO Req = (KProcessoDAO) SpringUtil.getApplicationContext().getBean(KProcessoDAO.class);
		Collection<KProcesso> listaKProcessos = Req.recuperarTodos();


		for (KProcesso  A : listaKProcessos ) {
								
				Listitem item = new Listitem();
				item.setValue(A);
				item.setLabel(A.getNome());
				
				Kprocessos.appendChild(item);
				
		}
	}
	

	
	public  void carregaCaracteristicasListbox (){
		
		listboxCaracteristicas.getItems().clear();
		
		CaracteristicaDAO Req = (CaracteristicaDAO) SpringUtil.getApplicationContext().getBean(CaracteristicaDAO.class);
		//Collection<Caracteristica> listaCaracteristicas = Req.recuperarCaracteristicaPorTipoSoftware((EnumTipoItemSoftware)listboxTiposSoftware.getSelectedItem().getValue());
		Collection<Caracteristica> listaCaracteristicas = Req.recuperarTodos();
		
		for (Caracteristica  C : listaCaracteristicas ) {
				if((C.getSetEnum().contains(listboxTiposSoftware.getSelectedItem().getValue())) ){
					 
					Listitem item = new Listitem();
					
					//Nome da Caracteristica
					Listcell cellNome = new Listcell(C.getNome());
					cellNome.setParent(item);
					cellNome.setValue(C);
					
					// Combobox do nivel de importancia
					NucleoListbox<EnumNivelImportancia>  listboxNivelImportancia = new NucleoListbox<EnumNivelImportancia>();
					listboxNivelImportancia.setWidth("140px");
					listboxNivelImportancia.setMold("select");
					carregaComboboxImportancia(listboxNivelImportancia);
					listboxNivelImportancia.setSelectedIndex(0);
					
					Listcell cellImportancia = new Listcell();
					cellImportancia.setParent(item);
					cellImportancia.setValue(listboxNivelImportancia);
					cellImportancia.appendChild(listboxNivelImportancia);
					
					// Combobox da obrigatoriedade da caracteristica
					Combobox comboObrigatoriedade = new Combobox();
					comboObrigatoriedade.appendItem("Sim");
					comboObrigatoriedade.appendItem("Não");
					comboObrigatoriedade.setWidth("60px");
					
					Listcell cellObrigatoriedade = new Listcell();
					cellObrigatoriedade.setParent(item);
					cellObrigatoriedade.setValue(comboObrigatoriedade);
					cellObrigatoriedade.appendChild(comboObrigatoriedade);

					item.setParent(listboxCaracteristicas);
					
				}
		}
	}
	
	
	//Auxilia a funcao preencherDadosObjeto();
	public EnumTipoItemSoftware buscaTipoPorNome (String nome){
	
		Collection<EnumTipoItemSoftware> listaTipos = Arrays.asList(EnumTipoItemSoftware.values());
		for (EnumTipoItemSoftware Ti : listaTipos) {
			if (Ti.toString().equals(nome)){
				return Ti;
			}	
		}
		return null;
	}
	

	
	@Override
	protected List definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// Tabs
		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		NucleoTab tabCaracteristicas= new NucleoTab();
		tabCaracteristicas.setNomeTab("Caracteristicas Aplicáveis");
		
		NucleoTab tabKAtividades= new NucleoTab();
		tabKAtividades.setNomeTab("KAtividades");
		
		NucleoTab tabKProcessos= new NucleoTab();
		tabKProcessos.setNomeTab("KProcessos");
		
		// Atribui o nome:
		tbNome.setWidth("285px");
		tbNome.setMaxlength(100);		
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		//Atribui a descrição:
		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);
		
		//Listbox de Tipo de Itens de Software	
		listboxTiposSoftware.setWidth("240px");
		listboxTiposSoftware.setRows(1);
		listboxTiposSoftware.setMold("select");
		
		listboxTiposSoftware.addEventListener("onClick", new EventListener() {	
			@Override
			public void onEvent(Event arg0) throws Exception {
				carregaCaracteristicasListbox();				
			}
		});
		
		gridDadosCadastro.adicionarLinhaObrigatoria("Tipo de Item de Software", listboxTiposSoftware);

		try {
		
			Collection<EnumTipoItemSoftware> listaTipos ;
			
			listaTipos = Arrays.asList(EnumTipoItemSoftware.values());
			//listboxTiposSoftware.addObjetos(listaTipos);
				for (EnumTipoItemSoftware objeto : listaTipos) {
					Listitem listitem = new Listitem(objeto.toString(), objeto);
					listboxTiposSoftware.appendChild(listitem);
				}
		
		}
		catch(Exception e) {				
		}

		listboxTiposSoftware.setSelectedItem(listboxTiposSoftware.getItemAtIndex(0));
		
		//Listbox Caracteristicas Aplicáveis:
		listHead.setParent(listboxCaracteristicas);
		Listheader listHeaderCaracteristica = new Listheader("Caracteristica");
		listHeaderCaracteristica.setParent(listHead);
		listHeaderCaracteristica.setWidth("45%");
		Listheader listHeaderImportancia = new Listheader("Nivel de Importancia");
		listHeaderImportancia.setParent(listHead);
		listHeaderImportancia.setWidth("35%");
		Listheader listHeaderObrigatoriedade = new Listheader("Obrigatório?");
		listHeaderObrigatoriedade.setParent(listHead);
		listHeaderObrigatoriedade.setWidth("20%");
		
		carregaCaracteristicasListbox();
				
		//Listibox de KAtividades
		carregaKAtividadesListbox(listKatividades);
		gridKAtividades.adicionarLinha("Tipo de Atividade", listKatividades);
		
		//Listbox de KProcessos
		carregaKProcessosListbox(listKprocessos);
		gridKProcessos.adicionarLinha("Tipo de Processo", listKprocessos);
	
		//Adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		tabCaracteristicas.setConteudoTab(listboxCaracteristicas);
		tabKAtividades.setConteudoTab(gridKAtividades);
		tabKProcessos.setConteudoTab(gridKProcessos);
		
		listaTabs.add(tabDadosCadastro);
		listaTabs.add(tabCaracteristicas);
		listaTabs.add(tabKAtividades);
		listaTabs.add(tabKProcessos);
		
		return listaTabs;
	}
	
	//Cria uma instancia da classe Importancia. Auxilia a funcao de preencherDadosObjeto
	public Set<Importancia> novaImportancia(){
		
		Collection<Listitem> lista = listboxCaracteristicas.getSelectedItems();
		Set<Importancia> setImportancia = new HashSet<Importancia>();
		
		for(Listitem L: lista){
			
			Importancia i = new Importancia();
						
			i.setCaracteristica((Caracteristica) ((Listcell)L.getChildren().get(0)).getValue());
	    	i.setNivel( (EnumNivelImportancia)(((NucleoListbox) ((Listcell)L.getChildren().get(1)).getValue()).getSelectedItem()).getValue());
			
	    	///System.out.println(((Combobox)((Listcell)L.getChildren().get(2)).getValue()).getSelectedItem().getLabel());
			if(((Combobox)((Listcell)L.getChildren().get(2)).getValue()).getSelectedItem().getLabel().equals("Sim")){
				i.setObrigatoriedade(true);
			}
			else{
				i.setObrigatoriedade(false);
			}
			
			dao.salvar(i);
			
			setImportancia.add(i);
		}
		
		
		return setImportancia;
	}
	
	//Atualiza o ListboxCaracteristicas com as importancias de cada uma. Auxilia a funcao de preencherDadosTela
		public void adicionaImportancia(Set<Importancia> setImportancia){
			listboxCaracteristicas.getItems().clear();
			
			CaracteristicaDAO Req = (CaracteristicaDAO) SpringUtil.getApplicationContext().getBean(CaracteristicaDAO.class);
			Collection<Caracteristica> listaCaracteristicas = Req.recuperarTodos();
			
			for (Caracteristica  C : listaCaracteristicas ) {
					if((C.getSetEnum().contains(listboxTiposSoftware.getSelectedItem().getValue())) ){
						
						Listitem item = new Listitem();
						
						//Nome da Caracteristica
						Listcell cellNome = new Listcell(C.getNome());
						cellNome.setParent(item);
						cellNome.setValue(C);
						
						// Combobox do nivel de importancia
						NucleoListbox<EnumNivelImportancia>  listboxNivelImportancia = new NucleoListbox<EnumNivelImportancia>();
						listboxNivelImportancia.setWidth("140px");
						listboxNivelImportancia.setMold("select");
						carregaComboboxImportancia(listboxNivelImportancia);
						listboxNivelImportancia.setSelectedIndex(0);
						
						Listcell cellImportancia = new Listcell();
						cellImportancia.setParent(item);
						cellImportancia.setValue(listboxNivelImportancia);
						cellImportancia.appendChild(listboxNivelImportancia);
						
						// Combobox da obrigatoriedade da caracteristica
						Combobox comboObrigatoriedade = new Combobox();
						comboObrigatoriedade.appendItem("Sim");
						comboObrigatoriedade.appendItem("Não");
						comboObrigatoriedade.setWidth("60px");
						
						Listcell cellObrigatoriedade = new Listcell();
						cellObrigatoriedade.setParent(item);
						cellObrigatoriedade.setValue(comboObrigatoriedade);
						cellObrigatoriedade.appendChild(comboObrigatoriedade);			
						
						for(Importancia i : setImportancia){
							if(i.getCaracteristica().equals(C)){
								listboxNivelImportancia.setObjetoSelecionado(i.getNivel());
								if(i.getObrigatoriedade()){
									comboObrigatoriedade.setSelectedIndex(0);
								}
								else{
									comboObrigatoriedade.setSelectedIndex(1);
								}
								item.setSelected(true);
							}
						}
						
						item.setParent(listboxCaracteristicas);
				
					}
			}
			
		}
		
	
	@Override
	protected void preencherDadosObjeto(PerspectivaAnalise objeto) {		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setTipo(buscaTipoPorNome(listboxTiposSoftware.getObjetoSelecionado().toString()));
		objeto.addKAtividades(listKatividades.getObjetosSelecionados());
		objeto.addKProcessos(listKprocessos.getObjetosSelecionados());
		objeto.setImportancia(this.novaImportancia());
		
	}


	@Override
	protected void preencherDadosTela(PerspectivaAnalise objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		listboxTiposSoftware.setObjetoSelecionado(objeto.getTipo());
		listKatividades.setObjetosSelecionados((Collection)objeto.getKAtividades());
		listKprocessos.setObjetosSelecionados((Collection)objeto.getKProcessos());
		this.adicionaImportancia(objeto.getImportancia());

		
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		//tbDescricao.setConstraint("no empty");
	}
	
	
}