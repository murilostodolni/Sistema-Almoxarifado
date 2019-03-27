package br.com.costazul.view;

import br.com.costazul.sistema.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IniciarCena extends Application {

	public static Stage stage;
	public String link; // link para a cena que você quer ir
	public static Usuario usuarioAtual; // pessoa que eu quero ficar passando toda vez que chama uma nova cena
	public String titulo; // texto que aparece no cabecalho da janela

	public IniciarCena(String nomeCena, Usuario atual, String titulo) {
		this.link = nomeCena + ".fxml";
		IniciarCena.usuarioAtual = atual;
		this.titulo = titulo;
	}

	public static void main(String[] args) {
		Application.launch(MainView.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource(link));

		Scene scene = new Scene(root);

		IniciarCena.stage = primaryStage;
		stage.setScene(scene);
		stage.setTitle(titulo);
		stage.show();
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
