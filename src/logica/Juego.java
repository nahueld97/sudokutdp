package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Juego {
	protected Celda[][] celdas;
	protected final int size = 9;

	public Juego() {
		celdas = new Celda[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				celdas[i][j] = new Celda();
			}
		}
	}

	public void iniciar(File f) {
		try {
			if (f.exists()) {
				Scanner reader = new Scanner(f);
				for (int i = 0; i < this.size && reader.hasNextLine(); i++) {
					String[] aux = reader.nextLine().split(" ");
					for (int j = 0; j < this.size; j++) {
						celdas[i][j].actualizar(Integer.parseInt(aux[j]));
					}
				}
				reader.close();
				if (isChecked()) {
					// TODO start timer
					System.out.println("todo ok");
				} else {
					// TODO idk do something
					System.out.println("fail miserable");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private boolean isChecked() {
		boolean checked = true;
		for (int i = 0; i < this.size && checked; i++) {
			checked = comprobarFila(i);
			checked = comprobarColumna(i);
		}
		for (int i = 0; i < size / 3 && checked; i++) {
			for (int j = 0; j < size / 3 && checked; j++) {
				checked = comprobarPanel(i, j);
			}
		}
		return checked;
	}

	public boolean comprobarPanel(int fila, int columna) {
		boolean ok = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int a = (j != 2) ? i : i + 1; a < 3; a++) {
					for (int b = (j != 2) ? j + 1 : 0; b < 3; b++) {
						ok = !celdas[fila * 3 + i][columna * 3 + j].equals(celdas[fila * 3 + a][columna * 3 + b]);
					}
				}
			}
		}
		return ok;
	}

	public boolean comprobarColumna(int i) {
		boolean ok = true;
		for (int j = 0; j < this.size && ok; j++) {
			for (int n = j + 1; n < this.size && ok; n++) {
				ok = !(celdas[i][j].equals(celdas[i][n]));
			}
		}
		return ok;
	}

	public boolean comprobarFila(int i) {
		boolean ok = true;
		for (int j = 0; j < this.size && ok; j++) {
			for (int n = j + 1; n < this.size && ok; n++) {
				ok = !celdas[j][i].equals(celdas[n][i]);
			}
		}
		return ok;
	}
	/*
	 * private void print() { for (int i = 0; i < celdas.length; i++) { for (int j =
	 * 0; j < celdas.length; j++) {
	 * System.out.print(""+celdas[i][j].getValor()+" "); } System.out.println(""); }
	 * }
	 */
}