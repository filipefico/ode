package nucleo.comuns.dominio;

import java.io.Serializable;

/**
 * Interface (opcional) para objetos de domínio que implementa equals()
 * e hashCode() utilizando um identificador universal único (UUID).
 * 
 * </p><p>
 * É bastante comum termos objetos de domínio em coleções como HashSet, que
 * assumem que os métodos equals() e hashCode() foram implementados de forma
 * que todas as instâncias que representem o mesmo objeto de domínio retornem
 * o mesmo valor em hashCode() e são iguais de acordo com o método equals().
 * 
 * </p><p>
 * Diversas implementações de equals() e hashCode() já foram sugeridas:
 * utilização de um conjunto de propriedades do objeto que, de acordo com as
 * regras de negócio, identificam univocamente o objeto; utilização do 
 * identificador da persistência; etc. Cada idéia possui suas vantagens e
 * desvantagens.
 * 
 * </p><p>
 * A solução proposta nesta classe é a utilização de um identificador único e
 * universal (UUID - Universal Unique Identifier). Esta interface provê métodos
 * para obter e alterar o UUID. Há uma implementacao padrão para esta interface
 * na classe NucleoObjetoDominioImpl.
 * 
 * @see nucleo.comuns.dominio.NucleoObjetoDominioImpl
 * @author Vitor Souza
 */
public interface NucleoObjetoDominio extends Serializable {
	/**
	 * Retorna o identificador universal único.
	 * 
	 * @return O UUID.
	 */
	String getUuid();
}
