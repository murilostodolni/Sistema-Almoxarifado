package br.com.costazul.view;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.costazul.bandodedados.ProdutoDAO;
import br.com.costazul.geradorpdf.GeradorPdf;
import br.com.costazul.sistema.Cargo;
import br.com.costazul.sistema.Produto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EntradaProdutoController {

	@FXML
	private Menu menuProdutos;

	@FXML
	private MenuItem btnRelatorioAcessos;

	@FXML
	private MenuItem btnSaidaProduto;

	@FXML
	private MenuItem btnAlterarProdutos;

	@FXML
	private MenuItem btnRelatorioUsuario;

	@FXML
	private MenuItem btnEntradaProduto;

	@FXML
	private MenuItem btnRelatorioProduto;

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
	private TextField txtCodigoProduto;

	@FXML
	private MenuItem btnFazerBalanco;

	@FXML
	private Button btnAdicionarProduto;

	@FXML
	private MenuItem btnUsuario;

	@FXML
	private Menu menuRelatorios;

	@FXML
	private MenuItem btnRelatorioValores;

	@FXML
	private Button btnRemover;

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

	private ArrayList<Produto> listaEntrada = new ArrayList<>();

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

	public void buscarProduto() {
		try {
			String codigoProduto = txtCodigoProduto.getText().substring(txtCodigoProduto.getText().indexOf("x") + 1,
					txtCodigoProduto.getText().length());

			Produto produto = ProdutoDAO.getInstance().buscar(codigoProduto);

			if (txtCodigoProduto.getText().contains("x")) {
				String x = "x";
				produto.setTotalProdutos(Integer
						.parseInt(txtCodigoProduto.getText().substring(0, txtCodigoProduto.getText().indexOf(x))));
			} else {
				produto.setTotalProdutos(1);
			}

			listaEntrada.add(produto);
			atualizarLista();
			txtCodigoProduto.setText("");
			txtCodigoProduto.selectPositionCaret(0);
		} catch (SQLException e) {
			// System.out.println("Produto não existe!");
		} catch (NullPointerException e) {
			// System.out.println("Produto não existe!");
		}
	}

	public void remover() {
		Produto produto = tblListaProdutos.getSelectionModel().getSelectedItem();
		listaEntrada.remove(produto);
		atualizarLista();
	}

	public void adicionarProdutoBanco() {
		String entrada = "";
		for (int i = 0; i < listaEntrada.size(); i++) {
			ProdutoDAO.getInstance().entradaProduto(listaEntrada.get(i), listaEntrada.get(i).getTotalProdutos(),
					IniciarCena.usuarioAtual);
			entrada += listaEntrada.get(i).getTotalProdutos() + " x " + listaEntrada.get(i).getNomeProduto() + "\n";
		}

		listaEntrada.clear();

		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setTitle("Entrada bem sucedida!");
		alerta.setHeaderText(entrada);
		alerta.show();

		atualizarLista();
	}

	public void atualizarLista() {
		columCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
		columDescricao.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columQuantidade.setCellValueFactory(new PropertyValueFactory<>("totalProdutos"));
		columValor.setCellValueFactory(new PropertyValueFactory<>("valorProduto"));

		tblListaProdutos.setItems(FXCollections.observableArrayList(listaEntrada));
	}
}