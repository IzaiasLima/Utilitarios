
public class Main {

	public static void main(String[] args) {
		// String codigo = "00193373700000001000500940144816060680935031";
		String codigo = "34197820000000096001090236669760192811461000";
		String numero = "34191.09024 36669.760195 28114.610000 7 82000000009600";

		
		BoletoUtil boleto = new BoletoUtil();
		
		// boleto.setCodigoBarras(codigo);
		boleto.setNumero(numero);
		
		System.out.println(boleto);
		
		System.out.println(boleto.isValida());

	}

}
