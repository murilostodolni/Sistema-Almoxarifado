package br.com.costazul.sistema;

public class Produto {
	private String codigoBarra;
	private String nomeProduto;
	private int totalProdutos;
	private double valorProduto;

	public Produto(String codigoBarra, String nomeProduto, int totalProdutos, double valorProduto) {
		this.codigoBarra = codigoBarra;
		this.nomeProduto = nomeProduto;
		this.totalProdutos = totalProdutos;
		this.valorProduto = valorProduto;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(int totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
}
