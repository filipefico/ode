package ode.resolucaoProblema.ciu;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;
import ode.resolucaoProblema.cdp.ResultadoImplementacaoSolucao;

import ode.resolucaoProblema.cgt.AplRegistrarOcorrenciaProblema;
import ode.resolucaoProblema.cgt.AplResultadoImplementacaoSolucao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CtrlRegistrarResultadoSolucao extends CtrlBase {

	
	private static final long serialVersionUID = -1953525478782397826L;
	private OcorrenciaProblema ocorrenciaProblemaSelecionado;
	private JanelaListagemResultadoSolucao jan;
	private OcorrenciaProblema ocorrenciaProblema;
	private ResultadoImplementacaoSolucao resultadoImplementacaoSolucao;
	@Autowired 
	AplResultadoImplementacaoSolucao aplResultadoImplementacaoSolucao; 
	
	@Autowired 
	AplRegistrarOcorrenciaProblema aplRegistrarOcorrenciaProblema;
	
	public OcorrenciaProblema getOcorrenciaProblemaSelecionado() {
		return ocorrenciaProblemaSelecionado;
	}

	public void setOcorrenciaProblemaSelecionado(
			OcorrenciaProblema ocorrenciaProblemaSelecionado) {
		this.ocorrenciaProblemaSelecionado = ocorrenciaProblemaSelecionado;
	}
	
	
	@Override
	public void iniciar() {

		
		jan = new JanelaListagemResultadoSolucao(this);
		jan.mostrar();
	}
	
	public OcorrenciaProblema getOcorrenciaProblema() {
		return ocorrenciaProblema;
	}

	public void setOcorrenciaProblema(OcorrenciaProblema ocorrenciaProblema) {
		this.ocorrenciaProblema = ocorrenciaProblema;
	}
	
	
	public void salvarResultadoImplementacaoSolucao(ResultadoImplementacaoSolucao resultadoImplementacaoSolucao) {
		aplResultadoImplementacaoSolucao.salvarResultadoImplementacaoSolucao(resultadoImplementacaoSolucao);
	}

	public ResultadoImplementacaoSolucao getResultadoImplementacaoSolucao() {
		return resultadoImplementacaoSolucao;
	}

	public void setResultadoImplementacaoSolucao(
			ResultadoImplementacaoSolucao resultadoImplementacaoSolucao) {
		this.resultadoImplementacaoSolucao = resultadoImplementacaoSolucao;
	}
	
	
	public void preencherPainelRegistroResultadoImplementacaoSolucao() {

		jan.preencherArvore();
}
	
	public void abrirjanelaregistraresultadosolucao(){
		JanelaRegistrarResultadoSolucao janelaRegistrarResultadoSolucao= new JanelaRegistrarResultadoSolucao(this);
			janelaRegistrarResultadoSolucao.mostrar();
	}

	
}

