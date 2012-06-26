package ode.resolucaoProblema.ciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;
import ode.resolucaoProblema.cgt.AplRegistrarOcorrenciaProblema;

@Controller
public class CtrlResolverProblema extends CtrlBase {

	CtrlSelecionarCausa ctrlSelecCausa;

	public CtrlResolverProblema() {
		super();
		ctrlSelecCausa = new CtrlSelecionarCausa();
	}

	private static final long serialVersionUID = -5105121227920474531L;

	private JanelaResolverProblema jan;
   
	public OcorrenciaProblema ocorrenciaproblema;
	
	@Autowired
	AplRegistrarOcorrenciaProblema aplocorrenciaproblema;


	@Override
	public void iniciar() {

		jan = new JanelaResolverProblema(this);
		jan.mostrar();
	}

	public OcorrenciaProblema getOcorrenciaProblema() {
		return ocorrenciaproblema;
	}

	public void setOcorrenciaProblema(OcorrenciaProblema ocorrenciaproblema) {
		this.ocorrenciaproblema = ocorrenciaproblema;
	}

	public void preencherPainelOcorrenciaProblema() {

		jan.preencherArvore();
	}

	public void abrirControladorSelecionarCausa(OcorrenciaProblema op) {
		 JanelaSelecionarCausa janelaSelecionarCausa = new JanelaSelecionarCausa();
		 janelaSelecionarCausa.mostrar(op);
	}

}
