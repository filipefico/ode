package ode.resolucaoProblema.ciu;



import java.util.EventListener;
import java.util.List;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.processoPadrao.ciu.CtrlDefinirProcessoPadrao;
import ode.resolucaoProblema.cdp.NivelImpacto;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;
import ode.resolucaoProblema.cdp.ResultadoImplementacaoSolucao;
import ode.resolucaoProblema.cdp.ResultadoSolucao;


public class JanelaRegistrarResultadoSolucao extends JanelaSimples {
	

	private static final long serialVersionUID = 3881410716062329323L;

	private CtrlRegistrarResultadoSolucao ctrl;
	
	private JanelaRegistrarResultadoSolucao janela;

	
	private Button buttonSalvar = new Button();
	private Button buttonCancelar = new Button();
	private Vlayout box;
	
	public JanelaRegistrarResultadoSolucao(CtrlRegistrarResultadoSolucao ctrl) {
			super();
			janela = this;
		this.ctrl = ctrl;
		}
	
	
	public void mostrar() {	
		this.setTitle("Registro de Resultado de Solução");
		this.setHflex("min");
		Hlayout painel = new Hlayout();
		painel.setWidth("600px");
		painel.setHeight("380px");
		painel.setParent(this);
		
		painel.setSclass("z-valign-top");
		
		box = new Vlayout();
		box.setParent(painel);
		box.setWidth("600px");
		box.setHeight("400px");
		
		preencherJanelaRegistrarResultadoSolucao();
		
		super.mostrar();
					
	}
	 
	private OcorrenciaProblema ocorrenciaSelecionada;
	private Textbox tbObservacao = new Textbox();
	private NucleoListbox<NivelImpacto> lbNivelImpactoTempo = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> listboxNivelImpactoTempo = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> lbNivelImpactoCusto = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> listboxNivelImpactoCusto = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> lbNivelImpactoQualidade = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<NivelImpacto> listboxNivelImpactoQualidade = new NucleoListbox<NivelImpacto>();
	private NucleoListbox<OcorrenciaProblema> listboxOcorrencia= new NucleoListbox<OcorrenciaProblema>();
	private NucleoListbox<ResultadoSolucao> lbResultadoSolucao = new NucleoListbox<ResultadoSolucao>();
	private NucleoListbox<ResultadoSolucao> listboxResultadoSolucao = new NucleoListbox<ResultadoSolucao>();

	

	
	 
	 class EventListenerOcorrencia implements EventListener, org.zkoss.zk.ui.event.EventListener {

	        public void onEvent(org.zkoss.zk.ui.event.Event event) {
	        	ocorrenciaSelecionada	 = listboxOcorrencia.getObjetoSelecionado();
	        	
	        }

	   }
	
	 
	 protected ResultadoImplementacaoSolucao preencherDadosObjeto() {    
		    ResultadoImplementacaoSolucao objeto = new ResultadoImplementacaoSolucao();
	        objeto.setObservacao(tbObservacao.getValue());
	        objeto.setImpactotempo(lbNivelImpactoTempo.getObjetoSelecionado());
	        objeto.setImpactocusto(lbNivelImpactoCusto.getObjetoSelecionado());
	        objeto.setImpactoqualidade(lbNivelImpactoQualidade.getObjetoSelecionado());
	        objeto.setResultadosolucao(lbResultadoSolucao.getObjetoSelecionado());
	      
	        
	        return objeto;
	    }
	 
	 
	 public class EventListnerSalvar implements EventListener, org.zkoss.zk.ui.event.EventListener {
			public void onEvent(Event arg0) throws Exception {
				//configurarConstraints();
				ResultadoImplementacaoSolucao resultadoImplementacaoSolucao = preencherDadosObjeto();
			    ctrl.salvarResultadoImplementacaoSolucao(resultadoImplementacaoSolucao);
				janela.onClose();// fecha a janela
			}
		}
	 
		public class EventListnerCancelar implements EventListener, org.zkoss.zk.ui.event.EventListener {
			public void onEvent(Event arg0) throws Exception {
				janela.onClose();// fecha a janela
			}
		}
	 
	 
	 private void preencherJanelaRegistrarResultadoSolucao() {
			
		//	final OcorrenciaProblema ocorrenciaProblema = ctrl.getRecomendacaoMedicaoSelecionada();
			
				GridDados grid = new GridDados();
				grid.setParent(box);
			
		       
				
				Listitem liNS = new Listitem();
				Listcell lcNS = new Listcell();
				lbNivelImpactoTempo.setWidth("275px");
				lbNivelImpactoTempo.setRows(1);
				lbNivelImpactoTempo.setObjetos(NivelImpacto.values());
				lbNivelImpactoTempo.setMold("select");
				lbNivelImpactoTempo.selecionarPrimeiroElemento();			
				lcNS.appendChild(lbNivelImpactoTempo);
				liNS.appendChild(lcNS);
				listboxNivelImpactoTempo.appendChild(liNS);
				grid.adicionarLinhaObrigatoria("Impacto Tempo ", listboxNivelImpactoTempo ); 
				
				Listitem liNS2 = new Listitem();
				Listcell lcNS2 = new Listcell();
				lbNivelImpactoCusto.setWidth("275px");
				lbNivelImpactoCusto.setRows(1);
				lbNivelImpactoCusto.setObjetos(NivelImpacto.values());
				lbNivelImpactoCusto.setMold("select");
				lbNivelImpactoCusto.selecionarPrimeiroElemento();			
				lcNS2.appendChild(lbNivelImpactoCusto);
				liNS2.appendChild(lcNS2);
				listboxNivelImpactoCusto.appendChild(liNS2);
				grid.adicionarLinhaObrigatoria("Impacto Custo ", listboxNivelImpactoCusto ); 
				

				Listitem liNS3 = new Listitem();
				Listcell lcNS3 = new Listcell();
				lbNivelImpactoQualidade.setWidth("275px");
				lbNivelImpactoQualidade.setRows(1);
				lbNivelImpactoQualidade.setObjetos(NivelImpacto.values());
				lbNivelImpactoQualidade.setMold("select");
				lbNivelImpactoQualidade.selecionarPrimeiroElemento();			
				lcNS3.appendChild(lbNivelImpactoQualidade);
				liNS3.appendChild(lcNS3);
				listboxNivelImpactoQualidade.appendChild(liNS3);
				grid.adicionarLinhaObrigatoria("Impacto Qualidade ", listboxNivelImpactoQualidade ); 
		
			
				
				Listitem liNS4 = new Listitem();
				Listcell lcNS4 = new Listcell();
				lbResultadoSolucao.setWidth("275px");
				lbResultadoSolucao.setRows(1);
				lbResultadoSolucao.setObjetos(ResultadoSolucao.values());
				lbResultadoSolucao.setMold("select");
				lbResultadoSolucao.selecionarPrimeiroElemento();			
				lcNS4.appendChild(lbResultadoSolucao);
				liNS4.appendChild(lcNS4);
				listboxResultadoSolucao.appendChild(liNS4);
				grid.adicionarLinhaObrigatoria("Resultado da Solução ", listboxResultadoSolucao );
				
				
				 tbObservacao.setWidth("285px");
			        tbObservacao.setMaxlength(500);
			        tbObservacao.setHeight("145px");
			        tbObservacao.setMultiline(true);    
			        grid.adicionarLinhaObrigatoria("Observação ", tbObservacao ); 
			        
				
				
		
				buttonSalvar.setLabel("Salvar");
				buttonCancelar.setLabel("Cancelar");
				
				Hbox hboxBotoes = new Hbox();
				hboxBotoes.setParent(box);
				hboxBotoes.setHeight("100%");
				
				hboxBotoes.setAlign("end");
				
				hboxBotoes.setPack("end");
				
				buttonSalvar.setParent(hboxBotoes);
				buttonCancelar.setParent(hboxBotoes);
				
		

				// definir ação para os botões.
				buttonSalvar.addEventListener("onClick", new EventListnerSalvar());
				buttonCancelar.addEventListener("onClick", new EventListnerCancelar());
						 	
		}
		
	
}
	
