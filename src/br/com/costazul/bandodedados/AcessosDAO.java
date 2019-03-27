package br.com.costazul.bandodedados;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import br.com.costazul.sistema.Usuario;

public class AcessosDAO {
	private static AcessosDAO singleton;
	private String TABELA_ACESSOS = "produtos";

	public static AcessosDAO getInstance() {
		if (singleton == null) {
			singleton = new AcessosDAO();
		}
		return singleton;
	}

	public void criarTabela() {
		ConexaoDAO conexao = new ConexaoDAO();

		String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_ACESSOS + "(" + "nome STRING," + "dia integer,"
				+ "mes integer," + "ano integer," + "hora text" + ");";

		try {
			/* cria o statement */
			Statement stmt = conexao.criarStatement();

			/* executa o stetament com a String sql criada acima */
			stmt.execute(sql);

			/*
			 * desconecta o banco de dados conexao.desconectar();
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/* desconecta o banco de dados */
			if (conexao != null) {
				conexao.desconectar();
			}
		}
	}

	public void adicionar(Usuario usuario, Calendar calendario) {
		// TODO implementar
	}
}
