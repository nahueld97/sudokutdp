package logica;

public class Celda {
	protected Grafica grafica;
	protected Integer valor;
	
	public Celda() {
		valor=null;
	}
	
	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public void actualizar(int i) {
		this.valor=i;
	}
	
	public boolean equals(Celda c) {
		return this.valor == c.getValor();
	}
}
