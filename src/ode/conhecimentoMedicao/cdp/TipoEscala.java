package ode.conhecimentoMedicao.cdp;

public enum TipoEscala {
	EscalaNominal{
		public String toString(){
			return "Escala Nominal";
		}
	},
	EscalaIntervalar{
		public String toString(){
			return "Escala Intervalar";
		}
	}, EscalaOrdinal{
		public String toString(){
			return "Escala Ordinal";
		}
	}, EscalaAbsoluta{
		public String toString(){
			return "Escala Absoluta";
		}
	}, EscalaTaxa{
		public String toString(){
			return "Escala de Taxa";
		}
	};
}
