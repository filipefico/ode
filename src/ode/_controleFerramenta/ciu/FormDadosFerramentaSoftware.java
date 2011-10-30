package ode._controleFerramenta.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._controleFerramenta.ciu.CtrlFerramentaSoftwareCRUD;
import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;

import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Textbox;

public class FormDadosFerramentaSoftware extends FormularioDadosCRUD<FerramentaSoftware> {

	private static final long serialVersionUID = 1L;

	private Textbox tbNome = new Textbox();
	private Checkbox cbAtivo = new Checkbox();
	private NucleoListbox<KFerramentaSoftware> listKRecurso = new NucleoListbox<KFerramentaSoftware>();
	private Checkbox cbInterna = new Checkbox();
	private Checkbox cbDisponivelApenasParaProjetos = new Checkbox();
	private Textbox tbCaminho = new Textbox();
	
	@Override
	public CtrlFerramentaSoftwareCRUD getControlador() {
		return (CtrlFerramentaSoftwareCRUD)super.getControlador();
	}

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

		List<KFerramentaSoftware> listaKFS = new ArrayList<KFerramentaSoftware>(getControlador().listarKFerramentasSoftware());
		listKRecurso.setWidth("240px");
		listKRecurso.setRows(1);
		listKRecurso.setMold("select");
		listKRecurso.setObjetos(listaKFS);
		listKRecurso.selecionarPrimeiroElemento();
		gridDadosCadastro.adicionarLinha("Tipo", listKRecurso);
		
		gridDadosCadastro.adicionarLinha("Interna", cbInterna);
		
		gridDadosCadastro.adicionarLinha("Disponível apenas para projetos abertos", cbDisponivelApenasParaProjetos);
		
		tbCaminho.setWidth("240px");
		tbCaminho.setMaxlength(255);
		gridDadosCadastro.adicionarLinhaObrigatoria("Caminho", tbCaminho);
		
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		return tabDadosCadastro;
	}

	@Override
	protected void preencherDadosObjeto(FerramentaSoftware objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setAtivo(cbAtivo.isChecked());
		objeto.setKFerramentaSoftware(listKRecurso.getObjetoSelecionado());
		objeto.setDisponivelApenasParaProjetos(cbDisponivelApenasParaProjetos.isChecked());
		objeto.setInterna(cbInterna.isChecked());
		objeto.setCaminho(tbCaminho.getValue());
	}

	@Override
	protected void preencherDadosTela(FerramentaSoftware objeto) throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		cbAtivo.setChecked(objeto.isAtivo());
		listKRecurso.setObjetoSelecionado(objeto.getKFerramentaSoftware());
		cbInterna.setChecked(objeto.isInterna());
		cbDisponivelApenasParaProjetos.setChecked(objeto.isDisponivelApenasParaProjetos());
		tbCaminho.setValue(objeto.getCaminho());
	}

	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty: Favor informar o Nome!");
		tbCaminho.setConstraint("no empty: Favor informar o Caminho!");
	}
	
	@Override
	protected boolean isValido() {
		if (listKRecurso.getObjetoSelecionado() == null)
			disparaErro(listKRecurso, "É necessário informar o Tipo!");
		return super.isValido();
	}

}