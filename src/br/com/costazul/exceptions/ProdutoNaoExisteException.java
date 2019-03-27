package br.com.costazul.exceptions;

import java.lang.Exception;

public class ProdutoNaoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
	private String codigoProduto;
	
	
	//TODO esse exception deve chamar um pop-up para avisar
	

	public ProdutoNaoExisteException(String codigoProduto) {
		super();
		this.codigoProduto = codigoProduto;
	}

	@Override
	public String toString() {
		return "Produto com o código de barras " + codigoProduto + " não está cadastrado!";
	}

}
