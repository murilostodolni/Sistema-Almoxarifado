package br.com.costazul.bandodedados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.costazul.sistema.Produto;

public class BalancoDAO {
	private static BalancoDAO singleton;

	public static BalancoDAO getInstance() {
		if (singleton == null) {
			singleton = new BalancoDAO();
		}
		return singleton;
	}

	public void criarTabela() {
		// pegando a data e hora do sistema 24h
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String data = sdf.format(new Date());

		data = data.replaceAll("\\/", "");

		String tabela = "balanco_" + data;

		String sql = "CREATE TABLE IF NOT EXISTS " + tabela + "(" + "codigo STRING," + "nome text,"
				+ "quantidade integer," + "valor REAL" + ");";

		try {
			/* abrindo o banco de dados */
			ConexaoDAO conexao = new ConexaoDAO();

			/* cria o statement */
			Statement stmt = conexao.criarStatement();

			/* executa o stetament com a String sql criada acima */
			stmt.execute(sql);

			/* desconecta o banco de dados */
			conexao.desconectar();
		} catch (SQLException e) {
			System.err.println();
		}
	}

	public void adicionar(Produto t) throws SQLException {
		// pegando a data e hora do sistema 24h
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String data = sdf.format(new Date());

		data = data.replaceAll("\\/", "");

		String tabela = "balanco_" + data;

		/* abrindo o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		/* so adiciona ao banco se o produto ainda nao existe no banco de dados */
		if (this.buscar(t.getCodigoBarra()) == null) {

			try {
				/* cria o statement */
				Statement stmt = conexao.criarStatement();

				/* criando o comando que sera executado pelo statement logo abaixo */
				String comando = "INSERT INTO " + tabela + "(codigo, nome, quantidade, valor)" + "VALUES('"
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

	public Produto buscar(String codigo) throws SQLException {
		// pegando a data e hora do sistema 24h
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String data = sdf.format(new Date());

		data = data.replaceAll("\\/", "");

		String tabela = "balanco_" + data;

		/* abri o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		Produto produto = null;

		String sql = "SELECT * FROM " + tabela + " WHERE codigo = '" + codigo + "';";
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

	public ArrayList<Produto> diferencaBalanco() {
		ArrayList<Produto> r = new ArrayList<>();

		// pegando a data e hora do sistema 24h
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String data = sdf.format(new Date());

		data = data.replaceAll("\\/", "");

		String tabela = "balanco_" + data;

		/* abri o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		ResultSet resultSet = null;
		Statement stmt = conexao.criarStatement();

		String sql = "SELECT * FROM " + "produtos" + " A LEFT JOIN " + tabela
				+ " B ON A.codigo = B.codigo WHERE B.codigo IS NULL;";
		try {
			/*
			 * executa o comando da String sql, ou seja, busca na tbl_alunos a linha que
			 * corresponde ao número de matricula
			 */
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				Produto produto = new Produto(resultSet.getString("codigo"), resultSet.getString("nome"),
						resultSet.getInt("quantidade"), resultSet.getDouble("valor"));

				r.add(produto);
			}

			stmt.close();
			resultSet.close();

			return r;

		} catch (SQLException e) {
			System.err.println();
			/*
			 * finally é utilizado para como "final" para fechar o que foi aberto
			 * anteriormente
			 */
		} finally {
			conexao.desconectar();
		}
		return r;
	}

	public ArrayList<Produto> buscarBalanco(String data) throws SQLException {

		String tabela = "balanco_" + data;

		ArrayList<Produto> r = new ArrayList<Produto>();
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		String sql = "SELECT * FROM " + tabela + ";";
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

	public ArrayList<Produto> buscar() {

		// pegando a data e hora do sistema 24h
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String data = sdf.format(new Date());

		data = data.replaceAll("\\/", "");

		String tabela = "balanco_" + data;

		ArrayList<Produto> r = new ArrayList<Produto>();
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		String sql = "SELECT * FROM " + tabela + ";";
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

	public void deletar(Produto produto) {
		// pegando a data e hora do sistema 24h
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String data = sdf.format(new Date());

		data = data.replaceAll("\\/", "");

		String tabela = "balanco_" + data;

		/* iniciando conexao com o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		/* criando o statement para o banco de dados */
		Statement stmt = conexao.criarStatement();

		/*
		 * criando comando que irá deletar a linha, a linha será achada através do
		 * número de matricula, que é igual ao login
		 */
		String sql = "DELETE FROM " + tabela + " WHERE codigo = '" + produto.getCodigoBarra() + "';";

		try {
			/* executando o comando criando na string */
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.err.println();
		} finally {
			try {
				/* fechando o que foi aberto anteriormente */
				stmt.close();
				conexao.desconectar();

			} catch (Exception e) {
				System.err.println();
			}
		}
	}
}
