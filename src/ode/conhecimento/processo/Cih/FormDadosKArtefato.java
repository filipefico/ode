package ode.conhecimento.processo.Cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nucleo.comuns.crud.visao.FormularioDadosCRUD;
import nucleo.comuns.crud.visao.GridDados;
import nucleo.comuns.excecao.NucleoRegraNegocioExcecao;
import nucleo.comuns.util.NucleoMensagens;
import nucleo.comuns.visao.componentes.NucleoTab;
import ode.conhecimento.processo.Cdp.KArtefato;
import ode.conhecimento.processo.Cdp.TipoKArtefato;
import ode.conhecimento.processo.Cgd.KArtefatoDAO;
import ode.conhecimento.processo.Cgd.TipoKArtefatoDAO;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class FormDadosKArtefato extends FormularioDadosCRUD<KArtefato> {

	private static final long serialVersionUID = 5858657763453209871L;

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	Combobox cbTipoArtefato = new Combobox();
	Listbox lbSubArtefato = new Listbox();
	Listbox lbDependencias = new Listbox();

	@Override
	protected List<NucleoTab> definirTabs() {
		
		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// Dados Cadastro
		NucleoTab tabDadosCadastro = new NucleoTab();

		// Atribui o nome à tab
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conteúdo à tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("150px");
		tbNome.setMaxlength(50);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_NOME), tbNome);

		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_TIPO_K_ARTEFATO),
				cbTipoArtefato);

		tbDescricao.setWidth("150px");
		tbDescricao.setMaxlength(10);
		gridDadosCadastro.adicionarLinha(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DESCRICAO), tbDescricao);

		// adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);// primeira aba

		// adicionando nova aba SubArtefatos
		NucleoTab tabSubArtefatos = new NucleoTab();
		tabSubArtefatos.setNomeTab("SubArtefatos");
		lbSubArtefato.setCheckmark(true);
		lbSubArtefato.setMultiple(true);
		// lbSubArtefato.
		tabSubArtefatos.setConteudoTab(lbSubArtefato);
		listaTabs.add(tabSubArtefatos);

		// adicionando nova aba Dependências
		// aqui esta faltando um pedaço de cógigo que faça o mesmo que
		// ListagemPaginada L:296 faça.
		NucleoTab tabDependencias = new NucleoTab();
		tabDependencias.setNomeTab("Dependencias");
		lbDependencias.setCheckmark(true);
		lbDependencias.setMultiple(true);
		tabDependencias.setConteudoTab(lbDependencias);
		listaTabs.add(tabDependencias);

		
		// recuperando TiposdeArtefato e adiciona no combobox
		TipoKArtefatoDAO tipoArtefatoDAO = (TipoKArtefatoDAO) SpringUtil
				.getBean("tipoKArtefatoDao");
		Collection<TipoKArtefato> listaTipoArtefatos = tipoArtefatoDAO.recuperarTodos();
		for (TipoKArtefato K : listaTipoArtefatos) {
			cbTipoArtefato.appendItem(K.getNome());
		}
		
		// recuperando dados e preenche a lista de subArtefatos e Dependencias
		KArtefatoDAO artefatoDAO = (KArtefatoDAO) SpringUtil
				.getBean("kArtefatoDao");
		Collection<KArtefato> listaDependencias = artefatoDAO.recuperarTodos();
		for (KArtefato K : listaDependencias) {
			lbSubArtefato.appendItem(K.getNome(),"=D");
			lbDependencias.appendItem(K.getNome(), "value");
		}
		
		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(KArtefato objeto) {
		objeto.setNome(tbNome.getValue());
		objeto.setDescricao(tbDescricao.getValue());

	}

	@Override
	protected void preencherDadosTela(KArtefato objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(objeto.getNome());
		tbDescricao.setValue(objeto.getDescricao());

	}

}
