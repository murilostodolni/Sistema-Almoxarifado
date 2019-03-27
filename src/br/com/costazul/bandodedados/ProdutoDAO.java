package br.com.costazul.bandodedados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.costazul.exceptions.ProdutoSemEstoqueException;
import br.com.costazul.sistema.Produto;
import br.com.costazul.sistema.Usuario;

public class ProdutoDAO implements IDAO<Produto> {
	private static ProdutoDAO singleton;
	private String TABELA_PRODUTOS = "produtos";

	public static ProdutoDAO getInstance() {
		if (singleton == null) {
			singleton = new ProdutoDAO();
		}
		return singleton;
	}

	public void criarTabela() {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_PRODUTOS + "(" + "codigo STRING," + "nome text,"
				+ "quantidade integer," + "valor REAL" + ");";

		/* abrindo o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		/* cria o statement */
		Statement stmt = conexao.criarStatement();
		try {
			/* executa o stetament com a String sql criada acima */
			stmt.execute(sql);

		} catch (SQLException e) {
			System.err.println();
		} finally {
			try {
				stmt.close();
				/* desconecta o banco de dados */
				conexao.desconectar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void adicionar(Produto t) throws SQLException {
		/* abrindo o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		/* so adiciona ao banco se o produto ainda nao existe no banco de dados */
		if (this.buscar(t.getCodigoBarra()) == null) {

			try {
				/* cria o statement */
				Statement stmt = conexao.criarStatement();

				/* criando o comando que sera executado pelo statement logo abaixo */
				String comando = "INSERT INTO " + TABELA_PRODUTOS + "(codigo, nome, quantidade, valor)" + "VALUES('"
						+ t.getCodigoBarra() + "','" + t.getNomeProduto() + "','" + t.getTotalProdutos() + "','"
						+ t.getValorProduto() + "');";

				/* executando o comando atraves do statement */
				stmt.executeUpdate(comando);
				/* fechando o statement */
				stmt.close();

			} catch (SQLException e) {
				System.err.println();
			} finally {
				if (conexao != null) {
					conexao.desconectar();
				}
			}
		}
	}

	public ArrayList<Produto> buscar() {
		ArrayList<Produto> r = new ArrayList<Produto>();
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		String sql = "SELECT * FROM " + TABELA_PRODUTOS + ";";
		try {

			ResultSet resultSet = stmt.executeQuery(sql);

			// se o resultSet for igual a null ele vai returnar null pois não existe aluno
			// TODO ver

			while (resultSet.next()) {

				Produto produto = new Produto(resultSet.getString("codigo"), resultSet.getString("nome"),
						resultSet.getInt("quantidade"), resultSet.getDouble("valor"));

				r.add(produto);
			}

			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conexao != null) {
				conexao.desconectar();
			}
		}
		return r;
	}

	public Produto buscar(String codigo) throws SQLException {
		/* abri o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		Produto produto = null;

		String sql = "SELECT * FROM " + TABELA_PRODUTOS + " WHERE codigo = '" + codigo + "';";
		try {
			/*
			 * executa o comando da String sql, ou seja, busca na tbl_alunos a linha que
			 * corresponde ao número de matricula
			 */
			ResultSet resultSet = stmt.executeQuery(sql);
			/*
			 * se o resultSet for igual a null ele vai returnar null pois não existe aluno
			 */
			if (resultSet.next()) {

				produto = new Produto(resultSet.getString("codigo"), resultSet.getString("nome"),
						resultSet.getInt("quantidade"), resultSet.getDouble("valor"));

				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
		return produto;
	}

	public void alterar(Produto t) {
		/* inicia a conexão com o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		/* cria o stamtemente com o banco de dados */
		Statement stmt = conexao.criarStatement();

		/* criando o comando para ser realizado no bando de dados */
		String sql = "UPDATE " + TABELA_PRODUTOS + " SET codigo = '" + t.getCodigoBarra() + "'," + " nome = '"
				+ t.getNomeProduto() + "'," + " quantidade = '" + t.getTotalProdutos() + "'," + " valor = '"
				+ t.getValorProduto() + "' WHERE codigo = '" + t.getCodigoBarra() + "';";

		try {
			/* executando o comando criado acima */
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.err.println();

		} finally {
			try {
				/* fechando o que foi aberto anteriormente */
				stmt.close();
				conexao.desconectar();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void entradaProduto(Produto t, int quantidade, Usuario usuario) {
		Produto produto = null;
		try {
			produto = buscar(t.getCodigoBarra());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// nao precia de if(produto != null), pois ele so vai entrar nesse metodo caso
		// existe o produto no banco
		produto.setTotalProdutos(produto.getTotalProdutos() + quantidade);

		EntradaDAO.getInstance().darEntrada(t, usuario);

		this.alterar(produto);

	}

	public void saidaProduto(Produto t, int quantidade, Usuario usuario) throws ProdutoSemEstoqueException {
		Produto produto = null;
		try {
			produto = buscar(t.getCodigoBarra());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// nao precia de if(produto != null), pois ele so vai entrar nesse metodo caso
		// existe o produto no banco
		if ((produto.getTotalProdutos() - quantidade) >= 0) {
			produto.setTotalProdutos(produto.getTotalProdutos() - quantidade);
			this.alterar(produto);
			SaidaDAO.getInstance().darSaida(t, usuario);
		} else
			throw new ProdutoSemEstoqueException();

	}

	@Override
	public void deletar(Produto t) {
		// TODO Auto-generated method stub

	}
}
