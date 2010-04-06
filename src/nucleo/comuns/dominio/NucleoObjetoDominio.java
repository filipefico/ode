package nucleo.comuns.dominio;

import java.io.Serializable;

/**
 * Interface (opcional) para objetos de dom�nio que implementa equals()
 * e hashCode() utilizando um identificador universal �nico (UUID).
 * 
 * </p><p>
 * � bastante comum termos objetos de dom�nio em cole��es como HashSet, que
 * assumem que os m�todos equals() e hashCode() foram implementados de forma
 * que todas as inst�ncias que representem o mesmo objeto de dom�nio retornem
 * o mesmo valor em hashCode() e s�o iguais de acordo com o m�todo equals().
 * 
 * </p><p>
 * Diversas implementa��es de equals() e hashCode() j� foram sugeridas:
 * utiliza��o de um conjunto de propriedades do objeto que, de acordo com as
 * regras de neg�cio, identificam univocamente o objeto; utiliza��o do 
 * identificador da persist�ncia; etc. Cada id�ia possui suas vantagens e
 * desvantagens.
 * 
 * </p><p>
 * A solu��o proposta nesta classe � a utiliza��o de um identificador �nico e
 * universal (UUID - Universal Unique Identifier). Esta interface prov� m�todos
 * para obter e alterar o UUID. H� uma implementacao padr�o para esta interface
 * na classe NucleoObjetoDominioImpl.
 * 
 * @see nucleo.comuns.dominio.NucleoObjetoDominioImpl
 * @author Vitor Souza
 */
public interface NucleoObjetoDominio extends Serializable {
	/**
	 * Retorna o identificador universal �nico.
	 * 
	 * @return O UUID.
	 */
	String getUuid();
}
