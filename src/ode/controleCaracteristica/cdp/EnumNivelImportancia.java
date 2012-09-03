package ode.controleCaracteristica.cdp;

public enum EnumNivelImportancia {

		IRRELEVANTE("Irrelevante"),
		POUCO_IMPORTANTE("Pouco importante"),
		IMPORTANTE("Importante"),
		MUITO_IMPORTANTE("Muito importante"),
		IMPRESCINDIVEL("Imprescindivel");
				
		private String label;

		private EnumNivelImportancia(String label) {
			this.label = label;
		}

		public String toString() {
			return this.label;
		}
}
