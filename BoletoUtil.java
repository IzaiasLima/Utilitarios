
public class BoletoUtil {
	private String numero;
	private String codigoBarras;
	private String campo1;
	private String campo2;
	private String campo3;
	private String campo4;
	private String dvBarra;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String boleto) {
		String campo = "";
		String dvCampo = "";
		
		boleto = boleto.trim();
		
		String[] s = boleto.split(" ");
		
		if (s.length > 0)
			boleto = String.join("", s);
		
		s = boleto.split("\\.");
		if (s.length > 0)
			boleto = String.join("", s);
		
		//3419109024 36669760195 28114610000 7 82000000009600
		//1234567890 12345678901 23456789012 3 45678901234567
		
		campo = boleto.substring(0,10);
		dvCampo = campo.substring(campo.length()-1);
		
		System.out.println(dvCampo);
		
		
		
		this.campo1 = boleto.substring(0,10);
		
		
		this.campo1 += dvCampo(this.campo1);
		
//		this.campo2 = this.codigoBarras.substring(24, 34);
//		this.campo2 += dvCampo(this.campo2);
//		
//		this.campo3 = this.codigoBarras.substring(34, 44);
//		this.campo3 += dvCampo(this.campo3);
//		
//		this.campo4 = this.codigoBarras.substring(5, 19);
//		this.dvBarra = this.codigoBarras.substring(4, 5);
		
		this.numero = boleto;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		if (null != codigoBarras && codigoBarras.length() == 44) {
			this.codigoBarras = codigoBarras;
			decomporCodigoBarras();
		}
	}

	private void decomporCodigoBarras() {
		String campo1A = this.codigoBarras.substring(0, 4);
		String campo1B = this.codigoBarras.substring(19, 24);

		this.campo1 = campo1A + campo1B;
		this.campo1 += dvCampo(this.campo1);
		
		this.campo2 = this.codigoBarras.substring(24, 34);
		this.campo2 += dvCampo(this.campo2);
		
		this.campo3 = this.codigoBarras.substring(34, 44);
		this.campo3 += dvCampo(this.campo3);
		
		this.campo4 = this.codigoBarras.substring(5, 19);
		this.dvBarra = this.codigoBarras.substring(4, 5);
		
		setNumero(String.format("%s%s%s%s%s", campo1, campo2, campo3, dvBarra, campo4));

	}

	public boolean isValida() {
		if (null == this.codigoBarras) return false;

		String barraSemDV = this.codigoBarras.substring(0, 4) + this.codigoBarras.substring(5, 44);
		int dvBarra = Integer.parseInt(this.codigoBarras.substring(4, 5));

		int mult = 1;
		int digit = 0;
		int acum = 0;

		for (int i = barraSemDV.length(); i > 0; i--) {
			mult = (mult >= 9) ? 2 : mult + 1;

			digit = Integer.parseInt(barraSemDV.substring(i - 1, i));
			acum += mult * digit;
		}

		int dv = 11 - (acum % 11);
		if (dv == 0 || dv > 9) dv = 1;
		return (dv == dvBarra);
	}
	
	private String dvCampo(String campo) {
		int mult = 1;
		int digit = 0;
		int acum = 0;
		
		for (int i = campo.length(); i > 0; i--) {
			mult = (mult == 1) ? 2 : 1;
			digit = Integer.parseInt(campo.substring(i - 1, i));
			acum += somaDigitos(mult * digit);
		}

		int dv = 10 - acum % 10;
		return Integer.toString((dv > 9) ? 0 : dv);
	}

	private int somaDigitos(int res) {
		if (res < 10)
			return res;
		int d1 = res / 10;
		return d1 + (res - (10 * d1));
	}
	
	private String getStrBoleto(){
		return String.format("%s %s %s %s %s", campo1, campo2, campo3, dvBarra, campo4);
	}

	@Override
	public String toString() {
		return "Número do boleto: " + getStrBoleto() + "\nCódigo de Barras: " + codigoBarras;
	}

}
