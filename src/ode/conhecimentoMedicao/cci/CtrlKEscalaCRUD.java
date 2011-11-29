package ode.conhecimentoMedicao.cci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KEscala;
import ode.conhecimentoMedicao.cgt.AplCadastrarKEscala;
import ode.conhecimentoMedicao.cgt.AplCadastrarKValorEscala;
import ode.conhecimentoMedicao.cih.FormDadosKEscala;
import ode.conhecimentoMedicao.cih.PainelCRUDKEscala;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKEscalaCRUD.NOME)
public class CtrlKEscalaCRUD extends CtrlCRUD<KEscala> {
	
	public static final String NOME = "CtrlKEscalaCRUD";
	
	@Autowired
	AplCadastrarKEscala apl;
	
	@Autowired
	AplCadastrarKValorEscala aplValorEscala;
	
	@Autowired
	CtrlKValorEscalaCRUD ctrlKValorEscalaCRUD;
	
	private String tituloJanelaDados = "Escala";
	private String tituloJanelaPrincipal = "Escala";
	
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KEscala> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KEscala> definirPainelCRUD() {
		return new PainelCRUDKEscala();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return tituloJanelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KEscala> definirFormularioCadastro() {
		return new FormDadosKEscala();
	}

	@Override
	public KEscala factoryObjetoDados() {
		return new KEscala();
	}

	public AplCadastrarKValorEscala getAplCadastrarKValorEscala() {
		return aplValorEscala;
	}

	public CtrlKValorEscalaCRUD getCtrlValorEscala() {
		return ctrlKValorEscalaCRUD;
	}
	
	@Override
	public void acaoSalvar(){
		formularioDados.atualizarObjeto();
		KEscala objetoCadastro = formularioDados
				.getObjetoCadastroDados();
		if (!objetoCadastro.isPersistente()) {
			super.acaoSalvar();
			return;
		}
		if(apl.existeMedidaRelacionada(objetoCadastro)){
			new SimNaoAlterarHelper<CtrlKEscalaCRUD>(this, NucleoMensagens.getMensagem(NucleoMensagens.TERMO_ESCALA), NucleoMensagens.getMensagem(NucleoMensagens.TERMO_MEDIDA), new EventListener() {
		
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

	public void atualizarFormulario() {
		((FormDadosKEscala)formularioDados).atualizarValorEscala();
	}
	
}
