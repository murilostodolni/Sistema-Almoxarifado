package br.com.costazul.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.costazul.bandodedados.ProdutoDAO;
import br.com.costazul.exceptions.CodigoBarrasInvalidoException;
import br.com.costazul.exceptions.ProdutoNaoExisteException;
import br.com.costazul.sistema.Produto;
import br.com.costazul.sistema.Usuario;

public class ProdutoService {
	private static ProdutoService singleton;

	public static ProdutoService getInstance() {
		if (singleton == null) {
			singleton = new ProdutoService();
		}
		return singleton;
	}

	public Produto entradaProduto(String codigo, Usuario usuario)
			throws SQLException, ProdutoNaoExisteException, CodigoBarrasInvalidoException {
		Produto produto = null;
		ProdutoDAO banco = ProdutoDAO.getInstance();

		if (isOnlyNumbersAndX(codigo)) {
			if (codigo.contains("x") || codigo.contains("X")) {
				// criando uma string com o codigo do produto sem o x e a quantidade a ser
				// adicionada que vem antes do x
				String codigoProduto = codigo.substring(codigo.indexOf("x") + 1, codigo.length());
				String x = "x";

				// caso o usuario digite X
				if (codigo.contains("X"))
					x = "X";

				// pegando a quantidade que é para ser adicionada (antes do x)
				int num = Integer.parseInt(codigo.substring(0, codigo.indexOf(x)));

				produto = banco.buscar(codigoProduto);

				if (produto == null)
					throw new ProdutoNaoExisteException(codigo);

				banco.entradaProduto(produto, num, usuario);
			} else {
				produto = banco.buscar(codigo);

				if (produto == null)
					throw new ProdutoNaoExisteException(codigo);

				banco.entradaProduto(produto, 1, usuario);
			}

		} else {
			throw new CodigoBarrasInvalidoException(codigo);
		}

		return produto;
	}

	private boolean isOnlyNumbersAndX(String texto) {
		boolean r = true;

		for (int i = 0; i < texto.length(); i++) {
			if (texto.charAt(i) != '0' && texto.charAt(i) != '1' && texto.charAt(i) != '2' && texto.charAt(i) != '3'
					&& texto.charAt(i) != '4' && texto.charAt(i) != '5' && texto.charAt(i) != '6'
					&& texto.charAt(i) != '7' && texto.charAt(i) != '8' && texto.charAt(i) != '9'
					&& texto.charAt(i) != 'x' && texto.charAt(i) != 'X')
				r = false;
		}

		return r;
	}

	public ArrayList<Produto> listaProdutos() {
		ArrayList<Produto> r = null;

		ProdutoDAO bancoProduto = new ProdutoDAO();

		r = bancoProduto.buscar();

		return r;
	}
}
