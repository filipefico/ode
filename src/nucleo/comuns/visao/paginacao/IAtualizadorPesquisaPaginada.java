package nucleo.comuns.visao.paginacao;

import ode.nucleo.cgd.ObjetoPagina;

public interface IAtualizadorPesquisaPaginada {
	/**
	 * Método responsável por atulizar a pesquisa paginada, da classe ListagemPaginada
	 * */
	public void atualizarPesquisa(ObjetoPagina pagina);
	public void atualizarPesquisa(); 

}
