package ode.conhecimentoMedicao.cci;

import java.lang.management.GarbageCollectorMXBean;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Vlayout;

import ode.conhecimentoMedicao.cdp.KDefinicaoOperacionalMedida;
import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.KNecessidadeInformacao;
import ode.conhecimentoMedicao.cdp.KTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKElementoMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKEscala;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKNecessidadeInformacao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKTipoEntidadeMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKUnidadeMedida;
import ode.conhecimentoMedicao.cih.FormDadosKMedida;
import ode.conhecimentoMedicao.cih.PainelCRUDKMedida;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;

@Controller(CtrlKMedidaCRUD.NOME)
public class CtrlKMedidaCRUD extends CtrlCRUD<KMedida>{

	public static final String NOME = "CtrlKMedidaCRUD";
	
	private final String tituloJanelaDados = "Medida";
	private final String tituloJanelaPrincipal = "Medida";
	private CtrlKDefinicaoOperacionalMedida ctrlV;
	@Autowired
	AplCadastrarKMedida apl;
	
	@Autowired
	AplCadastrarKUnidadeMedida aplKUnidadeMedida;
	
	@Autowired
	AplCadastrarKTipoEntidadeMensuravel aplKTipoEntidadeMensuravel;
	
	@Autowired
	AplCadastrarKElementoMensuravel aplKElementoMensuravel;
	
	@Autowired
	AplCadastrarKEscala aplKEscala;
	
	@Autowired
	AplCadastrarKNecessidadeInformacao aplKNecessidadeInformacao;
	
	public CtrlKMedidaCRUD(){
		larguraJanPrincipal = "800px";
	}
	public CtrlKDefinicaoOperacionalMedida getCtrlKDefinicaoOperacional(){
		return ctrlV;
	}
	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<KMedida> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<KMedida> definirPainelCRUD() {
		return new PainelCRUDKMedida();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return tituloJanelaPrincipal;
	}

	@Override
	public FormularioDadosCRUD<KMedida> definirFormularioCadastro() {
		return new FormDadosKMedida();
	}

	@Override
	public KMedida factoryObjetoDados() {
		return new KMedida();
	}

	public AplCadastrarKUnidadeMedida getAplKUnidadeMedida() {
		return aplKUnidadeMedida;
	}

	public AplCadastrarKTipoEntidadeMensuravel getAplKTipoEntidadeMensuravel() {
		return aplKTipoEntidadeMensuravel;
	}
	
	public AplCadastrarKElementoMensuravel getAplKElementoMensuravel(){
		return aplKElementoMensuravel;
	}

	public AplCadastrarKEscala getAplKEscala() {
		return aplKEscala;
	}

	public AplCRUD<KNecessidadeInformacao> getAplNecessidadeInformacao() {
		return aplKNecessidadeInformacao;
	}
	@Override
	public void iniciar() {
		ctrlV = (CtrlKDefinicaoOperacionalMedida) SpringUtil.getApplicationContext().getBean(CtrlKDefinicaoOperacionalMedida.class);
		super.iniciar();
	};
	
	public Collection<KDefinicaoOperacionalMedida> getListagemKDefinicaoOperacional(){
		return ctrlV.getListagemKDefinicaoOperacional();
	}
	
	@Override
	public void mostrarFormulario(
			ode._infraestruturaCRUD.ciu.CtrlCRUD.ModoExibicao modoExibicao) {
		super.mostrarFormulario(modoExibicao);
		janDados.addEventListener("onClose", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				onJanClose();
				
			}
		});
	}
	
	public void onJanClose(){
		try{
			ctrlV.getFormDados().getParent().detach();
		}catch(NullPointerException e){
			//Tá de boa... O formDados nao foi instanciado ainda. por isso nao consegue fechar
		}
	}

}
