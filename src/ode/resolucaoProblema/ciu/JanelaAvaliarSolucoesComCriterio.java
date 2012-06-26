package ode.resolucaoProblema.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.problema.cdp.KCriterioSelecaoSolucao;
import ode.problema.cdp.KSolucao;

public class JanelaAvaliarSolucoesComCriterio extends JanelaSimples {

	private static final long serialVersionUID = -1495686623433761018L;
	private Collection<KCriterioSelecaoSolucao> listaKCriterioSelecaoSolucao;

	CtrlAvaliarSolucoesComCriterio ctrl = SpringUtil.getApplicationContext()
			.getBean(CtrlAvaliarSolucoesComCriterio.class);

	private Listbox janela = new Listbox();
	private Listbox janela2 = new Listbox();
	public int linha;
	public List<Textbox> listapesos = new ArrayList<Textbox>();
	public List<Listcell> listaresultado = new ArrayList<Listcell>();
	Hashtable<Button, Listcell> hashButtonListcell;

	public JanelaAvaliarSolucoesComCriterio(List<Integer> resultado) {
		super();

	}

	public JanelaAvaliarSolucoesComCriterio() {
		super();

	}

	public void mostrar(Set<Listitem> listasolucao3) {
		this.setTitle("Avaliar Soluções com Critérios");
		this.setHflex("min");
		Hlayout painel = new Hlayout();
		Hlayout painel2 = new Hlayout();
		janela.setParent(this);
		janela2.setParent(this);
		painel.setParent(this);
		painel.setSclass("z-valign-top");

		Listhead cabecalho = new Listhead();
		cabecalho.setParent(janela);

		Listheader criterio = new Listheader();
		criterio.setParent(cabecalho);

		Listheader peso = new Listheader();
		peso.setParent(cabecalho);

		peso.setLabel("Peso");
		criterio.setLabel("Critérios");

		listaKCriterioSelecaoSolucao = ctrl.aplCadastrarKCriterioSelecaoSolucao
				.recuperarTodos();

		if (listaKCriterioSelecaoSolucao.size() > 0) {

			for (KCriterioSelecaoSolucao criterios : listaKCriterioSelecaoSolucao) {
				Listitem lista = new Listitem();
				lista.setParent(janela);
				String nome = criterios.getNome();
				Listcell listaCriterios = new Listcell();
				listaCriterios.setLabel(nome);
				listaCriterios.setParent(lista);

				Listcell listaPeso = new Listcell();
				Textbox inteiro = new Textbox();
				inteiro.setParent(listaPeso);
				listaPeso.setParent(lista);

				listapesos.add(inteiro);

			}

		} else {
			Label label = new Label("Não existe Ocorrência Cadastrada.");
			label.setParent(painel);

		}

		painel2.setParent(this);

		Listhead cabecalho2 = new Listhead();
		cabecalho2.setParent(janela2);

		Listheader solucao = new Listheader();
		solucao.setParent(cabecalho2);

		Listheader resultadoavaliacao = new Listheader();
		resultadoavaliacao.setParent(cabecalho2);

		Listheader botaoavaliar = new Listheader();
		botaoavaliar.setParent(cabecalho2);

		solucao.setLabel("Soluções");
		resultadoavaliacao.setLabel("Resultado da Avaliação");
		botaoavaliar.setLabel("");

		if (listasolucao3.size() > 0) {
			hashButtonListcell = new Hashtable<Button, Listcell>();

			for (Listitem lisolucoes : listasolucao3) {

				KSolucao solucoes = (KSolucao) (lisolucoes.getValue());
				Listitem lista2 = new Listitem();
				lista2.setParent(janela2);
				String nome = solucoes.getNome();
				Listcell listaSolucoes = new Listcell();
				listaSolucoes.setLabel(nome);
				listaSolucoes.setParent(lista2);

				Listcell cellResultado = new Listcell();
				cellResultado.setLabel("0");
				cellResultado.setParent(lista2);
				listaresultado.add(cellResultado);

				Listcell cellButton = new Listcell();
				cellButton.setParent(lista2);

				Button avaliar = new Button("Avaliar");
				avaliar.setParent(cellButton);
				hashButtonListcell.put(avaliar, cellResultado);
				avaliar.addEventListener("onClick", new EventListener() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						Button button = (Button) arg0.getTarget();
						JanelaAvaliarSolucoesComCriterio2 janelaAvaliarSolucao = new JanelaAvaliarSolucoesComCriterio2(
								listapesos, hashButtonListcell.get(button));
						janelaAvaliarSolucao.mostrar();
					}
				});

			}

		} else {
			Label label = new Label("Não existe Ocorrência Cadastrada.");
			label.setParent(painel);
		}

		super.mostrar();
	}
}
