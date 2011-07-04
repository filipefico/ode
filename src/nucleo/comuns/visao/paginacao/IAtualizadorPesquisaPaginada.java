package nucleo.comuns.visao.paginacao;

import nucleo.comuns.persistencia.ObjetoPagina;

public interface IAtualizadorPesquisaPaginada {
	/**
	 * M�todo respons�vel por atulizar a pesquisa paginada, da classe ListagemPaginada
	 * */
	public void atualizarPesquisa(ObjetoPagina pagina);
	public void atualizarPesquisa(); 

}
