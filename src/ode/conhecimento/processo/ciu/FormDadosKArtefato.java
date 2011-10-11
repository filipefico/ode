package ode.conhecimento.processo.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode.conhecimento.processo.cdp.KArtefato;
import ode.conhecimento.processo.cdp.TipoKArtefato;
import ode.conhecimento.processo.cgd.KArtefatoDAO;
import ode.conhecimento.processo.cgd.TipoKArtefatoDAO;

import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

public class FormDadosKArtefato extends FormularioDadosCRUD<KArtefato> {

	private static final long serialVersionUID = 5858657763453209871L;

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	Listbox lbTipoArtefato = new Listbox();
	Listbox lbSubArtefato = new Listbox();
	Listbox lbDependencias = new Listbox();

	Comboitem cbitemTipoArtefato;

	@Override
	protected List<NucleoTab> definirTabs() {

		// Cria a nova lista
		List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

		// Dados Cadastro
		NucleoTab tabDadosCadastro = new NucleoTab();

		// Atribui o nome � tab
		tabDadosCadastro.setNomeTab(NucleoMensagens
				.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

		// Atribui o conte�do � tab
		GridDados gridDadosCadastro = new GridDados();
		tbNome.setWidth("285px");
		tbNome.setMaxlength(50);
		gridDadosCadastro
				.adicionarLinha(
						NucleoMensagens.getMensagem(NucleoMensagens.TERMO_NOME),
						tbNome);

		tbDescricao.setWidth("285px");
		tbDescricao.setMaxlength(500);
		tbDescricao.setHeight("145px");
		tbDescricao.setMultiline(true);
		
		gridDadosCadastro.adicionarLinha(
				NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DESCRICAO),
				tbDescricao);

		// adiciono o grid de dados na tab
		tabDadosCadastro.setConteudoTab(gridDadosCadastro);
		listaTabs.add(tabDadosCadastro);// primeira aba

		// recuperando TiposdeArtefato e adiciona no listbox
		TipoKArtefatoDAO tipoArtefatoDAO = (TipoKArtefatoDAO) SpringUtil
				.getApplicationContext().getBean(TipoKArtefatoDAO.class);
		Collection<TipoKArtefato> listaTipoArtefatos = tipoArtefatoDAO
				.recuperarTodos();
		lbTipoArtefato.setModel(new ListModelList(listaTipoArtefatos));
		lbTipoArtefato.renderAll();

		// ---
		// adicionando nova aba SubArtefatos
		NucleoTab tabTipoArtefato = new NucleoTab();
		tabTipoArtefato.setNomeTab("Tipo Artefato");
		lbTipoArtefato.setCheckmark(true);
		lbTipoArtefato.setMultiple(false);
		tabTipoArtefato.setConteudoTab(lbTipoArtefato);
		listaTabs.add(tabTipoArtefato);

		// ---
		// adicionando nova aba SubArtefatos
		NucleoTab tabSubArtefatos = new NucleoTab();
		tabSubArtefatos.setNomeTab("SubArtefatos");
		lbSubArtefato.setCheckmark(true);
		lbSubArtefato.setMultiple(true);
		tabSubArtefatos.setConteudoTab(lbSubArtefato);
		listaTabs.add(tabSubArtefatos);

		// ---
		// adicionando nova aba Depend�nciass
		NucleoTab tabDependencias = new NucleoTab();
		tabDependencias.setNomeTab("Dependencias");
		lbDependencias.setCheckmark(true);
		lbDependencias.setMultiple(true);
		tabDependencias.setConteudoTab(lbDependencias);
		listaTabs.add(tabDependencias);

		// ---
		// recuperando dados e preenche a lista de subArtefatos e Dependencias
		KArtefatoDAO artefatoDAO = (KArtefatoDAO) SpringUtil
				.getApplicationContext().getBean(KArtefatoDAO.class);
		Collection<KArtefato> listaKArtefatoDS = artefatoDAO.recuperarTodos();

		// insere nos ListBox's
		lbSubArtefato.setModel(new ListModelList(listaKArtefatoDS));
		lbSubArtefato.renderAll();

		lbDependencias.setModel(new ListModelList(listaKArtefatoDS));
		lbDependencias.renderAll();

		return listaTabs;
	}

	@Override
	protected void preencherDadosObjeto(KArtefato kartefato) {
		kartefato.setNome(tbNome.getValue());
		kartefato.setDescricao(tbDescricao.getValue());

		// Tipo de Artefato
		if (lbTipoArtefato.getSelectedItem() != null) {
			kartefato.setTipo((TipoKArtefato) lbTipoArtefato.getSelectedItem()
					.getValue());
		}

		// SubArtefato
		HashSet<KArtefato> setSubArtefato = new HashSet<KArtefato>();
		if (lbSubArtefato.getSelectedItems() != null) {
			for (Iterator<Listitem> iterSubArtefato = lbSubArtefato
					.getSelectedItems().iterator(); iterSubArtefato.hasNext();/**/) {
				setSubArtefato.add((KArtefato) iterSubArtefato.next()
						.getValue());
			}
		}
		kartefato.setSubArtefatos(setSubArtefato);

		// dependencias
		HashSet<KArtefato> setDependencias = new HashSet<KArtefato>();
		if (lbDependencias.getSelectedItems() != null) {
			for (Iterator<Listitem> iterDependencia = lbDependencias
					.getSelectedItems().iterator(); iterDependencia.hasNext();/**/) {
				setDependencias.add((KArtefato) iterDependencia.next()
						.getValue());
			}
		}
		kartefato.setDependencias(setDependencias);

	}

	@Override
	protected void preencherDadosTela(KArtefato kartefato)
			throws NucleoRegraNegocioExcecao {
		tbNome.setValue(kartefato.getNome());
		tbDescricao.setValue(kartefato.getDescricao());

		// Tipo Artefato
		if ((kartefato.getTipo() != null) && (lbSubArtefato.getItems() != null)) {
			for (int indexlistbox = 0; indexlistbox < lbTipoArtefato.getItems()
					.size(); indexlistbox++) {
				Long idLbTipoKArtefato = ((TipoKArtefato) lbTipoArtefato
						.getItemAtIndex(indexlistbox).getValue()).getId();
				Long idTipoKArtefato = kartefato.getTipo().getId();

				if (idLbTipoKArtefato.compareTo(idTipoKArtefato) == 0) {
					lbTipoArtefato.setSelectedIndex(indexlistbox);
				}
			}
		}

		Object ob = kartefato;
		Object ob1 = kartefato.getSubArtefatos();
		// SubArtefatos
		if (lbSubArtefato.getItems() != null) {
			for (KArtefato subArtefato : kartefato.getSubArtefatos()) {
				for (int indexlistbox = 0; indexlistbox < lbSubArtefato
						.getItems().size(); indexlistbox++) {
					Long idlbSubArt = ((KArtefato) lbSubArtefato
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idsubArtefato = subArtefato.getId();
					if (idlbSubArt.compareTo(idsubArtefato) == 0) {
						lbSubArtefato.addItemToSelection(lbSubArtefato
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}

		// Dependencias
		if (lbDependencias.getItems() != null) {
			for (KArtefato subDependecia : kartefato.getDependencias()) {
				for (int indexlistbox = 0; indexlistbox < lbDependencias
						.getItems().size(); indexlistbox++) {
					Long idlbDependencia = ((KArtefato) lbDependencias
							.getItemAtIndex(indexlistbox).getValue()).getId();
					Long idDependencia = subDependecia.getId();
					if (idlbDependencia.compareTo(idDependencia) == 0) {
						lbDependencias.addItemToSelection(lbDependencias
								.getItemAtIndex(indexlistbox));
						break;
					}
				}
			}
		}

	}
}
