package ode.resolucaoProblema.ciu;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zul.Textbox;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode.problema.cdp.KCriterioSelecaoSolucao;
import ode.problema.cgt.AplCadastrarKCriterioSelecaoSolucao;

@Controller
public class CtrlAvaliarSolucoesComCriterio extends CtrlBase {
	@Autowired
	AplCadastrarKCriterioSelecaoSolucao aplcadastrarkcriterioselecaosolucao;

	private static final long serialVersionUID = -1935320398555700193L;
	@Autowired
	AplCadastrarKCriterioSelecaoSolucao aplCadastrarKCriterioSelecaoSolucao;

	@Override
	public void iniciar() {
		// TODO Auto-generated method stub

	}

	public void salvarpeso(KCriterioSelecaoSolucao kcriterioselecaosolucao) {
		aplcadastrarkcriterioselecaosolucao.salvarpeso(kcriterioselecaosolucao);
	}

	/*
	 * public void AbrirJanelaAvaliarSolucoescomCriterio22( List<Textbox>
	 * listapesos,JanelaAvaliarSolucoesComCriterio
	 * janelaAvaliarSolucoesComCriterio){ JanelaAvaliarSolucoesComCriterio2
	 * janelaAvaliarSolucoesComCriterio2= new
	 * JanelaAvaliarSolucoesComCriterio2(listapesos,
	 * janelaAvaliarSolucoesComCriterio);
	 * janelaAvaliarSolucoesComCriterio2.mostrar2(); }
	 * 
	 * public void AbrirJanelaAvaliarSolucoescomCriterio23( List<Textbox>
	 * listapesos,JanelaAvaliarSolucoesComCriterio
	 * janelaAvaliarSolucoesComCriterio){ JanelaAvaliarSolucoesComCriterio2
	 * janelaAvaliarSolucoesComCriterio2= new
	 * JanelaAvaliarSolucoesComCriterio2(listapesos,
	 * janelaAvaliarSolucoesComCriterio);
	 * janelaAvaliarSolucoesComCriterio2.mostrar3(); }
	 */
}
