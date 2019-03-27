package br.com.costazul.service;

import java.sql.SQLException;

import br.com.costazul.bandodedados.UsuarioDAO;
import br.com.costazul.sistema.Usuario;

public class UsuariosService {

	public static Usuario fazerLogin(String login) throws SQLException {
		UsuarioDAO bancoUsuario = new UsuarioDAO();
		return bancoUsuario.buscar(login);
	}
}
