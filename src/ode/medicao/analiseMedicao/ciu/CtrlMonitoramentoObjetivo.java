package ode.medicao.analiseMedicao.ciu;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.analiseMedicao.cdp.MonitoramentoObjetivo;
import ode.medicao.analiseMedicao.cgt.AplAnaliseMedicao;
import ode.medicao.analiseMedicao.cgt.AplMonitoramento;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplElaborarPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgt.AplElaborarPlanoMedicaoProjeto;


@Controller
public class CtrlMonitoramentoObjetivo extends CtrlBase{
	
	private JanelaSimples janelaPainel;
	private JanelaSimples janelaForm;
	private FormMonitoramento form;
	private PainelMonitoramento painel;
	private MonitoramentoObjetivo objeto;
	
	private final String larguraPainel = "715px";
	
	@Autowired
	AplMonitoramento apl;
	@Autowired
	AplCadastrarRecursoHumano aplRH;
	@Autowired
	AplCadastrarKAtividade aplativ;
	@Autowired
	AplElaborarPlanoMedicaoOrganizacao aplPMO;
	@Autowired
	AplElaborarPlanoMedicaoProjeto aplPMP;
	
	@Override
	public void iniciar() {
		janelaPainel = factoryJanelaSimples();
		painel = new PainelMonitoramento(this);
		
		janelaPainel.setTitle("Monitoramento");
		janelaPainel.setWidth(larguraPainel);
		
		painel.setParent(janelaPainel);
		
		painel.configurarComponentes();
		
		painel.mostrar();
		
		janelaPainel.mostrar();
	}
	
	public Iterable<KMedida> getMedidas() {
		return apl.recuperarTodasMedidas();
	}
	public Iterable<Projeto> getProjetos() {
		return apl.recuperarTodosProjetos();
	}
	
	public Iterable<RecursoHumano> getRecursosHumanos() {
		return aplRH.recuperarTodos();
	}
	public Iterable<KAtividade> getAtividades() {
		return aplativ.recuperarTodos();
	}
	public Iterable<PlanoMedicao> getPlanos() {
		Set<PlanoMedicao> pm = new HashSet<PlanoMedicao>();
		pm.addAll(aplPMP.recuperarTodos());
		pm.addAll(aplPMO.recuperarTodos());
		return pm;
	}

	public void acaoNovo() {
		janelaForm = factoryJanelaSimples();
		form = new FormMonitoramento(this);
		
		janelaForm.setTitle("Monitoramentos");
		janelaForm.setWidth(larguraPainel);
		
		objeto = new MonitoramentoObjetivo();
		
		form.setParent(janelaForm);
		
		form.montar();
		
		janelaForm.mostrar();
		
	}

	public void acaoAbrir() {
		janelaForm = factoryJanelaSimples();
		form = new FormMonitoramento(this);
		
		janelaForm.setTitle("Analise de Medição");
		janelaForm.setWidth(larguraPainel);
		
		objeto = painel.getListagem().getSelecionado();

		form.montar();
		
		form.preencheFormulario(objeto);
		
		form.setParent(janelaForm);
		
		janelaForm.mostrar();
	}

	public void acaoExcluir() {
		apl.excluir(objeto);
	}

	public void acaoSalvar() {
		form.preencheObjeto(objeto);
		
		apl.salvar(objeto);
		
		try{
			Messagebox.show("Objeto Salvo");
		}catch(Exception e){
			
		}
		painel.mostrar();
		
	}

	public void gerarGrafico(Date inicio, Date fim) {
		try{
			Messagebox.show("Função não implementada ainda.");
		}catch(Exception e){
			
		}
	}

	public Collection<MonitoramentoObjetivo> getMonitoramentos() {
		return apl.recuperarTodos();
	}


	
}
