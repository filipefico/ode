package ode.controleCaracteristica.ciu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.controleCaracteristica.cdp.Caracteristica;
import ode.controleCaracteristica.cdp.CaracteristicaValorNaoOrdenado;
import ode.controleCaracteristica.cdp.CaracteristicaValorOrdenado;
import ode.controleCaracteristica.cdp.EnumTipoItemSoftware;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;
import ode.controleCaracteristica.cdp.PossivelValorOrdenado;

public class FormDadosCaracteristica extends FormularioDadosCRUD<Caracteristica>{
	
	private static final long serialVersionUID = 1L;

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	
	// Tabs
	private List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();	
	private NucleoTab tabDadosCadastro = new NucleoTab();
	private NucleoTab tabPossiveisValores= new NucleoTab();
	private GridDados gridDadosCadastro = new GridDados();
	
	private Checkbox checkValorOrdenado = new Checkbox();
	private Checkbox checkValorNaoOrdenado = new Checkbox();
	
	private Vbox vboxGroupbox = new Vbox();
	private Groupbox groupByCaracteristica2 = new Groupbox();	
	private Groupbox groupByCaracteristica3 = new Groupbox();

	private NucleoMultipleListBox<EnumTipoItemSoftware> multipleListBoxTipo = new NucleoMultipleListBox<EnumTipoItemSoftware>();
	private NucleoMultipleListBox<String> multipleListBoxPermite = new NucleoMultipleListBox<String>();

	private CtrlPossivelValorOrdenado ctrlPO = (CtrlPossivelValorOrdenado) SpringUtil.getApplicationContext().getBean(CtrlPossivelValorOrdenado.class);
	private CtrlPossivelValorNaoOrdenado ctrlPNO = (CtrlPossivelValorNaoOrdenado) SpringUtil.getApplicationContext().getBean(CtrlPossivelValorNaoOrdenado.class);
	private boolean atribuicao = false;
	private boolean criacao = false;
	
	private class SelectionChanged implements EventListener{
		@Override
		public void onEvent(Event arg0) throws Exception {
			/*if(multipleListBoxPermite.getItemAtIndex(0).isSelected()){
				atribuicao = true;
			}else{
				atribuicao = false;
			}
				
			if(multipleListBoxPermite.getItemAtIndex(1).isSelected()){
				criacao = true;
			}
			else{
				criacao = false;
			}*/
		}
	}
	
	class EventoCheckbox implements EventListener {
		@Override
		public void onEvent(Event checkbox) throws Exception {
			// altera o comportamento visual de acordo com o tipo selecionado
			
			checkValorOrdenado.setChecked(false);
			checkValorNaoOrdenado.setChecked(false);
			((Checkbox) checkbox.getTarget()).setChecked(true);
					
			if(checkValorNaoOrdenado.isChecked()){
				multipleListBoxPermite.setVisible(true);
				gridDadosCadastro.adicionarLinha("Comportamento em caracterizações", multipleListBoxPermite);
				CaracteristicaValorNaoOrdenado novoObjeto = new CaracteristicaValorNaoOrdenado();
				setObjetoCadastroDados(novoObjeto);
				
				ctrlPNO.setCaracteristica(getObjetoCadastroDados());
				ctrlPNO.iniciar();	
			}
			else{
				//caso o usuário já tenha clicado uma vez no Valor nao ordenado e decidiu mudar a opcao:
				if(getObjetoCadastroDados() instanceof CaracteristicaValorNaoOrdenado){
					CaracteristicaValorOrdenado novoObjeto2 = new CaracteristicaValorOrdenado();
					setObjetoCadastroDados(novoObjeto2);
					gridDadosCadastro.getLastChild().removeChild(gridDadosCadastro.getLastChild().getLastChild());
				}
				ctrlPO.setCaracteristica(getObjetoCadastroDados());
				ctrlPO.iniciar();
			}

			///Faz abrir a aba de possiveis valores
			
				

		}

	}
	
	public void incluirTabPossiveisValores(){
		
		if(checkValorNaoOrdenado.isChecked()){
			PainelCRUD<PossivelValorNaoOrdenado> p = ctrlPNO.getPainelCRUD();
			tabPossiveisValores.setConteudoTab(p);
		}
		else{
			PainelCRUD<PossivelValorOrdenado> p = ctrlPO.getPainelCRUD();
			tabPossiveisValores.setConteudoTab(p);
		}
	}
	
	
	@Override
	protected List<NucleoTab> definirTabs() {
		
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));
		tabPossiveisValores.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_POSSIVEIS_VALORES));
		
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
		

		groupByCaracteristica2.setVisible(false);
		groupByCaracteristica2.setWidth("245px");
		
		//Campos do vboxcheck
		vboxGroupbox.setWidth("135px");
		checkValorOrdenado.setParent(vboxGroupbox);
		checkValorOrdenado.setLabel(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_VALOR_ORDENADO));
		checkValorNaoOrdenado.setParent(vboxGroupbox);
		checkValorNaoOrdenado.setLabel(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_VALOR_NAO_ORDENADO));
		EventoCheckbox eventoCheckbox = new EventoCheckbox();
		checkValorNaoOrdenado.addEventListener("onCheck", eventoCheckbox);
		checkValorOrdenado.addEventListener("onCheck", eventoCheckbox);
		gridDadosCadastro.adicionarLinha(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_TIPO),vboxGroupbox);
		
		///Para Caracteristicas de Valor Nao Ordenado:
		multipleListBoxPermite.addObjeto(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PERMITE_ATRIBUICAO_DE_MULTI_VALOR));
		multipleListBoxPermite.addObjeto(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PERMITE_CRIACAO_DE_POSSIVEL_VALOR));
		multipleListBoxPermite.addEventListener("onChange", new SelectionChanged());
		multipleListBoxPermite.setParent(groupByCaracteristica3);
		multipleListBoxPermite.setVisible(false);
		
		
		// ////////////////////////////
		// Dados Cadastro - Tipo de Item de Software
		// ////////////////////////////
		Label label = new Label();
		label.setParent(groupByCaracteristica3);
		label.setValue("Tipo de itens de Software");
		Collection<EnumTipoItemSoftware> tipos = Arrays.asList(EnumTipoItemSoftware.values());
		multipleListBoxTipo.setObjetos(tipos);
		multipleListBoxTipo.addEventListener("onChange", new SelectionChanged());
		multipleListBoxTipo.setParent(groupByCaracteristica3);
		
		gridDadosCadastro.adicionarLinha("Aplicabilidades",groupByCaracteristica3);

		
		
		//Adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);

		listaTabs.add(tabDadosCadastro);
		listaTabs.add(tabPossiveisValores);
		
		
		return listaTabs;
	}
	
	@Override
	protected void preencherDadosObjeto(Caracteristica objeto){

		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		objeto.setSetEnum(multipleListBoxTipo.getObjetosSelecionados());
		
		if(checkValorNaoOrdenado.isChecked()){
			
			
			Set<String> permite = multipleListBoxPermite.getObjetosSelecionados();
			for(String P: permite){
				if(P.equals(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PERMITE_ATRIBUICAO_DE_MULTI_VALOR))){
					atribuicao = true;
				}
				
				if(P.equals(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PERMITE_CRIACAO_DE_POSSIVEL_VALOR))){
					
					criacao = true;
				}
			}
			
			((CaracteristicaValorNaoOrdenado) objeto).setPermiteCriarPossiveisValores(criacao);
			((CaracteristicaValorNaoOrdenado) objeto).setPermiteMultivalor(atribuicao);
			
		}
		else{
			((CaracteristicaValorOrdenado) objeto).setValores(objeto.getPossiveisValores());
		}
		
		
	}

	@Override
	protected void preencherDadosTela(Caracteristica objeto) 
			throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		multipleListBoxTipo.setObjetosSelecionados(objeto.getSetEnum());
		
		if(objeto instanceof CaracteristicaValorNaoOrdenado){
			
			checkValorNaoOrdenado.setChecked(true);
			if(((CaracteristicaValorNaoOrdenado) objeto).ispermiteMultivalor()){
				multipleListBoxPermite.setSelectedIndex(0);
			}
			if(((CaracteristicaValorNaoOrdenado) objeto).isPermiteCriarPossiveisValores()){
				multipleListBoxPermite.setSelectedIndex(1);
			}
			
			multipleListBoxPermite.setVisible(true);
			gridDadosCadastro.adicionarLinha("Comportamento em caracterizações", multipleListBoxPermite);
			
			ctrlPNO.setCaracteristica(objeto);
			ctrlPNO.iniciar();
		}
		else{
			checkValorOrdenado.setChecked(true);
			ctrlPO.setCaracteristica(objeto);
			ctrlPO.iniciar();
		}
				
	}
}
