package ode.alocacaoRecurso.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.util.NucleoUtil;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.NucleoListHeader;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.alocacaoRecurso.cdp.EsforcoDespendido;
import ode.alocacaoRecurso.cdp.EstadoAlocacaoRH;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;

public class PainelCRUDEsforcoDespendido extends PainelCRUD<EsforcoDespendido> {

	private static final long serialVersionUID = 1L;
	
	public PainelCRUDEsforcoDespendido(CtrlEsforcoDespendidoCRUD ctrl) {
		setControlador(ctrl);
	}
	
	public CtrlEsforcoDespendidoCRUD getControlador() {
		return (CtrlEsforcoDespendidoCRUD)super.getControlador();
	}
		
	@Override
	protected Toolbar definirBarraFerramentas() {
		Toolbar toolbar = super.definirBarraFerramentas();
		
		if(!getControlador().getEstadoAlocacao().equals(EstadoAlocacaoRH.AguardandoInicioParticipacao) &&
				!getControlador().getEstadoAlocacao().equals(EstadoAlocacaoRH.EmAndamento) &&
				!getControlador().getEstadoAlocacao().equals(EstadoAlocacaoRH.EmAndamentoAjustes)) {
			for(Object tbbt : toolbar.getChildren()) {
				if (tbbt instanceof Toolbarbutton) {
					((Toolbarbutton) tbbt).setDisabled(true);
				}
			}
		}
		
		Toolbarbutton tbbtEncerrar = new Toolbarbutton();
		tbbtEncerrar.setParent(toolbar);
		tbbtEncerrar.setTooltip("Solicitar Encerramento da Participação");
		tbbtEncerrar.setLabel("Encerrar Participação");
		if(getControlador().getEstadoAlocacao().equals(EstadoAlocacaoRH.EmAndamento)
				|| getControlador().getEstadoAlocacao().equals(EstadoAlocacaoRH.EmAndamentoAjustes)) {
			tbbtEncerrar.addEventListener("onClick", new EventListener() {
				public void onEvent(Event event) throws Exception {
					getControlador().acaoEncerrarParticipacao();
				}
			});	
		}
		else {
			tbbtEncerrar.setDisabled(true);
		}
		
		return toolbar;
	}

	@Override
	public ListagemSimples<EsforcoDespendido> definirListagem() {
		
		return new ListagemSimples<EsforcoDespendido>() {

			private static final long serialVersionUID = 1L;
			
			@Override
			public void configurarComponentes() {
				super.configurarComponentes();
				listBox.setHeight("233px");
				listBox.setCheckmark(false);
				listBox.setMultiple(false);
			}

			@Override
			public List<NucleoListHeader> definirColunasTabela() {
				List<NucleoListHeader> colunas = new ArrayList<NucleoListHeader>();
				colunas.add(new NucleoListHeader("Data","data","50%"));
				colunas.add(new NucleoListHeader("Tempo","qtdHoras","50%"));
				return colunas;
			}

			@Override
			protected String[] recuperarDadosObjeto(EsforcoDespendido objeto) {
				return new String[]{NucleoUtil.formataData(objeto.getData()),Integer.toString(objeto.getQtdHoras())};
			}
		};
	}

}
