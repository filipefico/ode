package ode.medicao.execucaoMedicao.ciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import ode._controleRecursoHumano.cdp.RecursoHumano;
import ode._controleRecursoHumano.cgt.AplCadastrarRecursoHumano;
import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.conhecimento.processo.cdp.KAtividade;
import ode.conhecimento.processo.cgt.AplCadastrarKAtividade;
import ode.conhecimentoMedicao.cdp.KMedida;
import ode.conhecimentoMedicao.cgt.AplCadastrarKMedida;
import ode.controleProjeto.cdp.Projeto;
import ode.controleProjeto.cgt.AplCadastrarProjeto;
import ode.medicao.planejamentoMedicao.cdp.DefinicaoOperacionalMedida;

@Controller
public class CtrlMedicao extends CtrlBase{

	JanelaSimples js;
	
	NucleoCombobox<KMedida> cbKMedida;
	NucleoCombobox<RecursoHumano> cbRecursoHumano ;
	NucleoCombobox<DefinicaoOperacionalMedida> cbDefinicao ;
	Textbox decbox ;
	Datebox datebox ;
	NucleoCombobox<Projeto> cbProjeto ;
	NucleoCombobox<KAtividade> cbAtividade;
	Textbox tbContexto = new Textbox();
	
	
	private class OnMedidaSelecionada implements EventListener{

		@Override
		public void onEvent(Event arg0) throws Exception {
			cbDefinicao.getItems().clear();
			cbDefinicao.setObjetos(cbKMedida.getObjetoSelecionado().getDefinicoesMedida());
			cbDefinicao.selecionarPrimeiroElemento();
		}
		
	}
	
	@Autowired
	AplCadastrarRecursoHumano aplRH;
	@Autowired
	AplCadastrarKMedida aplKmedida;
	@Autowired
	AplCadastrarProjeto aplProjeto;
	@Autowired
	AplCadastrarKAtividade aplAtividade;
	
	@Override
	public void iniciar() {
		cbKMedida = new NucleoCombobox<KMedida>();
		cbKMedida.setWidth("100%");
		cbRecursoHumano = new NucleoCombobox<RecursoHumano>();
		cbRecursoHumano.setWidth("100%");
		cbDefinicao = new NucleoCombobox<DefinicaoOperacionalMedida>();
		cbDefinicao.setWidth("100%");
		decbox = new Textbox();
		decbox.setWidth("100%");
		datebox = new Datebox();
		cbProjeto = new NucleoCombobox<Projeto>();
		cbProjeto.setWidth("100%");
		cbAtividade = new NucleoCombobox<KAtividade>();
		cbAtividade.setWidth("100%");
		tbContexto = new Textbox();
		tbContexto.setWidth("100%");
		
		
		
		js = factoryJanelaSimples();
		js.setTitle("Coleta de Dados");
		GridDados gd = new GridDados();
		
		cbKMedida.setObjetos(aplKmedida.recuperarTodos());
		gd.adicionarLinha("Medida", cbKMedida);
		cbKMedida.addEventListener("onSelect", new OnMedidaSelecionada());
		cbKMedida.selecionarPrimeiroElemento();
		
		gd.adicionarLinha("Definição Operacional", cbDefinicao);
		cbDefinicao.setObjetos(cbKMedida.getObjetoSelecionado().getDefinicoesMedida());
		cbDefinicao.selecionarPrimeiroElemento();
		
		gd.adicionarLinha("Valor Medido", decbox);
		
		gd.adicionarLinha("Data da Medição", datebox);
		
		cbProjeto.setObjetos(aplProjeto.recuperarTodos());
		gd.adicionarLinha("Projeto", cbProjeto);
		
		cbRecursoHumano.setObjetos(aplRH.recuperarTodos());
		gd.adicionarLinha("Executor da Medição", cbRecursoHumano);
		
		cbAtividade.setObjetos(aplAtividade.recuperarTodos());
		gd.adicionarLinha("Momento da Medição", cbAtividade);
		
		Vbox vbox = new Vbox();
		vbox.appendChild(new Label("Contexto da Medição"));
		tbContexto.setParent(vbox);
		gd.adicionarLinhaUnica(vbox);
		tbContexto.setRows(4);
		tbContexto.setHflex("1");
		vbox.setHflex("1");
		
		Button salvar = new Button("Salvar");
		Toolbar tb = new Toolbar();
		salvar.setParent(tb);
		tb.setStyle("border:0px;background:white;");
		tb.setAlign("end");
		
		salvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				try {
					Messagebox mbox = new Messagebox();
					mbox.show("Medição salva");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		gd.setParent(js);
		tb.setParent(js);
		
		js.mostrar();
	}

}
