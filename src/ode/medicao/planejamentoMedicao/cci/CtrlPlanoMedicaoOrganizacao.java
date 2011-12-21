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
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgd.PlanoMedicaoOrganizacaoDAO;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgt.AplElaborarPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cih.PainelPrincipalPlanoMedicao;
import ode.medicao.planejamentoMedicao.cih.PainelPrincipalPlanoMedicaoOganizacao;

@Controller(CtrlPlanoMedicaoOrganizacao.NOME)
public class CtrlPlanoMedicaoOrganizacao extends CtrlPlanoMedicao{
	public static final String NOME = "CtrlPlanoMedicaoOrganizacao";

	
	protected String title ="Novo Plano de Medição da Organização";
	
	private PlanoMedicaoOrganizacao pmo;
	JanelaSimples janela;
	
	private NucleoListbox<PlanoMedicaoOrganizacao> lista;
	
	@Autowired
	AplElaborarPlanoMedicaoOrganizacao apl;
	
	@Override
	public PlanoMedicaoOrganizacao novoPlanoMedicao() {
		return new PlanoMedicaoOrganizacao();
	}

	public PlanoMedicaoOrganizacao getPlanoMedicao() {
		return pmo;
	}
	
	protected PainelPrincipalPlanoMedicao getPainelPrincipal() {
		PainelPrincipalPlanoMedicao pppmo = new PainelPrincipalPlanoMedicaoOganizacao();
		pppmo.setControlador(this);
		return pppmo;
	}

	public void setPlanoMedicao(PlanoMedicaoOrganizacao objeto) {
		this.pmo = objeto;
	}
	
	public void salvar(){
		painel.preencherObjetos(pmo);
		apl.salvar(pmo);
		Messagebox mbox = new Messagebox();
		try {
			mbox.show("Plano de Medição da Organização salvo");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void iniciar() {
		super.iniciar();
		setPlanoMedicao(novoPlanoMedicao());
	}
	
	public void abrir(){
		janela= factoryJanelaSimples();
		lista = new NucleoListbox<PlanoMedicaoOrganizacao>();
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
				painel.preencherDados((PlanoMedicaoOrganizacao)lista.getSelectedItem().getValue());
				janela.onClose();
			}
		});
		janela.mostrar();
	}

	
}
