package br.com.costazul.bandodedados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.costazul.sistema.Produto;
import br.com.costazul.sistema.Usuario;

public class EntradaDAO {
	private static EntradaDAO singleton;
	private String TABELA_ENTRADA = "entrada";

	public static EntradaDAO getInstance() {
		if (singleton == null) {
			singleton = new EntradaDAO();
		}
		return singleton;
	}

	public void criarTabela() {
		String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_ENTRADA + "(" + "codigo STRING," + "nome text,"
				+ "quantidade integer," + "usuario text," + "data text" + ");";

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

	public void darEntrada(Produto t, Usuario usuario) {
		/* abrindo o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		/* so adiciona ao banco se o produto ainda nao existe no banco de dados */
		// if (this.buscar(t.getCodigoBarra()) == null) {

		try {
			/* cria o statement */
			Statement stmt = conexao.criarStatement();

			// pegando a data e hora do sistema 24h
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			String data = sdf.format(new Date());

			/* criando o comando que sera executado pelo statement logo abaixo */
			String comando = "INSERT INTO " + TABELA_ENTRADA + "(codigo, nome, quantidade, usuario, data)" + "VALUES('"
					+ t.getCodigoBarra() + "','" + t.getNomeProduto() + "','" + t.getTotalProdutos() + "','"
					+ usuario.getNome() + "','" + data + "');";

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

	// TODO fazer o metodo para buscar tudo que está na tabela
	public ArrayList<String> buscar() {
		ArrayList<String> r = new ArrayList<String>();
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		String sql = "SELECT * FROM " + TABELA_ENTRADA + ";";
		try {

			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {

				r.add(resultSet.getString("codigo"));
				r.add(resultSet.getString("nome"));
				r.add(Integer.toString(resultSet.getInt("quantidade")));
				r.add(resultSet.getString("usuario"));
				r.add(resultSet.getString("data"));
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
}
