package ode.medicao.analiseMedicao.cdp;

public enum GrauSatisfacao {
	MUITOSATISFATORIO{
		public String toString(){
			return "Muito Satisfatório";
		}
	},
	SATISFATORIO{
		public String toString(){
			return "Satisfatório";
		}
	},
	REGULAR{
		public String toString(){
			return "Regular";
		}
	},
	INSATISFATORIO{
		public String toString(){
			return "Insatisfatório";
		}
	}
}
