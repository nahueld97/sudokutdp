package logica;

public class Celda {
	protected Grafica grafica;
	protected int valor;

	public Grafica getGrafica() {
		return grafica;
	}

	public Celda() {
		valor = 0;
		grafica = new Grafica();
	}

	public Celda(int valor) {
		this.valor = valor;
		this.grafica = new Grafica();
	}

	public Integer getValor() {
		return valor;
	}

	public void actualizar(int i) {
		this.valor = i;
		this.grafica.actualizar(i);
	}

	public boolean equals(Celda c) {
		return this.valor == c.getValor();
	}

	public boolean isEmpty() {
		return this.valor == 0;
	}
}
