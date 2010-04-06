package nucleo.comuns.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe que possi utilidades para manipulação de cores.
 * 
 * @author Alexandre G. N. Coelho
 * 
 */
public class Cor {

	public static final String COR_VERMELHO = NucleoMensagens.TERMO_VERMELHO;

	public static final String COR_AMARELO = NucleoMensagens.TERMO_AMARELO;

	public static final String COR_VERDE = NucleoMensagens.TERMO_VERDE;

	public static final String COR_AZUL = NucleoMensagens.TERMO_AZUL;

	/**
	 * Retorna as cores disponíveis
	 * 
	 * @return Coleção de cores.
	 */
	public static Collection<String> recuperarCoresDisponiveis() {
		Collection<String> cores = new ArrayList<String>();

		cores.add(COR_VERMELHO);
		cores.add(COR_AMARELO);
		cores.add(COR_VERDE);
		cores.add(COR_AZUL);

		return cores;
	}
	
	/**
	 * Recupera a cor de texto (CSS) de acordo com a cor passada.
	 * 
	 * @param cor
	 *            Cor.
	 * @return Cor de texto.
	 */
	public static String recuperarCorTexto(String cor) {

		if (COR_VERMELHO.equals(cor)) {
			return "color:red;";
		}
		if (COR_AZUL.equals(cor)) {
			return "color:blue;";
		}
		if (COR_AMARELO.equals(cor)) {
			return "color:yellow;";
		}
		if (COR_VERDE.equals(cor)) {
			return "color:green;";
		}

		return "color:black;";
	}
	
	/**
	 * Recupera a cor de fundo (CSS) de acordo com a cor passada.
	 * 
	 * @param cor
	 *            Cor.
	 * @return Cor de fundo.
	 */
	public static String recuperarCorFundo(String cor) {

		if (COR_VERMELHO.equals(cor)) {
			return "background-color:red;";
		}
		if (COR_AZUL.equals(cor)) {
			return "background-color:blue;";
		}
		if (COR_AMARELO.equals(cor)) {
			return "background-color:yellow;";
		}
		if (COR_VERDE.equals(cor)) {
			return "background-color:green;";
		}

		return "color:black;";
	}
	
	/**
	 * Recupera o objeto Color de acordo com a cor passada.
	 * 
	 * @param cor
	 *            Cor.
	 * @return Objeto Color.
	 */
	public static Color recuperarColor(String cor) {

		if (COR_VERMELHO.equals(cor)) {
			return new Color( 255 ,  0 ,  0 ,  128 );
		}
		if (COR_AZUL.equals(cor)) {
			return new Color( 0 ,  0 ,  255,  64);
		}
		if (COR_AMARELO.equals(cor)) {
			return new  Color( 255 ,  255 ,  0 ,  64 );
		}
		if (COR_VERDE.equals(cor)) {
			return new  Color( 0 ,  255 ,  0 ,  64 );
		}
	
		return new  Color( 0 ,  0 ,  0 ,  64 );
	}

}
