
public class CodigoDeBarra {

	// static String barra = "00193373700000001000500940144816060680935031";
	static String barra = "34197820000000096001090236669760192811461000";

	public static void main(String[] args) {
		String campo1A = barra.substring(0, 4);
		String campo1B = barra.substring(19, 24);
		String campo1 = campo1A + campo1B;

		String campo2 = barra.substring(24, 34);
		String campo3 = barra.substring(34, 44);
		String campo4  = barra.substring(5, 19);
		
		String dvBarra  = barra.substring(4, 5);

		System.out.println("Campo1A: " + campo1A);
		System.out.println("Campo1B: " + campo1B);
		System.out.println("Campo1: " + campo1);

		System.out.println("Campo2: " + campo2);
		System.out.println("Campo3: " + campo3);
		
		String campo1DV = String.format("%s%s", campo1, dv(campo1));
		String campo2DV = String.format("%s%s", campo2, dv(campo2));
		String campo3DV = String.format("%s%s", campo3, dv(campo3));
		
		System.out.println("\n");
		
		System.out.println(String.format("Boleto: %s %s %s %s %s", campo1DV, campo2DV, campo3DV, dvBarra, campo4));

		System.out.println("\n");
		
		if (checkDvBarra(barra)) 
			System.out.println("Barra OK");
		else 
			System.out.println("Leitura da barra com erro!");
	}

	static private int dv(String campo) {
		int mult = 1;
		int digit = 0;
		int res = 0;
		int acum = 0;
		
		System.out.println("\n");

		for (int i = campo.length(); i > 0; i--) {
			mult = (mult == 1) ? 2 : 1;
			digit = Integer.parseInt(campo.substring(i - 1, i));
			res = mult * digit;
			acum += somaDigitos(res);

			System.out.println(String.format("%s x %s = %s (%s)", digit, mult, somaDigitos(res), res));

		}

		int resto = acum % 10;
		int dv = 10 - resto;

		dv = (dv > 9) ? 0 : dv;

		System.out.println("Acum: " + acum);
		System.out.println("Resto: " + resto);

		System.out.println("DV: " + dv);

		return dv;
	}

	static int somaDigitos(int res) {
		if (res < 10)
			return res;
		int d1 = res / 10;
		return d1 + (res - (10 * d1));
	}
	
	static private boolean checkDvBarra(String barra) {
		String barraSemDV = barra.substring(0,4) + barra.substring(5,44);
		int dvBarra = Integer.parseInt(barra.substring(4, 5));
		
		int mult = 1;
		int digit = 0;
		int res = 0;
		int acum = 0;
		
		for (int i = barraSemDV.length(); i > 0; i--) {
			mult = (mult >= 9) ? 2 : mult + 1;
			
			
			digit = Integer.parseInt(barraSemDV.substring(i - 1, i));
			res = mult * digit;
			acum += res;

			System.out.println(String.format("%s x %s = %s", digit, mult, res));
		}
		
		int dv = 11 - (acum % 11);
		
		if (dv == 0  || dv > 9) dv=1;
		
		System.out.println(String.format("Acum: %s, div: %s, DV: %s", acum, acum/11, dv));
		
		
		return (dv == dvBarra);
	}

}
