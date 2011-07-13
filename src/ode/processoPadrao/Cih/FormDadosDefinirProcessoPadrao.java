package ode.processoPadrao.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import ode.conhecimento.processo.Cdp.KAtividade;
import ode.conhecimento.processo.Cdp.KProcesso;
import ode.conhecimento.processo.Cgd.KAtividadeDAO;
import ode.conhecimento.processo.Cgd.KProcessoDAO;
import ode.nucleo.cih.NucleoTab;
import ode.nucleo.crud.cih.FormularioDadosCRUD;
import ode.nucleo.crud.cih.GridDados;
import ode.processoPadrao.Cdp.CompPP;
import ode.processoPadrao.Cdp.InterfaceCompPP;
import ode.processoPadrao.Cgd.InterfaceCompPPDAO;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class FormDadosDefinirProcessoPadrao extends FormularioDadosCRUD<CompPP>{
	

	private static final long serialVersionUID = -67009077723605619L;
	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private Textbox tbObjetivo = new Textbox();
	Listbox lbInterfaceCompPPCompl = new Listbox();
	private Checkbox cbGranularidade = new Checkbox();
	private Checkbox cbGranularidade2 = new Checkbox();
	private Checkbox cbGranularidade3 = new Checkbox();
	private Combobox coProcSimples = new Combobox();
	
	

	public static KProcesso buscaKProcessoPorNome (String nome){
				
		KProcessoDAO kProcesso = (KProcessoDAO) SpringUtil.getBean("kProcessoDao");
		Collection<KProcesso> listakProcesso = kProcesso.recuperarTodos();
		for (KProcesso K : listakProcesso) {
			if (K.getNome().equals(nome)){
				return K;
			}
		}
		return null;
	}
	
	public static KAtividade buscaKAtividadePorNome (String nome){
		
		KAtividadeDAO kAtividade = (KAtividadeDAO) SpringUtil.getBean("kAtividadeDao");
		Collection<KAtividade> listakAtividade = kAtividade.recuperarTodos();
		for (KAtividade K : listakAtividade) {
			if (K.getNome().equals(nome)){
				return K;
			}
		}
		return null;
	}
	
	
	@Override
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// ////////////////////////////
		// Dados Cadastro
		// ////////////////////////////
		NucleoTab tabDadosCadastro = new NucleoTab();

		//Recupera Categorias de Processo e adiciona ao Combobox
		
					KProcessoDAO kprocesso = (KProcessoDAO) SpringUtil
						.getBean("kProcessoDao");
				Collection<KProcesso> listaProcesso = kprocesso.recuperarTodos();
				for (KProcesso K : listaProcesso) {
					coProcSimples.appendItem(K.getNome());
				}
				coProcSimples.setAutocomplete(true);
				
		if (cbGranularidade2.isChecked()==true || cbGranularidade.isChecked()==true){
			cbGranularidade3.setChecked(false);
		}
 				
		
		// Atribui o nome à tab
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_COMPPP));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("350px");
		tbNome.setMaxlength(50);		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);

		tbDescricao.setWidth("350px");
		tbDescricao.setMaxlength(300);
		tbDescricao.setHeight("145px");
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);		

		tbObjetivo.setWidth("350px");
		tbObjetivo.setMaxlength(300);
		tbObjetivo.setHeight("145px");
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_OBJETIVO),tbObjetivo);
		
		cbGranularidade.setLabel("Processo Complexo");
		gridDadosCadastro.adicionarLinha("",cbGranularidade2);
		cbGranularidade2.setLabel("Processo Simples");
		gridDadosCadastro.adicionarLinha("",cbGranularidade3);
		cbGranularidade3.setLabel("Macroatividade");
		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_GRANULARIDADE), cbGranularidade);		

		
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO), coProcSimples);
		coProcSimples.setWidth("300px");


		// adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);// primeira aba

		// recuperando TiposdeArtefato e adiciona no listbox
		InterfaceCompPPDAO interfaceCompPPDAO = (InterfaceCompPPDAO) SpringUtil
				.getBean("interfaceCompPPDao");
		Collection<InterfaceCompPP> listaCompPP = interfaceCompPPDAO
				.recuperarTodos();
		System.out.println("   =>Erro");
		lbInterfaceCompPPCompl.setModel(new ListModelList(listaCompPP));
		lbInterfaceCompPPCompl.renderAll();
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(CompPP objeto) {		
		
		/*// Tipo de Artefato
		if (lbCompPP.getSelectedItem() != null) {
			CompPP.setTipo((CompPP) lbCompPP.getSelectedItem()
					.getValue());
		}*/
		
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());
		//interfaceCompPP.setObjetivo(tbObjetivo.getValue());
		
	}
	
	

	@Override
	protected void preencherDadosTela(CompPP objeto) throws NucleoRegraNegocioExcecao {
		
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());
		//tbObjetivo.setValue(objeto.getObjetivo());
		//coProcSimples.setValue(objeto.getCategoria().getNome());
		
		if ((lbInterfaceCompPPCompl.getItems() != null)) {
			for (int indexlistbox = 0; indexlistbox < lbInterfaceCompPPCompl.getItems()
					.size(); indexlistbox++) {
				Long idLbInterfaceCompPP = ((CompPP) lbInterfaceCompPPCompl
						.getItemAtIndex(indexlistbox).getValue()).getId();
			//	Long idTipoKArtefato = kartefato.getTipo().getId();

				/*if (idLbTipoKArtefato.compareTo(idTipoKArtefato) == 0) {
					lbTipoArtefato.setSelectedIndex(indexlistbox);
				}*/
			}
		}
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
//	tbObjetivo.setConstraint("no empty");
				
	}
	

}
