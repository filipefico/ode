package ode.resolucaoProblema.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import org.zkoss.zul.Label;
import ode.problema.cdp.KCriterioSelecaoSolucao;

public class JanelaAvaliarSolucoesComCriterio2 extends JanelaSimples {

	private static final long serialVersionUID = 4941701754499895809L;
	
	Window referencia = this;
	Vlayout painel = new Vlayout();
	private Collection<KCriterioSelecaoSolucao> listaKCriterioSelecaoSolucao;
	CtrlOcorrenciaProblema ctrl2;
	CtrlAvaliarSolucoesComCriterio ctrl = SpringUtil.getApplicationContext()
			.getBean(CtrlAvaliarSolucoesComCriterio.class);
	private Listbox janela = new Listbox();
	GridDados grid = new GridDados();
	private Button buttonSalvar = new Button();
	public List<Combobox> listavalores = new ArrayList<Combobox>();
	public List<Textbox> listapesos;
	public Listcell cell;

	public JanelaAvaliarSolucoesComCriterio2(List<Textbox> listapesos, Listcell cell) {
		this.listapesos = listapesos;
		this.cell = cell;

	}

	public void mostrar() {
		this.setTitle("Avaliar Critérios");
		this.setHflex("min");
		janela.setParent(this);

		Listhead cabecalho = new Listhead();
		cabecalho.setParent(janela);

		Listheader criterio = new Listheader();
		criterio.setParent(cabecalho);

		Listheader dropbox = new Listheader();
		dropbox.setParent(cabecalho);

		dropbox.setLabel("");
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

				Listcell listavalor = new Listcell();
				listavalor.setParent(lista);
				Combobox combo = new Combobox();
				combo.setParent(listavalor);

				Comboitem item = new Comboitem();
				item.setLabel("Alto");
				item.setValue(3);
				item.setParent(combo);

				Comboitem item2 = new Comboitem();
				item2.setLabel("Médio");
				item2.setValue(2);
				item2.setParent(combo);

				Comboitem item3 = new Comboitem();
				item3.setLabel("Baixo");
				item3.setValue(1);
				item3.setParent(combo);
				listavalores.add(combo);

				super.mostrar();
			}

		}

		Textbox descricao = new Textbox();
		descricao.setWidth("750px");
		descricao.setHeight("100px");
		descricao.setMultiline(true);
		Label l = new Label("Descrição:   ");
		l.setParent(this);
		descricao.setParent(this);
		buttonSalvar.setLabel("Salvar");
		buttonSalvar.setParent(this);
		buttonSalvar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				cell.setLabel(Integer.toString(Multiplica(listavalores, listapesos)));
				referencia.onClose();
			}
		});

	}

	public int Multiplica(List<Combobox> listavalores,
			List<Textbox> listapesos) {
		int resultado = 0;
		for (int i = 0; i < listavalores.size(); i++) {
			int valor = (Integer)listavalores.get(i).getSelectedItem().getValue();
			int peso = Integer.parseInt(((Textbox) listapesos.get(i)).getValue());
			resultado += peso * valor;
		}

		return resultado;
	}

}
