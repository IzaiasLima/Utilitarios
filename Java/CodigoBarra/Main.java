
public class Main {

	public static void main(String[] args) {
		String numero = "34191.09024 36669.760195 28114.610000 7 82000000009600";
		// String codigo = "34197820000000096001090236669760192811461000";
		
		String codigo = "00193373700000001000500940144816060680935031";
		
		BoletoUtil boleto = new BoletoUtil();
		boleto.setNumero(numero);
		System.out.println(boleto);
		System.out.println(boleto.isValidoCodigoBarra());
		
		boleto.setCodigoBarras(codigo);
		System.out.println(boleto);
		System.out.println(boleto.isValidoCodigoBarra());
		
		numero = "0019050095 40144816069 06809350314 3 37370000000100";
		boleto.setNumero(numero);
		System.out.println(boleto);
		System.out.println(boleto.isValidoCodigoBarra());
	}
}
