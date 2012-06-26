package ode.resolucaoProblema.ciu;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.problema.cdp.KCausa;
import ode.problema.cdp.KProblema;
import ode.problema.cgd.KCausaDAO;
import ode.problema.cgd.KProblemaDAO;
import ode.problema.cgd.KSolucaoDAO;
import ode.problema.cgt.AplCadastrarKCausa;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;




@Controller
public class CtrlSelecionarCausa extends CtrlBase {
	
	
	
	private static final long serialVersionUID = -7467017035161856862L;

	private JanelaSelecionarCausa jan;
	 private KProblema kproblema;
	
	private KCausa kcausa;
	@Autowired
	public KCausaDAO kcausaDAO; 
	@Autowired
	public KSolucaoDAO ksolucaoDAO; 
	@Autowired
	public KProblemaDAO kproblemaDAO; 
	@Autowired
	AplCadastrarKCausa aplcadastrarkcausa;
private OcorrenciaProblema ocorrenciaproblema;

JanelaAvaliarSolucoesComCriterio janelaAvaliarSolucoesComCriterio;
	
	@Override
	public void iniciar() {

		
		jan = new JanelaSelecionarCausa();
		
		jan.mostrar();
	}
	
	public KCausa getKCausa() {
		return kcausa;
	}
	
	public void setKCausa(KCausa kcausa) {
		this.kcausa = kcausa;
	}

	public KProblema getKProblema() {
	
		// TODO Auto-generated method stub
		return kproblema;
	}

	public OcorrenciaProblema getOcorrenciaproblema() {
	
		return ocorrenciaproblema;
	}

	public void setOcorrenciaproblema(OcorrenciaProblema ocorrenciaproblema) {
		
		this.ocorrenciaproblema = ocorrenciaproblema;
	}
	
	public void AbrirJanelaAvaliarSolucoesComCriterio (Set<Listitem> listasolucao3){
	  janelaAvaliarSolucoesComCriterio= new JanelaAvaliarSolucoesComCriterio();
	 
		janelaAvaliarSolucoesComCriterio.mostrar(listasolucao3);

	}

	  
}
