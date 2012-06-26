package ode.resolucaoProblema.ciu;

import ode._infraestruturaCRUD.ciu.ListagemSimples;
import ode._infraestruturaCRUD.ciu.PainelCRUD;
import ode.resolucaoProblema.cdp.OcorrenciaProblema;

public class PainelOcorrenciaProblema extends PainelCRUD<OcorrenciaProblema> {

	
	private static final long serialVersionUID = -142460127176212550L;

	@Override
	public ListagemSimples<OcorrenciaProblema> definirListagem() {
		// TODO Auto-generated method stub
		return new ListagemOcorrenciaProblema();
	}

}
