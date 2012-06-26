package ode.resolucaoProblema.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;
import java.util.Collection;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vlayout;
import ode._controleFerramenta.cdp.FerramentaSoftware;
import ode._controleProcesso.cdp.Atividade;
import ode._controleProcesso.cdp.DefinicaoAtividade;
import ode._controleProcesso.cdp.DemandaRH;
import ode._controleProcesso.cdp.ProcessoProjetoEspecifico;
import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.alocacaoRecurso.cdp.AlocacaoFerramentaSoftware;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.CancelamentoAlocacaoRH;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;
import ode.alocacaoRecurso.ciu.CtrlAlocacaoRecurso;
import ode.alocacaoRecurso.ciu.JanAnularCancelamentoAlocacaoRH;
import ode.alocacaoRecurso.ciu.JanAvaliarAlocacaoRH;
import ode.alocacaoRecurso.ciu.JanCancelarAlocacaoRH;
import ode.conhecimento.processo.cdp.KFerramentaSoftware;
import ode.conhecimento.processo.cdp.KRecurso;
import ode.conhecimento.processo.cdp.KRecursoHumano;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

public class JanelaListagemResultadoSolucao extends JanelaSimples{

	
private static final long serialVersionUID = 1L;
	
	private CtrlRegistrarResultadoSolucao ctrl;
	
	private Tree arvore;
	
	private Collection<OcorrenciaProblema> listaOcorrenciaProblema;
	
	public JanelaListagemResultadoSolucao(CtrlRegistrarResultadoSolucao ctrl) {
		super();
		this.ctrl = ctrl;
	}
	
	
	
	
	public void mostrar() {	
		this.setTitle("Registro de Resultado de Solução");
		this.setHflex("min");
		
		Hlayout painel = new Hlayout();
		painel.setParent(this);
		
		painel.setSclass("z-valign-top");
		

		listaOcorrenciaProblema= ctrl.aplRegistrarOcorrenciaProblema.recuperarTodos();
		
		if(listaOcorrenciaProblema.size()>0) {
			arvore = new Tree();
			arvore.setParent(painel);
			arvore.setWidth("500px");
			arvore.setHeight("400px");
			arvore.setZclass("z-dottree");
			arvore.addEventListener("onSelect", new ArvoreSelectEventListener());
			

			
			ctrl.preencherPainelRegistroResultadoImplementacaoSolucao();
		}
		else {
			Label label = new Label("Não existe Ocorrência Cadastrada.");
			label.setParent(painel);
		}
		
		super.mostrar();
	}
	
	public void preencherArvore() {
		inserirNodesOcorrencia(listaOcorrenciaProblema);
	
	}
	
	private void inserirNodesOcorrencia(Collection<OcorrenciaProblema> recMed) {
	
		if(arvore.getTreechildren()!=null)
			arvore.getTreechildren().setParent(null);
		for (OcorrenciaProblema ocorrenciaProblema : listaOcorrenciaProblema) {

			if(arvore.getTreechildren()==null) {
				Treechildren tc = new Treechildren();
				tc.setParent(arvore);
			}
			
			Treeitem ti = new Treeitem(ocorrenciaProblema.getNome(),ocorrenciaProblema);
			ti.setParent(arvore.getTreechildren());
			
			
        }
		
	}



	public class ArvoreSelectEventListener implements EventListener {
		public void onEvent(Event e) throws Exception {
			
			SelectEvent event = (SelectEvent) e;
			Treeitem ti = (Treeitem) event.getReference();
				ctrl.setOcorrenciaProblema((OcorrenciaProblema) ti.getValue());
				ctrl.abrirjanelaregistraresultadosolucao(); 
				
	
		}	

	}
	

	
	

	
	
	
	
	
}
