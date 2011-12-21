package ode.medicao.planejamentoMedicao.cci;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.impl.XulElement;

import ode._infraestruturaBase.cdp.ObjetoPersistente;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.excecao.CtrlExcecoes;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaCRUD.cgt.AplCRUD;
import ode._infraestruturaCRUD.ciu.CtrlCRUD;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKRecursoHumano;
import ode.conhecimentoMedicao.cci.CtrlKMedidaCRUD;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cdp.KProcedimentoMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKPeriodicidade;
import ode.conhecimentoMedicao.cgt.AplCadastrarKProcedimentoAnaliseMedicao;
import ode.conhecimentoMedicao.cgt.AplCadastrarKProcedimentoMedicao;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarDefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cih.FormDadosDefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cih.ListagemDefinicaoOperacionalMedida;
import ode.medicao.planejamentoMedicao.cih.PainelCRUDDefinicaoOperacionalMedida;

@Controller(CtrlDefinicaoOperacionalMedida.NOME)
public class CtrlDefinicaoOperacionalMedida extends
		CtrlCRUD<DefinicaoOperacionalMedida> {

	public static final String NOME = "CtrlKDefinicaoOperacionalMedida";

	protected NucleoTab aba;
	protected CtrlKMedidaCRUD ctrlKMedida;

	@Autowired
	AplCadastrarDefinicaoOperacionalMedida apl;

	@Autowired
	AplCadastrarKProcedimentoMedicao aplKProcedimentoMedicao;

	@Autowired
	AplCadastrarKRecursoHumano aplKRecursoHumano;

	@Autowired
	AplCadastrarKAtividade aplKAtividade;

	@Autowired
	AplCadastrarKPeriodicidade aplKPeriodicidade;

	@Autowired
	AplCadastrarKProcedimentoAnaliseMedicao aplKProcedimentoAnaliseMedicao;

	@Autowired
	AplCadastrarObjetivoMedicao aplKObjetivoMedicao;

	public final String tituloJanelaDados = "Definição Operacional de Medida";

	public ListagemDefinicaoOperacionalMedida getListagemSimples() {
		return new ListagemDefinicaoOperacionalMedida();
	}

	@Override
	public void mostrarJanelaPrincipal() {
		Vbox vb = new Vbox();
		vb.appendChild(painelCRUD);
		aba.setConteudoTab(vb);

		atualizarPesquisa();
	};

	public void definirAba(NucleoTab tab) {
		aba = tab;
	}

	@Override
	public void atualizarPesquisa() {
		if (painelCRUD.getListagem().getObjetos() != null) {
			painelCRUD.getListagem().preencherLista();
		} else {
			painelCRUD.getListagem().setObjetos(
					new ArrayList<DefinicaoOperacionalMedida>());
		}
	}

	public void atualizarPesquisa(KMedida med) {
		Collection<DefinicaoOperacionalMedida> objetos = ((AplCadastrarDefinicaoOperacionalMedida) getAplCRUD())
				.recuperarRelacionadosMedida(med);
		painelCRUD.getListagem().atualizar(objetos);
	}

	@Override
	public String definirTituloJanelaDados() {
		return tituloJanelaDados;
	}

	@Override
	public AplCRUD<DefinicaoOperacionalMedida> definirAplCRUD() {
		return apl;
	}

	@Override
	public PainelCRUD<DefinicaoOperacionalMedida> definirPainelCRUD() {
		return new PainelCRUDDefinicaoOperacionalMedida();
	}

	@Override
	public String definirTituloJanelaPrincipal() {
		return "";
	}

	@Override
	public FormularioDadosCRUD<DefinicaoOperacionalMedida> definirFormularioCadastro() {
		return new FormDadosDefinicaoOperacionalMedida();
	}

	@Override
	public DefinicaoOperacionalMedida factoryObjetoDados() {
		return new DefinicaoOperacionalMedida();
	}

	public AplCadastrarKProcedimentoMedicao getAplKProcedimentoMedicao() {
		return aplKProcedimentoMedicao;
	}

	public AplCadastrarKRecursoHumano getAplKRecursoHumano() {
		return aplKRecursoHumano;
	}

	public AplCadastrarKAtividade getAplKAtividade() {
		return aplKAtividade;
	}

	public AplCadastrarKPeriodicidade getAplKPeriodicidade() {
		return aplKPeriodicidade;
	}

	public AplCadastrarKProcedimentoAnaliseMedicao getAplKProcedimentoAnaliseMedicao() {
		return aplKProcedimentoAnaliseMedicao;
	}

	@Override
	public void acaoSalvar() {
		try {
			// passo os dados do formulario para o objeto antes de pega-lo
			formularioDados.atualizarObjeto();
			DefinicaoOperacionalMedida objetoCadastro = formularioDados
					.getObjetoCadastroDados();
			Collection<DefinicaoOperacionalMedida> listaTemp = painelCRUD
					.getListagem().getObjetos();
			listaTemp.add(objetoCadastro);
			painelCRUD.getListagem().setObjetos(listaTemp);
			atualizarPesquisa();
			janDados.onClose();

		} catch (Exception e) {
			CtrlExcecoes.tratarExcecao(e);
		}
	}

	@Override
	protected void excluir(Set<Listitem> objetosSelecionados) {
		Collection<DefinicaoOperacionalMedida> listtemp = painelCRUD
				.getListagem().getObjetos();
		DefinicaoOperacionalMedida defItem;
		for (Listitem item : objetosSelecionados) {
			defItem = (DefinicaoOperacionalMedida) item.getValue();
			listtemp.remove(defItem);
		}
		painelCRUD.getListagem().setObjetos(listtemp);
		atualizarPesquisa();
	}

	public FormDadosDefinicaoOperacionalMedida getFormDados() {
		return (FormDadosDefinicaoOperacionalMedida) formularioDados;
	}

	public Collection<DefinicaoOperacionalMedida> getListagemKDefinicaoOperacional() {
		return painelCRUD.getListagem().getObjetos();
	}

	public AplCadastrarObjetivoMedicao getAplMedicao() {
		return aplKObjetivoMedicao;
	}
}
