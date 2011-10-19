package ode.conhecimentoMedicao.cih;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;

import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimentoMedicao.cci.CtrlKElementoMensuravelCRUD;
import ode.conhecimentoMedicao.cci.CtrlKEscalaCRUD;
import ode.conhecimentoMedicao.cci.CtrlKNecessidadeInformacaoCRUD;
import ode.conhecimentoMedicao.cci.CtrlKValorEscalaCRUD;
import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KObjetivoMedicao;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cdp.KValorEscala;
import ode.conhecimentoMedicao.cdp.TipoEscala;
import ode.conhecimentoMedicao.cdp.TipoObjetivoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKObjetivoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKValorEscala;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

public class FormDadosKEscala extends FormularioDadosCRUD<KEscala>{

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<KValorEscala> ckVal = new NucleoMultipleListBox<KValorEscala>();
	private NucleoListbox<TipoEscala> tipo = new NucleoListbox<TipoEscala>();
	private GridDados gridDadosCadastro = new GridDados();
	private CtrlKValorEscalaCRUD ctrlV;
	private EventListener onclick = new EventListener() {
		
		@Override
		public void onEvent(Event arg0) throws Exception {
			ctrlV = new CtrlKValorEscalaCRUD();
			ctrlV = (CtrlKValorEscalaCRUD) SpringUtil.getApplicationContext().getBean(ctrlV.getClass());
			ctrlV.iniciar();
		}
	};
	
	
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// ////////////////////////////
		// Dados Cadastro - Nome e Descricao
		// ////////////////////////////
		NucleoTab tabDadosCadastro = new NucleoTab();

		// Atribui o nome à tab
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		tbNome.setWidth("285px");
		tbNome.setMaxlength(100);		
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
		
		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);	
		
		
		
		tipo.setObjetos(Arrays.asList(TipoEscala.values()));
		tipo.setCheckmark(true);
		
		
		gridDadosCadastro.adicionarLinhaObrigatoria("Tipo", tipo);
		
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		
		// ////////////////////////////
		// Dados Cadastro - Valores de Escala
		// ////////////////////////////
		NucleoTab tabDadosValores = new NucleoTab();
		
		Vbox box = new Vbox();
		
		tabDadosValores.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_VALORES_DE_ESCALA));
		
		
		CtrlKEscalaCRUD ctrl = (CtrlKEscalaCRUD) this.getControlador();
		AplCadastrarKValorEscala apl =  ctrl.getAplCadastrarKValorEscala();
		Collection<KValorEscala> tipos = apl.recuperarTodos();
		
		Button botaoNovoValorEscala = new Button();
		botaoNovoValorEscala.setLabel("Criar Valor de Escala");
		botaoNovoValorEscala.addEventListener("onClick", onclick);
		
		ckVal.setObjetos(tipos);
		
		box.appendChild(ckVal);
		box.appendChild(botaoNovoValorEscala);
		
		tabDadosValores.setConteudoTab(box);
		////////////////
		
		listaTabs.add(tabDadosCadastro);
		listaTabs.add(tabDadosValores);

		return listaTabs;
	}

	@Override
	protected void preencherDadosTela(KEscala objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		ckVal.setObjetosSelecionados(objeto.getValores());
		tipo.setObjetoSelecionado(objeto.getTipo());
	}

	@Override
	protected void preencherDadosObjeto(KEscala objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setValores(ckVal.getObjetosSelecionados());
		objeto.setTipo(tipo.getObjetoSelecionado());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}

}
