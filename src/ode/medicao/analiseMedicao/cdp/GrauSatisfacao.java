package ode.medicao.analiseMedicao.cdp;

public enum GrauSatisfacao {
	MUITOSATISFATORIO{
		public String toString(){
			return "Muito Satisfat�rio";
		}
	},
	SATISFATORIO{
		public String toString(){
			return "Satisfat�rio";
		}
	},
	REGULAR{
		public String toString(){
			return "Regular";
		}
	},
	INSATISFATORIO{
		public String toString(){
			return "Insatisfat�rio";
		}
	}
}
