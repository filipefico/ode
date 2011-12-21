package ode.medicao.analiseMedicao.ciu;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Vbox;

import ode._infraestruturaBase.ciu.CtrlBase;
import ode._infraestruturaBase.ciu.NucleoCombobox;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode._infraestruturaCRUD.ciu.NucleoListbox;
import ode.medicao.analiseMedicao.cdp.AcaoCorretiva;
import ode.medicao.analiseMedicao.cdp.AnaliseMedicao;
import ode.medicao.analiseMedicao.cdp.GrauSatisfacao;
import ode.medicao.analiseMedicao.cdp.MonitoramentoObjetivo;

@Controller
public class CtrlAcaoCorretiva extends CtrlBase{

	JanelaSimples janela;
	
	Listitem acaoAnalise;
	NucleoCombobox<AnaliseMedicao> cbAnalise;
	Listitem acaoMonitoramento;
	NucleoCombobox<MonitoramentoObjetivo> cbMonitoramentos;
	Listbox tipoAcao;
	Set<AnaliseMedicao> analises;
	Set<MonitoramentoObjetivo> monitoramentos;
	{
		analises = new HashSet<AnaliseMedicao>();
		analises.add(new AnaliseMedicao("Ricardo de Almeida Falbo - 10/12/2011"));
		analises.add(new AnaliseMedicao("Ricardo de Almeida Falbo - 15/12/2011"));
		
		monitoramentos = new HashSet<MonitoramentoObjetivo>();
		monitoramentos.add(new MonitoramentoObjetivo("Ricardo de Almeida Falbo - 12/11/2011"));
		monitoramentos.add(new MonitoramentoObjetivo("Ricardo de Almeida Falbo - 10/12/2011"));
	}
	
	NucleoListbox<AcaoCorretiva> acoes;
	NucleoListbox<GrauSatisfacao> cbGraus;
	Textbox descricao;
	Textbox consideracoes;

	private Set<AcaoCorretiva> acoesConjuntas;
	{
		acoesConjuntas = new HashSet<AcaoCorretiva>();
		acoesConjuntas.add(new AcaoCorretiva("AC1"));
		acoesConjuntas.add(new AcaoCorretiva("AC2"));
		acoesConjuntas.add(new AcaoCorretiva("AC3"));
	}
	
	@Override
	public void iniciar() {
		janela = factoryJanelaSimples();
		janela.setTitle("Ação Corretiva");
		
		tipoAcao = new Listbox();
		tipoAcao.setCheckmark(true);
		janela.appendChild(tipoAcao);
		
		acaoAnalise = new Listitem();
		acaoAnalise.appendChild(new Listcell("Baseado em Analise"));
		Listcell lc = new Listcell();
		lc.appendChild(cbAnalise = new NucleoCombobox<AnaliseMedicao>());
		cbAnalise.setObjetos(analises);
		acaoAnalise.appendChild(lc);
		acaoAnalise.setParent(tipoAcao);

		acaoMonitoramento = new Listitem();
		acaoMonitoramento.appendChild(new Listcell("Baseado em Monitoramento"));
		lc = new Listcell();
		lc.appendChild(cbMonitoramentos = new NucleoCombobox<MonitoramentoObjetivo>());
		cbMonitoramentos.setObjetos(monitoramentos);
		acaoMonitoramento.appendChild(lc);
		acaoMonitoramento.setParent(tipoAcao);
		
		//////////////////////////////////////////////////////
		acoes = new NucleoListbox<AcaoCorretiva>();
		Listhead lh1;
		acoes.appendChild(lh1 = new Listhead());
		lh1.appendChild(new Listheader("Acões Corretivas"));
		acoes.setObjetos(acoesConjuntas);
		acoes.setParent(janela);
		
		Toolbar tb = new Toolbar();
		tb.setStyle("border:0px;background:white;");
		tb.setAlign("end");
		Button novo = new Button("Novo");
		Button abrir = new Button("Abrir");
		Button deletar = new Button("Deletar");
		novo.setParent(tb);
		abrir.setParent(tb);
		deletar.setParent(tb);
		tb.setParent(janela);
		
		Groupbox gb = new Groupbox();
		Caption gambi = new Caption();
		gb.setParent(janela);
		gb.appendChild(gambi);
		gambi.appendChild(new Checkbox());
		gambi.appendChild(new Label("Resultados Obtidos"));
		
		Hbox hResultado = new Hbox();
		hResultado.setParent(gb);
		hResultado.appendChild(new Label("Resultado"));
		cbGraus = new NucleoListbox<GrauSatisfacao>();
		cbGraus.setParent(hResultado);
		cbGraus.setCheckmark(true);
		cbGraus.setObjetos(GrauSatisfacao.values());
		cbGraus.selecionarPrimeiroElemento();
		
		Vbox vResto = new Vbox();
		vResto.setWidth("100%");
		vResto.setParent(gb);
		Label lbDescricao = new Label("Descrição dos Resultados");
		lbDescricao.setParent(vResto);
		descricao = new Textbox();
		descricao.setRows(3);
		descricao.setHflex("1");
		descricao.setParent(vResto);
		Label lbConsideracoes = new Label("Considerações Gerais");
		lbConsideracoes.setParent(vResto);
		consideracoes = new Textbox();
		consideracoes.setRows(3);
		consideracoes.setHflex("1");
		consideracoes.setParent(vResto);
		
		
		janela.mostrar();
	}

}
