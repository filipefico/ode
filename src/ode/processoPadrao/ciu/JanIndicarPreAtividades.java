package ode.processoPadrao.ciu;

public class JanIndicarPreAtividades extends JanCore {

	public JanIndicarPreAtividades(
			CtrlDefinirProcessoPadrao ctrlDefinirProcessoPadrao) {

		super(ctrlDefinirProcessoPadrao);
		conteudo();
		this.mostrar();
	}

	private void conteudo() {
		this.setTitle("Pré-Atividades");

	}

}
