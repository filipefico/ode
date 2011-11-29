package ode.medicao.planejamentoMedicao.cci;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.medicao.planejamentoMedicao.cdp.KNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cdp.KObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.medicao.planejamentoMedicao.cdp.PlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKNecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKObjetivoEstrategico;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarKObjetivoSoftware;
import ode.medicao.planejamentoMedicao.cgt.AplElaborarPlanoMedicaoOrganizacao;
import ode.medicao.planejamentoMedicao.cih.PainelPrincipalPlanoMedicaoOganizacao;

@Controller(CtrlPlanoMedicaoOrganizacao.NOME)
public class CtrlPlanoMedicaoOrganizacao extends CtrlPlanoMedicao{

	private PlanoMedicaoOrganizacao pmo;
	
	@Override
	public PlanoMedicaoOrganizacao novoPlanoMedicao() {
		return new PlanoMedicaoOrganizacao();
	}

	public PlanoMedicaoOrganizacao getPlanoMedicao() {
		return pmo;
	}

	public void setPlanoMedicao(PlanoMedicaoOrganizacao objeto) {
		this.pmo = objeto;
	}

	
}
