
package ode.controleCaracteristica.cdp;

public enum EnumTipoItemSoftware {
	PROJETO("Projeto"),
	MODULO("M�dulo"),
	PROCESSO_PADRAO("Processo Padr�o");
	
	private String label;

	private EnumTipoItemSoftware(String label) {
		this.label = label;
	}

	public String toString() {
		return this.label;
	}
}
