
package ode.controleCaracteristica.cdp;

public enum EnumTipoItemSoftware {
	PROJETO("Projeto"),
	MODULO("Módulo"),
	PROCESSO_PADRAO("Processo Padrão");
	
	private String label;

	private EnumTipoItemSoftware(String label) {
		this.label = label;
	}

	public String toString() {
		return this.label;
	}
}
