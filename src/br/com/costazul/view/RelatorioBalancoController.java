package br.com.costazul.view;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.costazul.bandodedados.BalancoDAO;
import br.com.costazul.geradorpdf.GeradorPdf;
import br.com.costazul.sistema.Cargo;
import br.com.costazul.sistema.Produto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class RelatorioBalancoController {

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
	private MenuItem btnFazerBalanco;

	@FXML
	private MenuItem btnUsuario;

	@FXML
	private Menu menuRelatorios;

	@FXML
	private MenuItem btnRelatorioValores;

	@FXML
	private Button btnGerarRelatorio;

	@FXML
	private DatePicker data;

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
		try {
			new IniciarCena("RelatorioBalanco", IniciarCena.usuarioAtual, "Relatório Balanco").start(MainView.stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void gerarRelatorio() {
		String dataEscolhida = data.getValue().toString();

		String ano = dataEscolhida.substring(2, 4);
		String mes = dataEscolhida.substring(5, 7);
		String dia = dataEscolhida.substring(8, 10);

		dataEscolhida = dia + mes + ano;

		try {
			ArrayList<Produto> balancoAntigo = BalancoDAO.getInstance().buscarBalanco(dataEscolhida);

			if (balancoAntigo == null) {
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Erro!");
				alerta.setHeaderText("Não foi feito balanço no dia selecionado");
				alerta.show();
			}

			String dataArquivo = dia + "/" + mes + "/" + ano;

			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF", "*.pdf"));

			File file = fileChooser.showSaveDialog(new Stage());

			if (file != null) {
				GeradorPdf.gerarBalancoAntigo(file.getAbsolutePath(), balancoAntigo, dataArquivo);
			} else {
				Alert alerta = new Alert(AlertType.WARNING);
				alerta.setTitle("Erro!");
				alerta.setHeaderText("Selecione um local válido");
				alerta.show();
			}
		} catch (SQLException e) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Erro!");
			alerta.setHeaderText("Não foi feito balanço no dia selecionado");
			alerta.show();
		}
	}
}
