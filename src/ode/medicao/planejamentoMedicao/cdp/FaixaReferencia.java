package ode.medicao.planejamentoMedicao.cdp;

public enum FaixaReferencia {
	BOM {
		public String toString() {
			return "Bom";
		}
	},
	REGULAR {
		public String toString() {
			return "Regular";
		}
	},
	RUIM {
		public String toString() {
			return "Ruim";
		}
	}
}
