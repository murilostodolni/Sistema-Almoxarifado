package br.com.costazul.sistema;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Testes {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String data = sdf.format(new Date());
		
		data = data.replaceAll("\\/", "");
		System.out.println(data);
	}

}
