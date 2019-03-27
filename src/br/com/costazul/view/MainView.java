package br.com.costazul.view;

import br.com.costazul.bandodedados.EntradaDAO;
import br.com.costazul.bandodedados.ProdutoDAO;
import br.com.costazul.bandodedados.SaidaDAO;
import br.com.costazul.bandodedados.UsuarioDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

	public static Stage stage;

	public static void main(String[] args) {
		// Inicializacao da View
		launch(MainView.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		UsuarioDAO.getInstance().criarTabela();
		ProdutoDAO.getInstance().criarTabela();
		SaidaDAO.getInstance().criarTabela();
		EntradaDAO.getInstance().criarTabela();

		// Instanciacao da classe principal da View
		Parent cena = FXMLLoader.load(getClass().getResource("Login.fxml"));

		Scene scene = new Scene(cena);

		MainView.stage = primaryStage;
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
	}
}
