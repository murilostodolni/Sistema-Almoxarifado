package br.com.costazul.sistema;

public enum Cargo {
	GERENTE(1), AUXILIAR_MANUTENCAO(2);

	private int nivel;

	Cargo(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return nivel;
	}
}
