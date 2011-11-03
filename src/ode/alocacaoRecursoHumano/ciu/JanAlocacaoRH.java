package ode.alocacaoRecursoHumano.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaCRUD.ciu.JanelaSimples;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class JanAlocacaoRH extends JanelaSimples {

	private static final long serialVersionUID = 1L;

	private List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

	private NucleoTab tabDefinirEquipe = new NucleoTab("Definir Equipe");

	private NucleoTab tabAlocarRecurso = new NucleoTab("Alocar Recursos");

	public JanAlocacaoRH(final CtrlAlocacaoRecursoHumano ctrl) {
		super();
		
		this.setTitle("Alocação de Recursos Humanos - " + ctrl.getProjeto().getNome());
		this.setHflex("min");

		PainelDefinirEquipe painelDefinirEquipe = new PainelDefinirEquipe(ctrl);
		tabDefinirEquipe.setConteudoTab(painelDefinirEquipe);
		listaTabs.add(tabDefinirEquipe);

		tabAlocarRecurso.getTab().addEventListener("onSelect", new EventListener(){
			public void onEvent(Event arg0) throws Exception {
				PainelAlocarRecursos painelAlocarRH = new PainelAlocarRecursos(ctrl);
				tabAlocarRecurso.setConteudoTab(painelAlocarRH);
			}
		});

		listaTabs.add(tabAlocarRecurso);

		NucleoTabbox tabbox = new NucleoTabbox(listaTabs);
		tabbox.setParent(this);
	}
}
