package ode.alocacaoRecurso.ciu;

import java.util.Collection;

import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleProcesso.cdp.Atividade;
import ode._controleProcesso.cdp.DefinicaoAtividade;
import ode._controleProcesso.cdp.DemandaRH;
import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.alocacaoRecurso.cdp.AlocacaoFerramentaSoftware;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.CancelamentoAlocacaoRH;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KRecurso;
import ode.conhecimento.processo.cdp.KRecursoHumano;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vlayout;

public class PainelAlocarRecursos extends Hlayout {
	
	private static final long serialVersionUID = 1L;
	
	private CtrlAlocacaoRecurso ctrl;
	
	private Tree arvore;
	
	private Vlayout boxDireita;
	
	private Collection<ProcessoProjetoEspecifico> listaProc;
	
	public PainelAlocarRecursos(final CtrlAlocacaoRecurso ctrl) {
		this.ctrl = ctrl;
		
		this.setSclass("z-valign-top");
		
		listaProc = ctrl.processoProjetoEspecificoDAO.recuperarPorProjeto(ctrl.getProjeto().getId());
		
		if(listaProc.size()>0) {
			arvore = new Tree();
			arvore.setParent(this);
			arvore.setWidth("275px");
			arvore.setHeight("300px");
			arvore.setZclass("z-dottree");
			arvore.addEventListener("onSelect", new ArvoreSelectEventListener());
			
			boxDireita = new Vlayout();
			boxDireita.setParent(this);
			boxDireita.setWidth("350px");
			
			ctrl.preencherPainelAlocarRecursos(this);
		}
		else {
			Label label = new Label("Este projeto não possui atividades cadastradas.");
			label.setParent(this);
		}
	}
	
	public void preencherArvore() {
		inserirNodesProjeto(listaProc);
	}
	
	private void inserirNodesProjeto(Collection<ProcessoProjetoEspecifico> listaProc) {
		//remove os filhos, caso existam
		if(arvore.getTreechildren()!=null)
			arvore.getTreechildren().setParent(null);
		for (ProcessoProjetoEspecifico processo : listaProc) {
			//no primeiro filho a ser adicionado, cria um container para os filhos 
			if(arvore.getTreechildren()==null) {
				Treechildren tc = new Treechildren();
				tc.setParent(arvore);
			}
			//cria um nó e adiciona
			Treeitem ti = new Treeitem(processo.getNome(),processo);
			ti.setParent(arvore.getTreechildren());
			
			//insere os subnós, funcionamento semelhante a esta função
			inserirNodesProcesso(ti, processo);
        }
		
	}

	private void inserirNodesProcesso(Treeitem tiProcesso, ProcessoProjetoEspecifico processo) {
		if(tiProcesso.getTreechildren()!=null)
			tiProcesso.getTreechildren().setParent(null);
		for (Atividade atividade : ctrl.atividadeDAO.recuperarPorProcessoProjetoEspecifico(processo.getId())) {
			if(tiProcesso.getTreechildren()==null) {
				Treechildren tc = new Treechildren();
				tc.setParent(tiProcesso);
			}
			Treeitem ti = new Treeitem(atividade.getNome(),atividade);
			ti.setImage("/imagens/atividade.jpeg");
			ti.setParent(tiProcesso.getTreechildren());
			
			inserirNodesAtividade(ti, atividade);
		}
	}
	
	private void inserirNodesAtividade(Treeitem tiAtividade, Atividade atividade) {
		if(tiAtividade.getTreechildren()!=null)
			tiAtividade.getTreechildren().setParent(null);
		DefinicaoAtividade d = ctrl.definicaoAtividadeDAO.recuperarPorAtividade(atividade.getId());
		if(d!=null) {
			for (Atividade subAtividade : d.getSubAtividades()) {
				if(tiAtividade.getTreechildren()==null) {
					Treechildren tc = new Treechildren();
					tc.setParent(tiAtividade);
				}
				Treeitem ti = new Treeitem(subAtividade.getNome(),subAtividade);
				ti.setImage("/imagens/atividade.jpeg");
				ti.setParent(tiAtividade.getTreechildren());
	        	inserirNodesAtividade(ti, subAtividade);
	        }
			for (DemandaRH demandaRH : ctrl.demandaRHDAO.recuperarPorAtividade(atividade.getId())) {
				if(tiAtividade.getTreechildren()==null) {
					Treechildren tc = new Treechildren();
					tc.setParent(tiAtividade);
				}
	        	Treeitem ti = new Treeitem(demandaRH.getkRecursoHumano().getNome(),demandaRH);
	        	ti.setImage("/imagens/recursoHumano.png");
				ti.setParent(tiAtividade.getTreechildren());
	        	inserirNodesDemandaRH(ti, demandaRH); 
	        }
			for (KRecurso kRecurso : d.getkRecursos()) {
				if(tiAtividade.getTreechildren()==null) {
					Treechildren tc = new Treechildren();
					tc.setParent(tiAtividade);
				}
	        	Treeitem ti = new Treeitem(kRecurso.getNome(),kRecurso);
	        	if(kRecurso instanceof KFerramentaSoftware)
	        		ti.setImage("/imagens/ferramentaSoftware.gif");
				ti.setParent(tiAtividade.getTreechildren());
	        	inserirNodesKRecurso(ti, atividade, kRecurso); 
	        }
		}
	}

	private void inserirNodesKRecurso(Treeitem tiKRecurso, Atividade atividade, KRecurso kRecurso) {
		if(tiKRecurso.getTreechildren()!=null)
			tiKRecurso.getTreechildren().setParent(null);
		if(kRecurso instanceof KFerramentaSoftware) {
			for (AlocacaoFerramentaSoftware alocacao : ctrl.listarAlocacoesFerramentaSoftwarePorAtividadeKFerramentaSoftware(atividade, (KFerramentaSoftware) kRecurso)) {
				if(tiKRecurso.getTreechildren()==null) {
					Treechildren tc = new Treechildren();
					tc.setParent(tiKRecurso);
				}
				Treeitem ti = new Treeitem(alocacao.getFerramentaSoftware().getNome(),alocacao);
				ti.setParent(tiKRecurso.getTreechildren());
			}
		}
	}

	private void inserirNodesDemandaRH(Treeitem tiDemandaRH, DemandaRH demandaRH) {
		if(tiDemandaRH.getTreechildren()!=null)
			tiDemandaRH.getTreechildren().setParent(null);
		for (AlocacaoRH alocacao : ctrl.listarAlocacoesRHPorDemandaRH(demandaRH)) {
			if(tiDemandaRH.getTreechildren()==null) {
				Treechildren tc = new Treechildren();
				tc.setParent(tiDemandaRH);
			}
			Treeitem ti = new Treeitem(alocacao.getRecursoHumano().getNome(),alocacao);
			ti.setParent(tiDemandaRH.getTreechildren());
		}
	}

	private class ArvoreSelectEventListener implements EventListener {
		public void onEvent(Event e) throws Exception {
			boxDireita.getChildren().clear();
			
			SelectEvent event = (SelectEvent) e;
			Treeitem ti = (Treeitem) event.getReference();
			
			if(ti.getValue() instanceof DemandaRH) {
				preencherBoxDemandaRH(ti);
			}
			if(ti.getValue() instanceof KFerramentaSoftware) {
				Atividade atividade = (Atividade) ((Treeitem)ti.getParent().getParent()).getValue();
				preencherBoxKFerramentaSoftware(ti, atividade);
			}
			else if(ti.getValue() instanceof AlocacaoRH) {
				ctrl.setAlocacaoSelecionada((AlocacaoRH) ti.getValue());
				preencherBoxAlocacaoRH();
			}
		}	
	}
	
	private void preencherBoxKFerramentaSoftware(final Treeitem ti, final Atividade atividade) {
		boxDireita.getChildren().clear();
		
		GridDados grid = new GridDados();
		grid.setParent(boxDireita);
		
		final KFerramentaSoftware kFerramentaSoftware = (KFerramentaSoftware) ti.getValue();
		
		grid.adicionarLinha("Atividade", atividade.getNome());
		
		Collection<FerramentaSoftware> listaFS = ctrl.ferramentaSoftwareDAO.recuperarPorKFerramentaSoftware(kFerramentaSoftware.getId());
		
		if (listaFS.size() > 0) {

			final NucleoListbox<FerramentaSoftware> listbox = new NucleoListbox<FerramentaSoftware>();
			listbox.setParent(boxDireita);

			listbox.setHeader("Alocar Ferramentas de Software");
			listbox.setObjetos(listaFS);
			listbox.setVflex("min");
			listbox.setRows(5);
			listbox.setCheckmark(true);
			listbox.setMultiple(true);
			
			for(AlocacaoFerramentaSoftware a : ctrl.listarAlocacoesFerramentaSoftwarePorAtividadeKFerramentaSoftware(atividade, kFerramentaSoftware)) {
				listbox.getItem(a.getFerramentaSoftware()).setSelected(true);
			}

			Button botao = new Button("Alocar");
			botao.addEventListener("onClick", new EventListener() {
				public void onEvent(Event arg0) throws Exception {
					ctrl.alocarFerramentasSoftware(atividade, listbox.getObjetosSelecionados(), listbox.getObjetosNaoSelecionados());
					inserirNodesKRecurso(ti, atividade, kFerramentaSoftware);
				}
				
			});
			botao.setParent(boxDireita);
		}
	}
	
	private void preencherBoxDemandaRH(final Treeitem ti) {
		boxDireita.getChildren().clear();
		
		GridDados grid = new GridDados();
		grid.setParent(boxDireita);
		
		final DemandaRH demandaRH = (DemandaRH) ti.getValue();
		final Atividade atividade = demandaRH.getDefinicaoAtividade().getAtividade(); 
		
		grid.adicionarLinha("Atividade", atividade.getNome());
		
		KRecursoHumano krh = demandaRH.getkRecursoHumano();
		Collection<RecursoHumano> listaRH = ctrl.listarParticipacoesRecursosHumanosPorPapel(krh);
		
		if (listaRH.size() > 0) {

			final NucleoListbox<RecursoHumano> listbox = new NucleoListbox<RecursoHumano>();
			listbox.setParent(boxDireita);

			listbox.setHeader("Alocar Recursos Humanos");
			listbox.setObjetos(listaRH);
			listbox.setVflex("min");
			listbox.setRows(5);
			listbox.setCheckmark(true);
			listbox.setMultiple(true);
			
			for(AlocacaoRH aa : ctrl.listarAlocacoesRHPorDemandaRH(demandaRH)) {
				listbox.getItem(aa.getRecursoHumano()).setSelected(true);
			}

			Button botao = new Button("Alocar");
			botao.addEventListener("onClick", new EventListener() {
				public void onEvent(Event arg0) throws Exception {
					ctrl.alocarRecursosHumanos(demandaRH, listbox.getObjetosSelecionados(), listbox.getObjetosNaoSelecionados());
					inserirNodesDemandaRH(ti, demandaRH);
				}
				
			});
			botao.setParent(boxDireita);
		}
	}

	public void preencherBoxAlocacaoRH() {
		boxDireita.getChildren().clear();
		
		final AlocacaoRH alocacaoRH = ctrl.getAlocacaoSelecionada();
		
		GridDados gridAlocacao = new GridDados();
		gridAlocacao.setParent(boxDireita);
		
		gridAlocacao.adicionarLinha("Estado", alocacaoRH.getEstado().toString());
		
		final Intbox txtDedicacao = new Intbox();
		txtDedicacao.setValue(alocacaoRH.getDedicacao());
		gridAlocacao.adicionarLinha("Dedicação Diária", txtDedicacao);
		
		final Datebox txtDtInicioPrevisto = new Datebox();
		txtDtInicioPrevisto.setValue(alocacaoRH.getDtInicioPrevisto());
		gridAlocacao.adicionarLinha("Data de Início Previsto", txtDtInicioPrevisto);
		
		final Datebox txtDtFimPrevisto = new Datebox();
		txtDtFimPrevisto.setValue(alocacaoRH.getDtFimPrevisto());
		gridAlocacao.adicionarLinha("Data de Fim Previsto", txtDtFimPrevisto);
		
		Datebox txtDtInicioEfetivo = new Datebox();
		txtDtInicioEfetivo.setValue(alocacaoRH.getDtInicioEfetivo());
		txtDtInicioEfetivo.setDisabled(true);
		gridAlocacao.adicionarLinha("Data de Início Efetivo", txtDtInicioEfetivo);
		
		Datebox txtDtFimEfetivo = new Datebox();
		txtDtFimEfetivo.setValue(alocacaoRH.getDtFimEfetivo());
		txtDtFimEfetivo.setDisabled(true);
		gridAlocacao.adicionarLinha("Data de Fim Efetivo", txtDtFimEfetivo);
		
		Hbox hboxBotoes = new Hbox();
		hboxBotoes.setParent(boxDireita);
		
		Button botaoDefinir = new Button("Definir");
		botaoDefinir.addEventListener("onClick", new EventListener() {
			public void onEvent(Event event) throws Exception {
				ctrl.editarAlocacao(txtDedicacao.getValue(), txtDtInicioPrevisto.getValue(), txtDtFimPrevisto.getValue());
			}
		});
		botaoDefinir.setParent(hboxBotoes);
		
		Button botaoAvaliarParticipacao = new Button("Avaliar Participação");
		botaoAvaliarParticipacao.setParent(hboxBotoes);
		
		botaoAvaliarParticipacao.setDisabled(!alocacaoRH.getEstado().equals(EstadoAlocacaoRH.EmAvaliacaoEncerramento));
		botaoAvaliarParticipacao.addEventListener("onClick", new EventListener() {
			public void onEvent(Event arg0) throws Exception {
				JanAvaliarAlocacaoRH janAvaliarAlocacaoRH = new JanAvaliarAlocacaoRH(ctrl);
				janAvaliarAlocacaoRH.mostrar();
			}
		});
		
		final CancelamentoAlocacaoRH cancelamentoAlocacao = ctrl.cancelamentoAlocacaoRHDAO.recuperarPorAlocacaoRH(alocacaoRH.getId()); 
		
		if(cancelamentoAlocacao == null) {
			Button botaoCancelarAlocacao = new Button("Cancelar Alocação");
			botaoCancelarAlocacao.addEventListener("onClick", new EventListener() {
				public void onEvent(Event event) throws Exception {
					JanCancelarAlocacaoRH janCancelarAlocacao = new JanCancelarAlocacaoRH(ctrl);
					janCancelarAlocacao.mostrar();
				}
				
			});
			botaoCancelarAlocacao.setParent(hboxBotoes);
		}
		else {
			//alocação está cancelada
			txtDedicacao.setDisabled(true);
			txtDtInicioPrevisto.setDisabled(true);
			txtDtFimPrevisto.setDisabled(true);
			
			botaoDefinir.setDisabled(true);
			
			Button botaoAnularCancelamento = new Button("Anular Cancelamento");
			botaoAnularCancelamento.addEventListener("onClick", new EventListener() {
				public void onEvent(Event event) throws Exception {
					JanAnularCancelamentoAlocacaoRH janAnularCancelamentoAlocacao = new JanAnularCancelamentoAlocacaoRH(ctrl, cancelamentoAlocacao);
					janAnularCancelamentoAlocacao.mostrar();
				}
				
			});
			botaoAnularCancelamento.setParent(hboxBotoes);
		}
	}
}
