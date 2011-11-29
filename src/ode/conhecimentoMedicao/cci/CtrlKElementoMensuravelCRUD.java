package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKElementoMensuravel;
import ode.conhecimentoMedicao.cih.FormDadosKElementoMensuravel;
import ode.conhecimentoMedicao.cih.PainelCRUDKElementoMensuravel;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKElementoMensuravelCRUD.NOME)
public class CtrlKElementoMensuravelCRUD extends CtrlCRUD<KElementoMensuravel>{

	public static final String NOME = "CtrlKElementoMensuravelCRUD";
	
	@Autowired
	AplCadastrarKElementoMensuravel apl;
	
	
	private String tituloJanelaDados = "Elemento Mensuravel";
	private String tituloJanelaPrincipal = "Elemento Mensuravel";
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KElementoMensuravel> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KElementoMensuravel> definirPainelCRUD() {
		return new PainelCRUDKElementoMensuravel();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return tituloJanelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KElementoMensuravel> definirFormularioCadastro() {
		return new FormDadosKElementoMensuravel();
	}

	@Override
	public KElementoMensuravel factoryObjetoDados() {
		return new KElementoMensuravel();
	}

	@Override
	public void acaoSalvar(){
		formularioDados.atualizarObjeto();
		KElementoMensuravel objetoCadastro = formularioDados
				.getObjetoCadastroDados();
		if (!objetoCadastro.isPersistente()) {
			super.acaoSalvar();
			return;
		}
		if(apl.relacionamentoComMedida(objetoCadastro)){
			new SimNaoAlterarHelper<CtrlKElementoMensuravelCRUD>(this, NucleoMensagens.getMensagem(NucleoMensagens.TERMO_ELEMENTO_MENSURAVEL), NucleoMensagens.getMensagem(NucleoMensagens.TERMO_MEDIDA), new EventListener() {
		
				@Override
				public void onEvent(Event arg0) throws Exception {
					callbackAcaoSalvar();
					
				}
			});
		}else{
			super.acaoSalvar();
		}
	}

	public void callbackAcaoSalvar() {
		super.acaoSalvar();
	}
	
}
