package ode.alocacaoRecurso.ciu;

import java.util.Collection;

import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.alocacaoRecurso.cdp.AlocacaoRH;
import ode.alocacaoRecurso.cdp.EsforcoDespendido;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;
import ode.alocacaoRecurso.cgt.AplRegistrarEsforcoDespendido;
import ode.alocacaoRecurso.cgt.AplControlarAlocacaoRH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

@Controller
public class CtrlEsforcoDespendidoCRUD extends CtrlCRUD<EsforcoDespendido> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AplRegistrarEsforcoDespendido aplRegistrarEsforcoDespendido;
	
	@Autowired
	private AplControlarAlocacaoRH aplControlarAlocacaoRH;
	
	private AlocacaoRH alocacaoRH;
		
	public PainelCRUDEsforcoDespendido iniciar(AlocacaoRH alocacaoRH) {
		this.alocacaoRH = alocacaoRH;
		this.painelCRUD = new PainelCRUDEsforcoDespendido(this);
		configurarComponentes();
		atualizarPesquisa();
		return (PainelCRUDEsforcoDespendido)this.painelCRUD;
	}
	
	@Override
	public JanelaSimples factoryJanelaSimples() {
		JanelaSimples jan = new JanelaSimples();
		jan.setWidth("300px");
		return jan;
	}
	
	@Override
	public void atualizarPesquisa() {
		Collection<EsforcoDespendido> objetos = aplRegistrarEsforcoDespendido.recuperarPorAlocacaoRH(alocacaoRH.getId());
		painelCRUD.getListagem().atualizar(objetos);
		atualizarToolbar();
	}
	
	@SuppressWarnings("unchecked")
	private void atualizarToolbar() {
		painelCRUD.getToolbar().setParent(null);
		painelCRUD.definirComponentes();		
		painelCRUD.getChildren().add(0, painelCRUD.getToolbar());
	}

	@Override
	public String definirTituloJanelaDados() {
		return "Cadastrar Esforço Despendido";
	}

	@Override
	public AplCRUD<EsforcoDespendido> definirAplCRUD() {
		return this.aplRegistrarEsforcoDespendido;
	}

	@Override
	public PainelCRUD<EsforcoDespendido> definirPainelCRUD() {
		return painelCRUD;
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "Usuários";
	}

	@Override
	public FormularioDadosCRUD<EsforcoDespendido> definirFormularioCadastro() {
		return new FormDadosEsforcoDespendido();
	}

	@Override
	public EsforcoDespendido factoryObjetoDados() {
		EsforcoDespendido e = new EsforcoDespendido();
		e.setAlocacaoRH(alocacaoRH);
		return e;
	}

	public AlocacaoRH getAlocacaoRH() {
		return alocacaoRH;
	}

	public void acaoEncerrarParticipacao() {
		try {
			confirmaSimNao("Deseja realmente solicitar o encerramento da participação na atividade?", new EventListener() {

				public void onEvent(Event event) throws InterruptedException {
					if (((Integer) event.getData()).intValue() == Messagebox.YES) {
						aplControlarAlocacaoRH.solicitarEncerramentoParticipacao(alocacaoRH);
						atualizarToolbar();
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public EstadoAlocacaoRH getEstadoAlocacao() {
		return alocacaoRH.getEstado();
	}
}
