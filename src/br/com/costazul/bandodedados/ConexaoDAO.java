package br.com.costazul.bandodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoDAO {
	private Connection conexao;

	public ConexaoDAO() {
		try {
			String url = "jdbc:sqlite:" + "banco_dados.db";

			this.conexao = DriverManager.getConnection(url);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/* cria os statements para o sql ser executado */
	public void desconectar() {
		try {
			if (this.conexao.isClosed() == false) {
				this.conexao.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/* método é apenas para não precisar ficar fazer toda vez uma conexão para criar
	 * o statement */
	public Statement criarStatement() {
		try {
			return this.conexao.createStatement();
		} catch (SQLException e) {
			return null;
		}
	}

	public Connection getConexao() {
		return conexao;
	}
}
