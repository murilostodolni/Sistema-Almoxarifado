package br.com.costazul.exceptions;

public class CodigoBarrasInvalidoException extends Exception {
	private static final long serialVersionUID = 12L;
	private String codigo;

	
	//TODO esse exception deve chamar um pop-up para avisar
	
	
	public CodigoBarrasInvalidoException(String codigo) {
		super();
		this.codigo = codigo;
	}

	public String toString() {
		return "O código de barras " + codigo + "é invalido";
	}
}
