package ode.agenda.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;

public class JanAgenda  extends JanelaSimples {
	
	private static final long serialVersionUID = 1L;

	public JanAgenda(CtrlAgenda ctrl) {
		super();
		
		this.setTitle("Agenda - " + ctrl.getRecursoHumano().getNome());
		this.setHflex("min");
		
		PainelAlocacoesAtividades painel = new PainelAlocacoesAtividades(ctrl);
		painel.setParent(this);
	}
	
}
