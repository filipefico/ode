package ode.medicao.EntidadeMensuravel.cdp;


public enum TipoEntidadeMensuravel{
	PROJETO{
		public String toString(){
			return "Projeto";
		}
	},
	ATIVIDADE{
		public String toString(){
			return "Atividade";
		}
	},
	ARTEFATO{
		public String toString(){
			return "Artefato";
		}
	},
	RECURSOHUMANO{
		public String toString(){
			return "Recurso Humano";
		}
	},
	PROCESSOESPECIFICOPROJETO{
		public String toString(){
			return "Processo Espec�fico de Projeto";
		}
	},
	PROCESSOPADRAO{
		public String toString(){
			return "Processo Padr�o";
		}
	}
}
