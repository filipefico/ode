package ode.processoPadrao.ciu;

public class JanDefinirDependencias extends JanCore {
	public JanDefinirDependencias(CtrlDefinirProcessoPadrao ctrl) {
		super(ctrl);
		conteudo();
		mostrar();
	}

	private void conteudo() {
		this.setTitle("Definir Dependencias");
	}

	private static final long serialVersionUID = -4206190222266107301L;

}
