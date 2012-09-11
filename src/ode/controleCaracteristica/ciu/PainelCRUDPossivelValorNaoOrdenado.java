package ode.controleCaracteristica.ciu;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD.ModoExibicao;
import ode.controleCaracteristica.cdp.PossivelValorNaoOrdenado;

public class PainelCRUDPossivelValorNaoOrdenado extends PainelCRUD<PossivelValorNaoOrdenado>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8580487562426512786L; 

	@Override
	public ListagemSimples<PossivelValorNaoOrdenado> definirListagem() {
		ListagemPossivelValorNaoOrdenado listagem = new ListagemPossivelValorNaoOrdenado();
		return listagem;
	}
	
	@Override
	public void definirComponentes() {

		
		toolbar = definirBarraFerramentas();
		//toolbar = definirBotaoSimilaridade(toolbar);
		toolbar = definirBotaoTabelaSimilaridade(toolbar);
		toolbar.setStyle("border:0px;background:white;");
		
	}
	
	public Toolbar definirBotaoTabelaSimilaridade(Toolbar toolbar){
		
		Toolbarbutton tbbtSimilaridade = new Toolbarbutton();
		tbbtSimilaridade.setImage("/imagens/classificar.png");
		tbbtSimilaridade.setTooltiptext("Definir Tabela de Similaridades");
		tbbtSimilaridade.setParent(toolbar);
		tbbtSimilaridade.addEventListener("onClick", new EventListenerTabelaSimilaridadeNOVO());
		
		Toolbarbutton tbbtSimilaridadeE = new Toolbarbutton();
		tbbtSimilaridadeE.setImage("/imagens/definir.png");
		tbbtSimilaridadeE.setTooltiptext("Editar Tabela de Similaridades");
		tbbtSimilaridadeE.setParent(toolbar);
		tbbtSimilaridadeE.addEventListener("onClick", new EventListenerTabelaSimilaridadeEDITAR());
		
		return toolbar;
	}
	
	/** Classe do evento do botao de definir a tabela de Similaridade. */
	private class EventListenerTabelaSimilaridadeNOVO implements EventListener {

		public void onEvent(Event event) {
			
			CtrlPossivelValorNaoOrdenado ctrlPNO = (CtrlPossivelValorNaoOrdenado) SpringUtil.getApplicationContext().getBean(CtrlPossivelValorNaoOrdenado.class);
			ctrlPNO.definirTabelaSimilaridade(ModoExibicao.NOVO);	
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}

	}
	
	/** Classe do evento do botao de editar a tabela de Similaridade. */
	private class EventListenerTabelaSimilaridadeEDITAR implements EventListener {

		public void onEvent(Event event) {
			
			CtrlPossivelValorNaoOrdenado ctrlPNO = (CtrlPossivelValorNaoOrdenado) SpringUtil.getApplicationContext().getBean(CtrlPossivelValorNaoOrdenado.class);
			ctrlPNO.definirTabelaSimilaridade(ModoExibicao.EDITAR);	
		}

		@SuppressWarnings("unused")
		public boolean isAsap() {
			return true;
		}

	}
}