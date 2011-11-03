package ode._controleProcesso.ciu;

import ode._infraestruturaCRUD.ciu.JanelaSimples;

import org.zkoss.zul.Label;

public class JanDefinicaoProcesso extends JanelaSimples {

	private static final long serialVersionUID = 1L;

	public JanDefinicaoProcesso(CtrlDefinicaoProcesso ctrl) {
		super();
		
		this.setTitle("Defini��o de Processos - " + ctrl.getProjeto().getNome());
		Label label = new Label("Teste");
		label.setParent(this);
	}
}
