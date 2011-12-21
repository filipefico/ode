package ode.medicao.planejamentoMedicao.cih;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Textbox;

import ode.conhecimento.processo.cdp.KProcesso;
import ode.conhecimento.processo.cgt.AplCadastrarKProcesso;
import ode.medicao.planejamentoMedicao.cci.CtrlNecessidadeInformacaoCRUD;
import ode.medicao.planejamentoMedicao.cdp.NecessidadeInformacao;
import ode.medicao.planejamentoMedicao.cdp.ObjetivoMedicao;
import ode.medicao.planejamentoMedicao.cgt.AplCadastrarObjetivoMedicao;
import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaCRUD.ciu.FormularioDadosCRUD;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.NucleoMultipleListBox;
import ode._infraestruturaBase.excecao.NucleoRegraNegocioExcecao;
import ode._infraestruturaBase.util.NucleoMensagens;

public class FormDadosNecessidadeInformacao extends  FormularioDadosCRUD<NecessidadeInformacao>{

	private Textbox tbNome = new Textbox();
	private Textbox tbDescricao = new Textbox();
	private NucleoMultipleListBox<ObjetivoMedicao> ckMed = new NucleoMultipleListBox<ObjetivoMedicao>();
	private NucleoMultipleListBox<KProcesso> ckProc = new NucleoMultipleListBox<KProcesso>();
	private Groupbox gbMedicao = new Groupbox();
	private Groupbox gbProc = new Groupbox();
	GridDados gridDadosCadastro;
	
	protected List<NucleoTab> definirTabs() {
		// Cria a nova lista
				List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

				// ////////////////////////////
				// Dados Cadastro - Objetivos Medicao Relacionados
				// ////////////////////////////
				
				CtrlNecessidadeInformacaoCRUD ctrl = (CtrlNecessidadeInformacaoCRUD) this.getControlador();
				AplCadastrarObjetivoMedicao aplMed =  ctrl.getAplKObjetivoMedicao();
				Collection<ObjetivoMedicao> medicoes = aplMed.recuperarTodos();
				
				ckMed.setObjetos(medicoes);
				gbMedicao.appendChild(new Caption(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_OBJETIVOS_MEDICAO)));
				gbMedicao.appendChild(ckMed);
				
				// ////////////////////////////
				// Dados Cadastro - Processo
				// ////////////////////////////
				
				AplCadastrarKProcesso aplPrc =  ctrl.getAplKProcesso();
				Collection<KProcesso> processos = aplPrc.recuperarTodos();
				
				ckProc.setObjetos(processos);
				gbProc.appendChild(new Caption(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_PROCESSO)));
				gbProc.appendChild(ckProc);
				
				////////////////
				
				// ////////////////////////////
				// Dados Cadastro - Nome e Descricao
				// ////////////////////////////
				NucleoTab tabDadosCadastro = new NucleoTab();

				// Atribui o nome à tab
				tabDadosCadastro.setNomeTab(NucleoMensagens.getMensagem(NucleoMensagens.TERMO_DADOS_CADASTRO));

				// Atribui o conteúdo à tab
				gridDadosCadastro = new GridDados();
				tbNome.setWidth("385px");
				tbNome.setMaxlength(300);	
				tbNome.setHeight("35px");
				tbNome.setMultiline(true);
				gridDadosCadastro.adicionarLinhaObrigatoria(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_NOME),tbNome);
				
				tbDescricao.setWidth("385px");
				tbDescricao.setMaxlength(400);
				tbDescricao.setHeight("65px");
				tbDescricao.setMultiline(true);
				gridDadosCadastro.adicionarLinha(NucleoMensagens
						.getMensagem(NucleoMensagens.TERMO_DESCRICAO),tbDescricao);
				
				tabDadosCadastro.setConteudoTab(gridDadosCadastro);

				return listaTabs;
	}
	
	@Override
	protected void montarTabs() {
		super.montarTabs();
		this.appendChild(gbMedicao);
		this.appendChild(gbProc);
		this.appendChild(gridDadosCadastro);
	};

	@Override
	protected void preencherDadosTela(NecessidadeInformacao objeto)
			throws NucleoRegraNegocioExcecao {
		tbNome.setText(objeto.getNome());
		tbDescricao.setText(objeto.getDescricao());
		ckMed.setObjetosSelecionados(objeto.getObjetivosMedicao());
		ckProc.setObjetosSelecionados(objeto.getProcessos());
	}

	@Override
	protected void preencherDadosObjeto(NecessidadeInformacao objeto) {
		objeto.setNome(tbNome.getText());
		objeto.setDescricao(tbDescricao.getText());
		objeto.setObjetivosMedicao(ckMed.getObjetosSelecionados());
		objeto.setProcessos(ckProc.getObjetosSelecionados());
	}
	
	@Override
	protected void configurarConstraints() {
		tbNome.setConstraint("no empty");		
		tbDescricao.setConstraint("no empty");
	}


}
