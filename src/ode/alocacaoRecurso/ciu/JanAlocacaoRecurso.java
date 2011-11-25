package ode.alocacaoRecurso.ciu;

import java.util.ArrayList;
import java.util.List;

import ode._infraestruturaBase.ciu.NucleoTab;
import ode._infraestruturaBase.ciu.NucleoTabbox;
import ode._infraestruturaCRUD.ciu.JanelaSimples;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class JanAlocacaoRecurso extends JanelaSimples {

	private static final long serialVersionUID = 1L;

	private List<NucleoTab> listaTabs = new ArrayList<NucleoTab>();

	private NucleoTab tabDefinirEquipe = new NucleoTab("Definir Equipe");

	private NucleoTab tabAlocarRecurso = new NucleoTab("Alocar Recursos");
	
	private PainelDefinirEquipe painelDefinirEquipe;
	
	private PainelAlocarRecursos painelAlocarRH;
	
	private final CtrlAlocacaoRecurso ctrl;

	public JanAlocacaoRecurso(final CtrlAlocacaoRecurso ctrl) {
		super();
		
		this.ctrl = ctrl;
		
		this.setTitle("Alocação de Recursos Humanos - " + ctrl.getProjeto().getNome());
		this.setHflex("min");

		if(ctrl.existeEquipeDefinida()) {
			tabAlocarRecurso.getTab().setSelected(true);	
		}
		
		criarTabDefinirEquipe();
		listaTabs.add(tabDefinirEquipe);
				
		criarTabAlocarRecurso();
		listaTabs.add(tabAlocarRecurso);

		tabAlocarRecurso.getTab().addEventListener("onSelect", new EventListener(){
			public void onEvent(Event arg0) throws Exception {
				criarTabAlocarRecurso();
			}
		});
		
		tabDefinirEquipe.getTab().addEventListener("onSelect", new EventListener(){
			public void onEvent(Event arg0) throws Exception {
				criarTabDefinirEquipe();
			}
		});
		
		NucleoTabbox tabbox = new NucleoTabbox(listaTabs);
		tabbox.setParent(this);
	}

	public PainelDefinirEquipe getPainelDefinirEquipe() {
		return painelDefinirEquipe;
	}

	public PainelAlocarRecursos getPainelAlocarRH() {
		return painelAlocarRH;
	}

	public void criarTabAlocarRecurso() {
		tabAlocarRecurso.getConteudo().clear();
		painelAlocarRH = new PainelAlocarRecursos(ctrl);
		tabAlocarRecurso.setConteudoTab(painelAlocarRH);
	}

	public void criarTabDefinirEquipe() {
		tabDefinirEquipe.getConteudo().clear();
		painelDefinirEquipe = new PainelDefinirEquipe(ctrl);
		tabDefinirEquipe.setConteudoTab(painelDefinirEquipe);
	}
}
