package br.com.costazul.bandodedados;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO<T> {
	
	public void criarTabela();

	public void adicionar(T t) throws SQLException;
	
	public ArrayList<T> buscar();

	public T buscar(String codigo) throws SQLException;

	public void deletar(T t);
}
