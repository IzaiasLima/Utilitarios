
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

	public void setNumero(String numero) {
		if (numero != null) {
			decomporNumero(numero);
			this.numero = String.format("%s%s%s%s%s", campo1, campo2, campo3, dvBarra, campo4);
			this.codigoBarras = String.format("%s%s%s%s%s%s", campo1.substring(0, 4), dvBarra, campo4,
					campo1.substring(4, 9), campo2.substring(0, 10), campo3.substring(0, 10));
		}
	}

	private void decomporNumero(String numero) {
		numero = numero.trim();
		String[] s = numero.split(" ");

		if (s.length > 0)
			numero = String.join("", s);

		s = numero.split("\\.");
		if (s.length > 0)
			numero = String.join("", s);

		String campo = numero.substring(0, 10);
		if (isDvCampoOK(campo))
			this.campo1 = campo;

		campo = numero.substring(10, 21);
		if (isDvCampoOK(campo))
			this.campo2 = campo;

		campo = numero.substring(21, 32);
		if (isDvCampoOK(campo))
			this.campo3 = campo;

		this.campo4 = numero.substring(33, 47);
		this.dvBarra = numero.substring(32, 33);
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

		this.numero = String.format("%s%s%s%s%s", campo1, campo2, campo3, dvBarra, campo4);
	}

	public boolean isValidoCodigoBarra() {
		if (null == this.codigoBarras)
			return false;

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
		if (dv == 0 || dv > 9)
			dv = 1;
		return (dv == dvBarra);
	}

	private boolean isDvCampoOK(String campo) {
		String numCampo = campo.substring(0, campo.length() - 1);
		String dv = campo.substring(campo.length() - 1);
		return dvCampo(numCampo).equals(dv);
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

	private String getStrBoleto() {
		return String.format("%s %s %s %s %s", campo1, campo2, campo3, dvBarra, campo4);
	}

	@Override
	public String toString() {
		return "Número do boleto: " + getStrBoleto() + "\nCódigo de Barras: " + codigoBarras;
	}

}
