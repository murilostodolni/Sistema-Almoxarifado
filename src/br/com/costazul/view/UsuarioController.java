package br.com.costazul.view;

import java.io.File;
import java.sql.SQLException;

import br.com.costazul.bandodedados.UsuarioDAO;
import br.com.costazul.geradorpdf.GeradorPdf;
import br.com.costazul.sistema.Cargo;
import br.com.costazul.sistema.Usuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class UsuarioController {

	@FXML
	private MenuItem btnSaidaProduto;

	@FXML
	private MenuItem btnRelatorioProduto;

	@FXML
	private TextField txtNome;

	@FXML
	private CheckBox checkAuxiliar;

	@FXML
	private Button btnAlterarUsuario;

	@FXML
	private AnchorPane adicionarProduto;

	@FXML
	private TextField txtLogin;

	@FXML
	private CheckBox checkGerente;

	@FXML
	private MenuItem btnUsuario;

	@FXML
	private Button btnCriarUsuario;

	@FXML
	private Menu menuProdutos;

	@FXML
	private AnchorPane ancorTeste;

	@FXML
	private MenuItem btnRelatorioAcessos;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private MenuItem btnAlterarProdutos;

	@FXML
	private MenuItem btnRelatorioUsuario;

	@FXML
	private MenuItem btnEntradaProduto;

	@FXML
	private Text txtUsuario;

	@FXML
	private Text txtNomeUsuario;

	@FXML
	private MenuItem btnPesquisaProdutos;

	@FXML
	private Button btnExcluirUsuario;

	@FXML
	private MenuItem btnNovoProduto;

	@FXML
	private Menu menuUsuario;

	@FXML
	private MenuItem btnFazerBalanco;

	@FXML
	private Menu menuRelatorios;

	@FXML
	private MenuItem btnRelatorioValores;

	public void initialize() {
		txtNomeUsuario.setText(IniciarCena.usuarioAtual.getNome());

		if (IniciarCena.usuarioAtual.getCargo() == Cargo.AUXILIAR_MANUTENCAO) {
			menuUsuario.setDisable(true);
			btnRelatorioValores.setDisable(true);
		}
	}

	public void novoProduto() {
		try {
			new IniciarCena("AdicionarProduto", IniciarCena.usuarioAtual, "Adicionar Produto").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void entradaProduto() {
		try {
			new IniciarCena("EntradaProduto", IniciarCena.usuarioAtual, "Entrada Produto").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saidaProduto() {
		try {
			new IniciarCena("Saida", IniciarCena.usuarioAtual, "Saida Produto").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pesquisarProduto() {
		try {
			new IniciarCena("PesquisarProduto", IniciarCena.usuarioAtual, "Pesquisar Produto").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void alterarProduto() {
		try {
			new IniciarCena("AlterarProduto", IniciarCena.usuarioAtual, "Alterar Produto").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fazerBalanco() {
		try {
			new IniciarCena("FazerBalanco", IniciarCena.usuarioAtual, "Fazer Balanco").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void relatorioProdutos() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf"));

		File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			GeradorPdf.gerarPdfProduto(file.getAbsolutePath());
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Erro!");
			alerta.setHeaderText("Selecione um local válido");
			alerta.show();
		}
	}

	public void relatorioSaida() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf"));

		File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			GeradorPdf.gerarPdfSaida(file.getAbsolutePath());
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Erro!");
			alerta.setHeaderText("Selecione um local válido");
			alerta.show();
		}
	}

	public void relatorioEntrada() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf"));

		File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			GeradorPdf.gerarPdfEntrada(file.getAbsolutePath());
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Erro!");
			alerta.setHeaderText("Selecione um local válido");
			alerta.show();
		}
	}

	public void relatorioValores() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf"));

		File file = fileChooser.showSaveDialog(new Stage());

		if (file != null) {
			GeradorPdf.gerarPdfValores(file.getAbsolutePath());
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Erro!");
			alerta.setHeaderText("Selecione um local válido");
			alerta.show();
		}
	}

	public void relatorioAcessos() {

	}

	public void usuario() {
		try {
			new IniciarCena("Usuarios", IniciarCena.usuarioAtual, "Criar Usuário").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void trocarUsuario() {
		try {
			new IniciarCena("Login", null, "Login").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sair() {
		Platform.exit();
	}

	public void buscar() {
		checkAuxiliar.setSelected(false);
		checkGerente.setSelected(false);

		try {
			Usuario usuario = UsuarioDAO.getInstance().buscar(txtLogin.getText());
			if (usuario != null) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("ERRO!");
				alerta.setHeaderText("Login já existe!!!");
				alerta.show();

				txtNome.setText(usuario.getNome());
				txtSenha.setText(usuario.getSenha());
				if (usuario.getCargo() == Cargo.GERENTE)
					checkGerente.setSelected(true);
				else
					checkAuxiliar.setSelected(true);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		} catch (NullPointerException e) {
			// e.printStackTrace();
		}
	}

	public void alterarUsuario() {
		try {
			UsuarioDAO.getInstance().alterar(verificar());
			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setHeaderText("Usuário alterado com sucesso!");
			alerta.show();
			new IniciarCena("Usuario", IniciarCena.usuarioAtual, "Criar Usuário").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluirUsuario() {
		try {
			UsuarioDAO.getInstance().deletar(verificar());
			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setHeaderText("Usuário excluido com sucesso!");
			alerta.show();
			new IniciarCena("Usuario", IniciarCena.usuarioAtual, "Criar Usuário").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Usuario verificar() {
		Usuario usuario = null;
		if (checkAuxiliar.isSelected() && checkGerente.isSelected()) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Aviso!");
			alerta.setHeaderText("Selecione apenas uma opção!");
			alerta.show();
		} else if (!checkAuxiliar.isSelected() && !checkGerente.isSelected()) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Aviso!");
			alerta.setHeaderText("Selecione uma das opções!");
			alerta.show();
		} else if (txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Aviso!");
			alerta.setHeaderText("Você deve preencher todos os campos!");
			alerta.show();
		} else {
			Cargo cargo;

			if (checkGerente.isSelected())
				cargo = Cargo.GERENTE;
			else
				cargo = Cargo.AUXILIAR_MANUTENCAO;

			usuario = new Usuario(txtNome.getText(), txtLogin.getText(), txtSenha.getText(), cargo);
		}
		return usuario;
	}

	public void criarUsuario() {
		try {
			UsuarioDAO.getInstance().adicionar(verificar());
			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setHeaderText("Usuário criado com sucesso!");
			alerta.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			new IniciarCena("Usuario", IniciarCena.usuarioAtual, "Criar Usuário").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
