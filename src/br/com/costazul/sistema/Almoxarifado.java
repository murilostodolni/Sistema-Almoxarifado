package br.com.costazul.sistema;

public class Almoxarifado {
	private int quantidadeTotalEstocado;
	private float valorTotalEstocado;
	private Produto listaProdutos[] = new Produto[1];
	private int posicaoUltimoProdutoCadastrado = 0; 
	
	public void entradaProduto(Produto produto, int quantidade) {
		//this.setQuantidadeTotalEstocado(this.getQuantidadeTotalEstocado() + quantidade);;
		//this.setQuantidadeTotalEstocado(this.getQuantidadeTotalEstocado() + );
	}
	
	public void saidaProduto() {
		
	}
	
	public void alterarInformacaoProduto() {
		
	}
	
	public void adicionarProdutoAlmoxarifado(Produto produto) {
		Produto temp[] = new Produto[this.posicaoUltimoProdutoCadastrado + 1];
		
		listaProdutos[this.posicaoUltimoProdutoCadastrado] = produto;
		
		for(int i=0; i<this.listaProdutos.length; i++) {
			temp[i] = this.listaProdutos [i];
		}
		
		this.listaProdutos = temp;		
		this.posicaoUltimoProdutoCadastrado++;
	}
	
	public void verificaValorTotalEstocado() {
		System.out.println("TOTAL ESTOCADO: " + (this.posicaoUltimoProdutoCadastrado + 1));
	}
	
	public void verificaQuantidadeTotalProdutosEstocado() {
		
	}

	
	//----- GETTERS E SETTERS -----
	
	public int getQuantidadeTotalEstocado() {
		return quantidadeTotalEstocado;
	}

	public void setQuantidadeTotalEstocado(int quantidadeTotalEstocado) {
		this.quantidadeTotalEstocado = quantidadeTotalEstocado;
	}

	public float getValorTotalEstocado() {
		return valorTotalEstocado;
	}

	public void setValorTotalEstocado(float valorTotalEstocado) {
		this.valorTotalEstocado = valorTotalEstocado;
	}


}
