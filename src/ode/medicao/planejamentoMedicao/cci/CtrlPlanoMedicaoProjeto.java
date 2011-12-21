package ode.medicao.planejamentoMedicao.cci;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jruby.ast.PreExeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Vbox;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgd.ProjetoDAO;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoProjeto;
import ode.medicao.planejamentoMedicao.cgd.PlanoMedicaoProjetoDAO;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgt.AplElaborarPlanoMedicaoProjeto;
import ode.medicao.planejamentoMedicao.cih.PainelPrincipalPlanoMedicao;
import ode.medicao.planejamentoMedicao.cih.PainelPrincipalPlanoMedicaoOganizacao;
import ode.medicao.planejamentoMedicao.cih.PainelPrincipalPlanoMedicaoProjeto;

@Controller(CtrlPlanoMedicaoProjeto.NOME)
public class CtrlPlanoMedicaoProjeto extends CtrlPlanoMedicao{
	public static final String NOME = "CtrlPlanoMedicaoProjeto";


	protected String title ="Novo Plano de Medição do Projeto";
	
	private PlanoMedicaoProjeto pmo;
	JanelaSimples janela;
	
	private NucleoListbox<PlanoMedicaoProjeto> lista;
	
	@Autowired
	AplElaborarPlanoMedicaoProjeto apl;
	
	@Autowired
	ProjetoDAO pdao;
	
	@Override
	public PlanoMedicaoProjeto novoPlanoMedicao() {
		return new PlanoMedicaoProjeto();
	}

	public PlanoMedicaoProjeto getPlanoMedicao() {
		return pmo;
	}

	public void setPlanoMedicao(PlanoMedicaoProjeto objeto) {
		this.pmo = objeto;
	}
	
	public void salvar(){
		painel.preencherObjetos(pmo);
		apl.salvar(pmo);
		Messagebox mbox = new Messagebox();
		try {
			mbox.show("Plano de Medição do Projeto salvo");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected PainelPrincipalPlanoMedicao getPainelPrincipal() {
		PainelPrincipalPlanoMedicao pppmo = new PainelPrincipalPlanoMedicaoProjeto();
		pppmo.setControlador(this);
		return pppmo;
	}
	
	@Override
	public void iniciar() {
		super.iniciar();
		setPlanoMedicao(novoPlanoMedicao());
	}
	
	public void abrir(){
		janela= factoryJanelaSimples();
		lista = new NucleoListbox<PlanoMedicaoProjeto>();
		Vbox box = new Vbox();
		box.setParent(janela);
		lista.setParent(box);
		janela.setTitle("Abrir");
		lista.setObjetos(apl.recuperarTodos());
		Button ok = new Button("Abrir");
		ok.setParent(box);
		ok.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				painel.preencherDados((PlanoMedicaoProjeto)lista.getSelectedItem().getValue());
				janela.onClose();
			}
		});
		janela.mostrar();
	}

	public Iterable<Projeto> getProjetos() {
		return pdao.recuperarTodos();
	}

	
}
