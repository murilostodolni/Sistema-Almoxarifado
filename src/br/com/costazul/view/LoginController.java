package br.com.costazul.view;

import java.sql.SQLException;

import br.com.costazul.service.UsuariosService;
import br.com.costazul.sistema.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

	@FXML
	private TextField txtLogin;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnEntrar;

	public void fazerLogin() {
		Usuario usuario = null;

		try {
			usuario = UsuariosService.fazerLogin(txtLogin.getText());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			// TODO caso queira sistema com senha, pegar string da cena e comparar com senha
			// do usuario
			if (usuario.getLogin().equals(txtLogin.getText())) {
				try {
					new IniciarCena("Menu", usuario, "Menu").start(MainView.stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (NullPointerException ex) {
			System.out.println("Login incorreto");
		}

	}

}
