package ode.medicao.execucaoMedicao.ciu;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.medicao.execucaoMedicao.cdp.Medicao;
import ode.medicao.execucaoMedicao.cgt.AplRegistrarMedicao;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;

@Controller
public class CtrlMedicao extends CtrlBase{

	private JanelaSimples js;
	private PainelMedicao painelMedicao;
	
	private JanelaSimples janela;	
	private NucleoListbox<Medicao> lista;
	
	private Medicao atual;
	
	@Autowired
	AplCadastrarRecursoHumano aplRH;
	@Autowired
	AplCadastrarKMedida aplKmedida;
	@Autowired
	AplCadastrarProjeto aplProjeto;
	@Autowired
	AplCadastrarKAtividade aplAtividade;
	@Autowired
	AplRegistrarMedicao aplMedicao;
	
	public Medicao getNewMedicao(){
		return new Medicao();
	}
	
	public Collection<Projeto> getTodosProjetos(){
		return aplProjeto.recuperarTodos();
	}
	
	public Collection<KMedida> getTodasMedidas(){
		return aplKmedida.recuperarTodos();
	}
	
	public Collection<KAtividade> getTodasAtividades(){
		return aplAtividade.recuperarTodos();
	}
	
	public Collection<RecursoHumano> getTodosRecursosHumano(){
		return aplRH.recuperarTodos();
	}
	
	@Override
	public void iniciar() {
		
		js = factoryJanelaSimples();
		
		js.setTitle("Execução de Medição");
		
		painelMedicao = new PainelMedicao();
		
		painelMedicao.setCtrl(this);
		
		painelMedicao.montar();
		
		painelMedicao.setParent(js);
		
		js.mostrar();
	}
	
	public void iniciar(Medicao objeto) {	
		js = factoryJanelaSimples();
		
		js.setTitle(objeto.toString());
		
		painelMedicao = new PainelMedicao();
		
		painelMedicao.setCtrl(this);
		
		painelMedicao.montar(objeto);
		
		painelMedicao.setParent(js);
		
		js.mostrar();
	}

	public void salvar() {
		Medicao med = getNewMedicao();
		painelMedicao.painelToObjeto(med);
		aplMedicao.salvar(med);
		atual = med;
	}

	public void abrirMedicoesPassadas() {
		janela= factoryJanelaSimples();
		lista = new NucleoListbox<Medicao>();
		Vbox box = new Vbox();
		box.setParent(janela);
		lista.setParent(box);
		janela.setTitle("Abrir");
		lista.setObjetos(aplMedicao.recuperarTodos());
		Button ok = new Button("Abrir");
		ok.setParent(box);
		ok.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				js.onClose();
				atual = lista.getObjetoSelecionado();
				iniciar(atual);
				janela.onClose();
			}
		});
		janela.mostrar();
	}

	public void atualizar() {
		painelMedicao.painelToObjeto(atual);
		aplMedicao.atualizar(atual);
	}

	public void excluir() {
		aplMedicao.excluir(atual);
	}

	public void fechar() {

		if(janela!=null){
			janela.onClose();
		}
		
		atual=null;
	}

}
