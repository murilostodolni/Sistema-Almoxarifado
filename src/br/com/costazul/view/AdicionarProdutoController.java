package br.com.costazul.view;

import java.io.File;
import java.sql.SQLException;

import br.com.costazul.bandodedados.ProdutoDAO;
import br.com.costazul.geradorpdf.GeradorPdf;
import br.com.costazul.sistema.Cargo;
import br.com.costazul.sistema.Produto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AdicionarProdutoController {

	@FXML
	private Menu menuProdutos;

	@FXML
	private MenuItem btnRelatorioAcessos;

	@FXML
	private MenuItem btnSaidaProduto;

	@FXML
	private MenuItem btnAlterarProdutos;

	@FXML
	private TextField txtQuantidade;

	@FXML
	private MenuItem btnRelatorioUsuario;

	@FXML
	private MenuItem btnEntradaProduto;

	@FXML
	private MenuItem btnRelatorioProduto;

	@FXML
	private TextField txtDescricao;

	@FXML
	private Text txtUsuario;

	@FXML
	private AnchorPane adicionarProduto;

	@FXML
	private Text txtNomeUsuario;

	@FXML
	private MenuItem btnPesquisaProdutos;

	@FXML
	private MenuItem btnNovoProduto;

	@FXML
	private TextField txtCodigoBarra;

	@FXML
	private Menu menuUsuario;

	@FXML
	private MenuItem btnFazerBalanco;

	@FXML
	private TextField txtValor;

	@FXML
	private Button btnAdicionarProduto;

	@FXML
	private MenuItem btnUsuario;

	@FXML
	private Menu menuRelatorios;

	@FXML
	private Label lblProdutoExiste;

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

	public void existeProduto() {
		try {
			if (ProdutoDAO.getInstance().buscar(this.txtCodigoBarra.getText()) != null) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("ERRO!");
				alerta.setHeaderText("Produto já existe!!!");
				alerta.show();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void adicionarProdutoBanco() {
		String stgValor = txtValor.getText();
		Double valor;
		try {
			valor = Double.parseDouble(stgValor);
		} catch (RuntimeException ex) {
			valor = Double.parseDouble(stgValor.replaceAll("\\,", "\\."));
		}

		Produto produto = new Produto(txtCodigoBarra.getText(), txtDescricao.getText(),
				Integer.parseInt(txtQuantidade.getText()), valor);

		try {
			ProdutoDAO.getInstance().adicionar(produto);

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Produto criado com sucesso!");
			alerta.setHeaderText("Descrição: " + produto.getNomeProduto() + "\n" + "Código: " + produto.getCodigoBarra()
					+ "\n" + "Quantidade: " + produto.getTotalProdutos() + "\n" + "Valor: "
					+ produto.getValorProduto());
			alerta.show();

			try {
				new IniciarCena("AdicionarProduto", IniciarCena.usuarioAtual, "Sistema Almoxarifado")
						.start(MainView.stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro!");
			alerta.setHeaderText("Erro ao adicionar produto!");
			alerta.setContentText("Contate o administrador do sistema!");
			alerta.show();
		}
	}
}