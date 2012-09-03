package ode.conhecimentoMedicao.cci;

import java.lang.management.GarbageCollectorMXBean;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Vlayout;

import ode.conhecimentoMedicao.cdp.KElementoMensuravel;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKElementoMensuravel;
import ode.conhecimentoMedicao.cgt.AplCadastrarKEscala;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKUnidadeMedida;
import ode.conhecimentoMedicao.cih.FormDadosKMedida;
import ode.conhecimentoMedicao.cih.PainelCRUDKMedida;
import ode.medicao.EntidadeMensuravel.cdp.TipoEntidadeMensuravel;
import ode.medicao.planejamentoMedicao.cci.CtrlDefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarNecessidadeInformacao;
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
	private CtrlDefinicaoOperacionalMedida ctrlV;
	@Autowired
	AplCadastrarKMedida apl;
	
	@Autowired
	AplCadastrarKUnidadeMedida aplKUnidadeMedida;
	
	@Autowired
	AplCadastrarKElementoMensuravel aplKElementoMensuravel;
	
	@Autowired
	AplCadastrarKEscala aplKEscala;
	
	@Autowired
	AplCadastrarNecessidadeInformacao aplKNecessidadeInformacao;
	
	public CtrlKMedidaCRUD(){
		larguraJanPrincipal = "800px";
	}
	public CtrlDefinicaoOperacionalMedida getCtrlKDefinicaoOperacional(){
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
	
	public AplCadastrarKElementoMensuravel getAplKElementoMensuravel(){
		return aplKElementoMensuravel;
	}

	public AplCadastrarKEscala getAplKEscala() {
		return aplKEscala;
	}

	public AplCRUD<NecessidadeInformacao> getAplNecessidadeInformacao() {
		return aplKNecessidadeInformacao;
	}
	@Override
	public void iniciar() {
		ctrlV = (CtrlDefinicaoOperacionalMedida) SpringUtil.getApplicationContext().getBean(CtrlDefinicaoOperacionalMedida.class);
		super.iniciar();
	};
	
	public Collection<DefinicaoOperacionalMedida> getListagemKDefinicaoOperacional(){
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
	public Collection<KElementoMensuravel> separaElementosPorTiposMensuraveis(Set<TipoEntidadeMensuravel> tipos, Collection<KElementoMensuravel> elementosDisponiveis) {
		Set<KElementoMensuravel> retorno = new HashSet<KElementoMensuravel>();
		for(TipoEntidadeMensuravel tipo:tipos){
			for(KElementoMensuravel elemento:elementosDisponiveis){
				if(elemento.getTipoEntidadeMensuravel().contains(tipo)){
					retorno.add(elemento);
				}
			}
		}
		return retorno;
	}

}
