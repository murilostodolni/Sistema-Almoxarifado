package br.com.costazul.view;

import java.io.File;
import java.sql.SQLException;

import br.com.costazul.bandodedados.ProdutoDAO;
import br.com.costazul.geradorpdf.GeradorPdf;
import br.com.costazul.service.ProdutoService;
import br.com.costazul.sistema.Cargo;
import br.com.costazul.sistema.Produto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PesquisarProdutoController {

	@FXML
	private Button btnAdicionar;

	@FXML
	private MenuItem btnSaidaProduto;

	@FXML
	private MenuItem btnRelatorioProduto;

	@FXML
	private AnchorPane adicionarProduto;

	@FXML
	private TextField txtCodigoBarra;

	@FXML
	private AnchorPane listarProdutos;

	@FXML
	private MenuItem btnUsuario;

	@FXML
	private Menu menuProdutos;

	@FXML
	private AnchorPane ancorTeste;

	@FXML
	private MenuItem btnRelatorioAcessos;

	@FXML
	private MenuItem btnAlterarProdutos;

	@FXML
	private TextField txtQuantidade;

	@FXML
	private MenuItem btnRelatorioUsuario;

	@FXML
	private MenuItem btnEntradaProduto;

	@FXML
	private TextField txtDescricao;

	@FXML
	private Text txtUsuario;

	@FXML
	private Text txtNomeUsuario;

	@FXML
	private MenuItem btnPesquisaProdutos;

	@FXML
	private MenuItem btnNovoProduto;

	@FXML
	private Menu menuUsuario;

	@FXML
	private MenuItem btnFazerBalanco;

	@FXML
	private TextField txtValor;

	@FXML
	private Button btnListarProdutos;

	@FXML
	private Menu menuRelatorios;

	@FXML
	private MenuItem btnRelatorioValores;

	@FXML
	private TableView<Produto> tblListaProdutos;

	@FXML
	private TableColumn<Produto, Integer> columQuantidade;

	@FXML
	private TableColumn<Produto, String> columDescricao;

	@FXML
	private TableColumn<Produto, Double> columValor;

	@FXML
	private TableColumn<Produto, String> columCodigo;

	public void initialize() {
		txtNomeUsuario.setText(IniciarCena.usuarioAtual.getNome());
		adicionarProduto.setVisible(true);
		listarProdutos.setVisible(false);

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
			Produto produto = ProdutoDAO.getInstance().buscar(this.txtCodigoBarra.getText());
			if (produto != null) {
				this.txtDescricao.setText(produto.getNomeProduto());
				this.txtQuantidade.setText(Integer.toString(produto.getTotalProdutos()));
				this.txtValor.setText(Double.toString(produto.getValorProduto()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void buttonAdicionarProduto() {
		if (listarProdutos.isVisible())
			listarProdutos.setVisible(false);

		adicionarProduto.setVisible(true);
	}

	public void buttonListarProdutos() {
		if (adicionarProduto.isVisible())
			adicionarProduto.setVisible(false);

		listarProdutos.setVisible(true);

		columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
		columDescricao.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columQuantidade.setCellValueFactory(new PropertyValueFactory<>("totalProdutos"));
		columValor.setCellValueFactory(new PropertyValueFactory<>("valorProduto"));

		tblListaProdutos.setItems(atualizaTabela());

	}

	private ObservableList<Produto> atualizaTabela() {
		return FXCollections.observableArrayList(ProdutoService.getInstance().listaProdutos());
	}
}