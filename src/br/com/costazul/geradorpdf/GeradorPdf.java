package br.com.costazul.geradorpdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.costazul.bandodedados.EntradaDAO;
import br.com.costazul.bandodedados.ProdutoDAO;
import br.com.costazul.bandodedados.SaidaDAO;
import br.com.costazul.sistema.Produto;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GeradorPdf {

	public static void gerarPdfProduto(String caminho) {
		ArrayList<String> info = new ArrayList<>();

		ArrayList<Produto> produtos = ProdutoDAO.getInstance().buscar();

		for (int i = 0; i < produtos.size(); i++) {
			info.add(produtos.get(i).getCodigoBarra());
			info.add(produtos.get(i).getNomeProduto());
			info.add(Integer.toString(produtos.get(i).getTotalProdutos()));
		}

		// criação do documento
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			String data = sdf.format(new Date());

			Paragraph p1 = new Paragraph("RELATÓRIO ENTRADAS ALMOXARIFADO");
			Paragraph p2 = new Paragraph(data);

			p1.setAlignment(Element.ALIGN_CENTER);
			p2.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);
			document.add(p2);

			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(new float[] { 0.35f, 0.75f, 0.25f });

			table.addCell("Código Barras");
			table.addCell("Descrição");
			table.addCell("Quantidade");

			for (int i = 0; i < info.size(); i++) {
				table.addCell(info.get(i));
			}

			document.add(table);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

	public static void gerarPdfValores(String caminho) {
		ArrayList<String> info = new ArrayList<>();

		ArrayList<Produto> produtos = ProdutoDAO.getInstance().buscar();

		double totalEstoque = 0;

		DecimalFormat df = new DecimalFormat("0.##");

		for (int i = 0; i < produtos.size(); i++) {
			info.add(produtos.get(i).getCodigoBarra());
			info.add(produtos.get(i).getNomeProduto());
			info.add(Integer.toString(produtos.get(i).getTotalProdutos()));
			Double total = produtos.get(i).getValorProduto() * produtos.get(i).getTotalProdutos();
			totalEstoque += total;
			info.add("R$ " + df.format(total));
		}

		// criação do documento
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			String data = sdf.format(new Date());

			Paragraph p1 = new Paragraph("RELATÓRIO ENTRADAS ALMOXARIFADO");
			Paragraph p2 = new Paragraph(data);

			p1.setAlignment(Element.ALIGN_CENTER);
			p2.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);
			document.add(p2);

			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(new float[] { 0.3f, 0.35f, 0.2f, 0.2f });

			table.addCell("Código Barras");
			table.addCell("Descrição");
			table.addCell("Quantidade");
			table.addCell("Total");

			for (int i = 0; i < info.size(); i++) {
				table.addCell(info.get(i));
			}

			document.add(table);

			Paragraph p3 = new Paragraph("TOTAL EM ESTOQUE: R$ " + df.format(totalEstoque));

			p3.setAlignment(Element.ALIGN_LEFT);

			document.add(p3);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

	public static void gerarPdfSaida(String caminho) {
		ArrayList<String> info = SaidaDAO.getInstance().buscar();

		// criação do documento
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			String data = sdf.format(new Date());

			Paragraph p1 = new Paragraph("RELATÓRIO ENTRADAS ALMOXARIFADO");
			Paragraph p2 = new Paragraph(data);

			p1.setAlignment(Element.ALIGN_CENTER);
			p2.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);
			document.add(p2);

			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(new float[] { 0.5f, 0.75f, 0.2f, 0.35f, 0.40f });

			table.addCell("Código Barras");
			table.addCell("Descrição");
			table.addCell("Quant");
			table.addCell("Nome");
			table.addCell("Data");

			for (int i = 0; i < info.size(); i++) {
				table.addCell(info.get(i));
			}

			document.add(table);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

	public static void gerarPdfEntrada(String caminho) {
		ArrayList<String> info = EntradaDAO.getInstance().buscar();

		// criação do documento
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			String data = sdf.format(new Date());

			Paragraph p1 = new Paragraph("RELATÓRIO ENTRADAS ALMOXARIFADO");
			Paragraph p2 = new Paragraph(data);

			p1.setAlignment(Element.ALIGN_CENTER);
			p2.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);
			document.add(p2);

			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(new float[] { 0.5f, 0.75f, 0.2f, 0.35f, 0.40f });

			table.addCell("Código Barras");
			table.addCell("Descrição");
			table.addCell("Quant");
			table.addCell("Nome");
			table.addCell("Data");

			for (int i = 0; i < info.size(); i++) {
				table.addCell(info.get(i));
			}

			document.add(table);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

	public static void gerarBalancoAntigo(String caminho, ArrayList<Produto> listaBalanco, String data) {
		// criação do documento
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();

			Paragraph p1 = new Paragraph("BALANÇO ALMOXARIFADO DO DIA" + data);

			p1.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);

			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(new float[] { 0.35f, 0.45f, 0.2f });

			table.addCell("Código Barras");
			table.addCell("Descrição");
			table.addCell("Em estoque");

			ArrayList<String> info = new ArrayList<>();

			for (int i = 0; i < listaBalanco.size(); i++) {
				info.add(listaBalanco.get(i).getCodigoBarra());
				info.add(listaBalanco.get(i).getNomeProduto());
				info.add(Integer.toString(listaBalanco.get(i).getTotalProdutos()));
			}

			for (int i = 0; i < info.size(); i++) {
				table.addCell(info.get(i));
			}
			document.add(table);

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setHeaderText("Balanço realizado com sucesso!!");
			alerta.show();

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}

	public static void gerarBalanco(String caminho, ArrayList<Produto> listaNaoConferido,
			ArrayList<String> listaConferido) {
		// criação do documento
		Document document = new Document();
		try {

			PdfWriter.getInstance(document, new FileOutputStream(caminho));
			document.open();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
			String data = sdf.format(new Date());

			Paragraph p1 = new Paragraph("BALANÇO ALMOXARIFADO");
			Paragraph p2 = new Paragraph(data);

			p1.setAlignment(Element.ALIGN_CENTER);
			p2.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);
			document.add(p2);

			document.add(new Paragraph(" "));

			PdfPTable table = new PdfPTable(new float[] { 0.35f, 0.5f, 0.15f, 0.15f });

			table.addCell("Código Barras");
			table.addCell("Descrição");
			table.addCell("Sistema");
			table.addCell("Estoque");

			for (int i = 0; i < listaConferido.size(); i++) {
				table.addCell(listaConferido.get(i));
			}

			document.add(table);

			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			Paragraph p3 = new Paragraph("PRODUTOS EM ESTOQUE NÃO CONFERIDOS NO BALANÇO");

			p3.setAlignment(Element.ALIGN_CENTER);

			document.add(p3);

			document.add(new Paragraph(" "));

			ArrayList<String> info = new ArrayList<>();

			for (int i = 0; i < listaNaoConferido.size(); i++) {
				info.add(listaNaoConferido.get(i).getCodigoBarra());
				info.add(listaNaoConferido.get(i).getNomeProduto());
				info.add(Integer.toString(listaNaoConferido.get(i).getTotalProdutos()));
				info.add("?");
			}

			PdfPTable table1 = new PdfPTable(new float[] { 0.35f, 0.5f, 0.15f, 0.15f });

			table1.addCell("Código Barras");
			table1.addCell("Descrição");
			table1.addCell("Sistema");
			table1.addCell("Estoque");

			for (int i = 0; i < info.size(); i++) {
				table1.addCell(info.get(i));
			}
			document.add(table1);

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setHeaderText("Balanço realizado com sucesso!!");
			alerta.show();

		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		document.close();
	}
}
