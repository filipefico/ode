package nucleo.comuns.visao.paginacao;

import nucleo.comuns.visao.NucleoWindow;

public class JanelaSimples extends NucleoWindow{

	@Override
	protected String getTituloWindow() {
		
		return "Titulo Simples";
	}

	@Override
	public void onCreateWindow() {
		
	}

	public void mostrar() {		
		doOverlapped();
						
	}

}
