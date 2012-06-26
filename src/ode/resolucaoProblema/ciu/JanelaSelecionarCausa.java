package ode.resolucaoProblema.ciu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import ode.problema.cdp.KSolucao;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

import org.zkoss.zul.Vlayout;







public class JanelaSelecionarCausa extends JanelaSimples {


	private static final long serialVersionUID = 5757977094877991531L;

	

	private CtrlSelecionarCausa ctrl;
	private Collection<KCausa> listakcausa;
	public Collection<KSolucao> listasolucao;
	protected Collection<KSolucao> listasolucaoescolhidos;
	private Tree arvore;
	private OcorrenciaProblema kocorrenciaproblema = new OcorrenciaProblema();
	private KProblema kproblema = new KProblema();
	String nome = new String(); 
	private Button buttonAvaliarSolucoes = new Button();
	Vlayout painel = new Vlayout();
	Set<KSolucao> listasolucao2 = new HashSet<KSolucao>();
	protected NucleoListbox<KSolucao> listbox = new NucleoListbox<KSolucao>();
	Set<Listitem> listasolucao3 = new HashSet<Listitem>();
	private Button buttonSalvar = new Button();
	private Button buttonSelecionarCausa = new Button();
	private Button buttonSelecionarSolucao = new Button();

	public JanelaSelecionarCausa() {
		super();
		
	}
	


	public void mostrar(OcorrenciaProblema op) {
		this.setTitle("Resolver Problema - Causas Listadas");
		this.setHflex("min");
		painel.setParent(this);
		painel.setWidth("500px");
		painel.setHeight("500px");
		Label l1 = new Label("Causas");
        l1.setParent(painel);
		
		System.out.println(op.getNome());
		painel.setSclass("z-valign-top");
		ctrl = SpringUtil.getApplicationContext().getBean(CtrlSelecionarCausa.class);
		kproblema = op.getKproblema();
		listakcausa = ctrl.kcausaDAO.recuperarCausaPorProblema(kproblema);


		if (listakcausa.size() > 0) {
			arvore = new Tree();
			arvore.setParent(painel);
			arvore.setWidth("498px");
			arvore.setHeight("250px");
		
			
			 

			
			
			
			arvore.setCheckmark(true);
			arvore.setMultiple(true);
			arvore.setZclass("z-dottree");
			arvore.addEventListener("onSelect", new ArvoreSelectEventListener());
			buttonSelecionarCausa.setLabel("Selecionar Causas em Ocorrências Anteriores");
			buttonSelecionarCausa.setParent(painel);
		
			
			this.preencherArvore();
		} else {
			Label label = new Label(
					"Não existe Causa.");
			label.setParent(painel);
		}
		 Label l = new Label("Soluções Possíveis");
         l.setParent(painel);
		buttonAvaliarSolucoes.setLabel("Avaliar Soluções usando Critérios");
		buttonAvaliarSolucoes.setParent(this);
		buttonAvaliarSolucoes.addEventListener("onClick", new AvaliarSolucoesEventListener());
		
		buttonSalvar.setLabel("Salvar");
		buttonSalvar.setParent(this);
		buttonSalvar.addEventListener("onClick", new AvaliarSolucoesEventListenerSalvar());
		
		
		
		buttonSelecionarSolucao.setLabel("Selecionar Soluções em Ocorrências Anteriores");
		buttonSelecionarSolucao.setParent(this);
		
		super.mostrar();
	}


	public void preencherArvore() {
		inserirNodesKCausa(listakcausa);

	}

	private void inserirNodesKCausa(
			Collection<KCausa> kcausa) {

		if (arvore.getTreechildren() != null)
			arvore.getTreechildren().setParent(null);
		for (KCausa kcausa2 : listakcausa) {

			if (arvore.getTreechildren() == null) {
				Treechildren tc = new Treechildren();
				tc.setParent(arvore);
			}

			Treeitem ti = new Treeitem(kcausa2.getNome(),
					kcausa2);

			ti.setParent(arvore.getTreechildren());

		}

	}


	public OcorrenciaProblema getKocorrenciaproblema() {
		return kocorrenciaproblema;
	}

	public void setKocorrenciaproblema(OcorrenciaProblema kocorrenciaproblema) {
		this.kocorrenciaproblema = kocorrenciaproblema;
	}

		

	public class ArvoreSelectEventListener implements EventListener {
		public void onEvent(Event e) throws Exception {
			SelectEvent event = (SelectEvent) e;
			Treeitem ti = (Treeitem) event.getReference();
			ctrl.setKCausa((KCausa) ti.getValue());
			KCausa kcausa = ctrl.getKCausa();
			
			listasolucao2.addAll(ctrl.ksolucaoDAO.recuperarSolucaoPorCausa(kcausa));
			listbox.setParent(painel);
			listbox.setHeader(nome);
			listbox.setObjetos(listasolucao2);
			listbox.setWidth("498px");
			listbox.setHeight("250px");
			listbox.setCheckmark(true);
			listbox.setMultiple(true);
			listbox.addEventListener("onSelect", new  SolucaoSelectEventListener());
			
		}	

	}

	
	public class SolucaoSelectEventListener implements EventListener {
		public void onEvent(Event e) throws Exception {
			
			listasolucao3 = listbox.getSelectedItems();

			System.out.println("TAMANHO: "+ listbox.getSelectedItems().size());
			System.out.println(listasolucao3.size());
			
			
			
		}	

	}

	
	public class AvaliarSolucoesEventListener implements EventListener {

		 public void onEvent(Event e) throws Exception {
			ctrl.AbrirJanelaAvaliarSolucoesComCriterio (listasolucao3);
			
		 }
	}
	public class AvaliarSolucoesEventListenerSalvar implements EventListener {

		 public void onEvent(Event e) throws Exception {
			 onClose();// fecha a janela
			
		 }
	}
	
	

	}
