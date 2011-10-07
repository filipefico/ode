package ode._controleFerramenta.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._controleFerramenta.cdp.EscopoFerramentaSoftware;
import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleFerramenta.cdp.OrigemFerramentaSoftware;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode._infraestruturaCRUD.ciu.NucleoRadiogroup;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;

import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Textbox;

public class FormDadosFerramentaSoftware extends FormularioDadosCRUD<FerramentaSoftware> {

	private static final long serialVersionUID = 1L;

	private Textbox tbNome = new Textbox();
	private Checkbox cbAtivo = new Checkbox();
	private NucleoListbox<KFerramentaSoftware> listKRecurso = new NucleoListbox<KFerramentaSoftware>();
	private NucleoRadiogroup<EscopoFerramentaSoftware> listEscopo = new NucleoRadiogroup<EscopoFerramentaSoftware>();
	private NucleoRadiogroup<OrigemFerramentaSoftware> listOrigem = new NucleoRadiogroup<OrigemFerramentaSoftware>();

	@Override
	protected List<NucleoTab> definirTabs() {
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();
		listaTabs.add(definirTabDadosCadastro());
		return listaTabs;
	}

	private NucleoTab definirTabDadosCadastro() {
		NucleoTab tabDadosCadastro = new NucleoTab();
		tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("240px");
		tbNome.setMaxlength(80);
		gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME), tbNome);

		cbAtivo.setChecked(true);
		gridDadosCadastro.adicionarLinha("Ativo", cbAtivo);

		List<KFerramentaSoftware> listaKFS = new ArrayList<KFerramentaSoftware>(((CtrlFerramentaSoftwareCRUD)this.getControlador()).listarKFerramentasSoftware());
		listKRecurso.setWidth("240px");
		listKRecurso.setRows(1);
		listKRecurso.setMold("select");
		listKRecurso.setObjetos(listaKFS);
		gridDadosCadastro.adicionarLinha("Tipo", listKRecurso);
		
		listEscopo.setWidth("240px");
		listEscopo.setObjetos(EscopoFerramentaSoftware.values());
		gridDadosCadastro.adicionarLinha("Escopo", listEscopo);
		
		listOrigem.setWidth("240px");
		listOrigem.setObjetos(OrigemFerramentaSoftware.values());
		gridDadosCadastro.adicionarLinha("Origem", listOrigem);
		
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		return tabDadosCadastro;
	}

	@Override
	protected void preencherDadosObjeto(FerramentaSoftware objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setAtivo(cbAtivo.isChecked());
		objeto.setKRecurso(listKRecurso.getObjetoSelecionado());
		objeto.setEscopo(listEscopo.getObjetoSelecionado());
		objeto.setOrigem(listOrigem.getObjetoSelecionado());
	}

	@Override
	protected void preencherDadosTela(FerramentaSoftware objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		cbAtivo.setChecked(objeto.isAtivo());
		listKRecurso.setObjetoSelecionado(objeto.getKFerramentaSoftware());
		listOrigem.setObjetoSelecionado(objeto.getOrigem());
		listEscopo.setObjetoSelecionado(objeto.getEscopo());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty: Favor informar o Nome!");
	}
	
	@Override
	protected boolean isValido() {
		if (listKRecurso.getObjetoSelecionado() == null)
			disparaErro(listKRecurso, "É necessário informar o Tipo!");
		if (listEscopo.getObjetoSelecionado() == null)
			disparaErro(listEscopo, "É necessário informar o Escopo!");
		if (listOrigem.getObjetoSelecionado() == null)
			disparaErro(listOrigem, "É necessário informar a Origem!");
		return super.isValido();
	}

}