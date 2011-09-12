package ode.nucleo.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NucleoUtil {

	public static final String SALT = "97hg4wg";
	/**
	 * Verifica se dois objetos são iguais, mesmo se forem nulos.
	 * 
	 * @param objeto1
	 *            Um dos objetos a ser comparado.
	 * @param objeto2
	 *            Um dos objetos a ser comparado.
	 * 
	 * @return Se forem iguais, retorna verdadeiro. Caso contrário, falso.
	 */
	public static boolean saoIguais(Object objeto1, Object objeto2) {

		if ((objeto1 != null) && (objeto2 != null)) {
			// Verifica se são datas
			if ((objeto1 instanceof Date) && (objeto2 instanceof Date)) {
				objeto1 = ((Date) objeto1).getTime();
				objeto2 = ((Date) objeto2).getTime();
				// Verifica se são BigDecimais
			} else if ((objeto1 instanceof BigDecimal)
					&& (objeto2 instanceof BigDecimal)) {
				
				// Ajusta os números com duas casas decimais
				objeto1 = ((BigDecimal)objeto1).setScale(2,BigDecimal.ROUND_HALF_UP);
				objeto2 = ((BigDecimal)objeto2).setScale(2,BigDecimal.ROUND_HALF_UP);
				
				return ((BigDecimal) objeto1).compareTo((BigDecimal) objeto2) == 0;
			}
			return objeto1.equals(objeto2);
		} else if ((objeto1 == null) && (objeto2 == null)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Retorna a diferenca da data2 pela data1 em dias. Se a data1 for maior que
	 * a data2, o valor retornado é negativo.
	 * 
	 * @param data1
	 *            Data da qual será subtraída a data2.
	 * @param data2
	 *            Data que será subtraída da data1.
	 * @return A diferenca entre as datas data2 e data1 em dias.
	 */
	public static long diferencaDatasEmDias(Date data1, Date data2) {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			data1 = df.parse(df.format(data1));
			data2 = df.parse(df.format(data2));

			long mili1 = data1.getTime();
			long mili2 = data2.getTime();

			long miliDiferenca = mili2 - mili1;

			return miliDiferenca / 86400000;

		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public static String encrypt(String str) {
		String output = "";
		try {
			str = SALT+str; //SALT
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(str.getBytes("UTF-8")));
			output = hash.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
}
