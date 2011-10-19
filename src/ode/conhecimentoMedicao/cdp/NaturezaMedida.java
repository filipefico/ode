package ode.conhecimentoMedicao.cdp;

public enum NaturezaMedida {
	BASE{
		public String toString(){
			return "Base";
		}
	},
	DERIVADA{
		public String toString(){
			return "Derivada";
		}
	};
}
