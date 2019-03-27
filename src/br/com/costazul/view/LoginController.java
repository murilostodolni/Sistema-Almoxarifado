package br.com.costazul.view;

import java.sql.SQLException;

import br.com.costazul.service.UsuariosService;
import br.com.costazul.sistema.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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

		if (usuario.getLogin().equals(txtLogin.getText()) && usuario.getSenha().equals(txtSenha.getText())) {
			try {
				new IniciarCena("Menu", usuario, "Menu").start(MainView.stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro!");
			alerta.setHeaderText("Usuário e/ou senha não existe!");
			alerta.show();
		}
	}

}
