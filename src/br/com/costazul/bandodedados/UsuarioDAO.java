package br.com.costazul.bandodedados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.costazul.sistema.Cargo;
import br.com.costazul.sistema.Usuario;

public class UsuarioDAO implements IDAO<Usuario> {
	private static UsuarioDAO singleton;
	private String TABELA_USUARIOS = "usuarios";

	public static UsuarioDAO getInstance() {
		if (singleton == null) {
			singleton = new UsuarioDAO();
		}
		return singleton;
	}

	public void criarTabela() {
		ConexaoDAO conexao = new ConexaoDAO();

		String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIOS + "(" + "nome STRING," + "login text,"
				+ "senha text," + "nivel integer" + ");";

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

	public void adicionar(Usuario t) {
		/* abrindo o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		try {
			/* sï¿½ entra se a pessoa adicionada ainda nï¿½o existe no banco de dados */
			if (this.buscar(t.getLogin()) == null) {

				/* cria o statement */
				Statement stmt = conexao.criarStatement();

				/* criando o comando que serï¿½ executado pelo statement logo abaixo */
				String comando = "INSERT INTO " + TABELA_USUARIOS + "(nome, login, senha, nivel)" + "VALUES('"
						+ t.getNome() + "','" + t.getLogin() + "','" + t.getSenha() + "','" + t.getCargo().getNivel()
						+ "');";

				/* executando o comando atravï¿½s do statement */
				stmt.executeUpdate(comando);
				/* fechando o statement */
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conexao != null) {
				conexao.desconectar();
			}
		}
	}

	public ArrayList<Usuario> buscar() {
		/* abri o banco de dados */
		ArrayList<Usuario> r = new ArrayList<Usuario>();
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		/*
		 * qual informação você quer buscar? nesse caso ele quer buscar pelo login que é
		 * igual ao número de matricula
		 */
		String sql = "SELECT * FROM " + TABELA_USUARIOS + ";";
		try {
			/*
			 * executa o comando da String sql, ou seja, busca na tbl_alunos a linha que
			 * corresponde ao número de matricula
			 */
			ResultSet resultSet = stmt.executeQuery(sql);
			/*
			 * se o resultSet for igual a null ele vai returnar null pois não existe aluno
			 */
			/*
			 * if (!resultSet.next()) { stmt.close(); conexao.desconectar(); return null; }
			 */

			while (resultSet.next()) {
				Cargo cargo = null;
				if (resultSet.getInt("nivel") == 1)
					cargo = Cargo.GERENTE;
				else
					cargo = Cargo.AUXILIAR_MANUTENCAO;

				Usuario usuario = new Usuario(resultSet.getString("nome"), resultSet.getString("login"),
						resultSet.getString("senha"), cargo);

				r.add(usuario);
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

	public Usuario buscar(String codigo) throws SQLException {
		/* abri o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		Statement stmt = conexao.criarStatement();

		Usuario usuario = null;

		/*
		 * qual informação você quer buscar? nesse caso ele quer buscar pelo login que é
		 * igual ao número de matricula
		 */
		String sql = "SELECT * " + " FROM " + TABELA_USUARIOS + " WHERE login = '" + codigo + "';";
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

				Cargo cargo = null;
				if (resultSet.getInt("nivel") == 1)
					cargo = Cargo.GERENTE;
				else
					cargo = Cargo.AUXILIAR_MANUTENCAO;

				usuario = new Usuario(resultSet.getString("nome"), resultSet.getString("login"),
						resultSet.getString("senha"), cargo);

				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conexao.desconectar();
		}
		return usuario;
	}

	public void alterar(Usuario t) {
		ConexaoDAO conexao = new ConexaoDAO();

		/* cria o stamtemente com o banco de dados */
		Statement stmt = conexao.criarStatement();

		/* criando o comando para ser realizado no bando de dados */
		String sql = "UPDATE " + TABELA_USUARIOS + " SET nome = '" + t.getNome() + "'," + " login = '" + t.getLogin()
				+ "'," + " senha = '" + t.getSenha() + "'," + " nivel = '" + t.getCargo().getNivel() + "'"
				+ " WHERE login = '" + t.getLogin() + "';";

		try {
			/* executando o comando criado acima */
			stmt.executeUpdate(sql);

			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}

	public void deletar(Usuario t) {
		/* iniciando conexao com o banco de dados */
		ConexaoDAO conexao = new ConexaoDAO();

		/* criando o statement para o banco de dados */
		Statement stmt = conexao.criarStatement();

		/*
		 * criando comando que irá deletar a linha, a linha será achada através do
		 * número de matricula, que é igual ao login
		 */
		String sql = "DELETE FROM " + TABELA_USUARIOS + " WHERE login = '" + t.getLogin() + "';";

		try {
			/* executando o comando criando na string */
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.desconectar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
