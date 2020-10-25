package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase modela el funcionamiento de un juego de sudoku clasico de 9*9.
 * 
 * @author Nahuel Diaz
 *
 */
public class Juego {
	protected Celda[][] celdas;
	protected final int size = 9;
	protected Reloj timer;

	/**
	 * Crea una instancia de un juego con las casillas vacias
	 */
	public Juego() {
		celdas = new Celda[this.size][this.size];
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				celdas[i][j] = new Celda();
			}
		}
		timer = new Reloj();
	}

	/**
	 * Inicializa el juego desde una solucion dada por un archivo.
	 * 
	 * @param f Archivo de la solucion a cargar, esta debe respetar el formato: Los
	 *          nros del 1 al 9 separados por espacio. Cada linea representa una
	 *          fila del juego.
	 * @throws JuegoException si la solucion no es valida o no respeta el formato.
	 */
	public void iniciar(File f) throws JuegoException {

		// inicio y checkeo el archivo
		if (f.exists()) {
			Scanner reader;
			try {
				reader = new Scanner(f);
				for (int i = 0; i < this.size && reader.hasNextLine(); i++) {
					String[] linea = reader.nextLine().split(" ");
					for (int j = 0; j < this.size; j++) {
						try {
							int aux = Integer.parseInt(linea[j]);
							if (aux > 0 && aux <= this.size) {
								celdas[i][j].actualizar(aux);
							}
						} catch (NumberFormatException e) {
							reader.close();
							throw new JuegoException("EL archivo no respeta el formato");
						}
					}
				}
				reader.close();
			} catch (FileNotFoundException e1) {
				throw new JuegoException("No se encuentra el archivo");
			}
		} else {
			throw new JuegoException("No se encuentra el archivo");
		}

		if (esGanador()) {
			timer.inicio();
			randomizar();
		} else {
			throw new JuegoException("Solucion no valida");
		}
	}

	/**
	 * Comprueva que el estado del juego este completo y sea este una solucion
	 * valida.
	 * 
	 * @return verdadero si el juego esta finalizado o falso en caso contrario.
	 */
	public boolean esGanador() {
		boolean checked = true;
		for (int i = 0; i < this.size && checked; i++) {
			for (int j = 0; j < this.size && checked; j++) {
				checked = !celdas[i][j].isEmpty() && comprobarPanel(i, j) && comprobarFila(i, j)
						&& comprobarColumna(i, j);
			}
		}
		return checked;
	}

	/**
	 * Desabilita al azar casillas del juego.
	 */
	private void randomizar() {
		Random ran = new Random();
		for (Celda[] cel : celdas) {
			for (Celda celda : cel) {
				if (ran.nextBoolean()) {
					celda.actualizar(0);
				}
			}
		}
	}

	/**
	 * Comprueba si el panel de la casilla en la posicion dada corresponde a un
	 * panel valido
	 * 
	 * @param fila    posicion de la fila de la casilla.
	 * @param columna posicion de la columna de la casilla.
	 * @return verdadero si el panel es valido y falso en caso contrario.
	 */
	public boolean comprobarPanel(int fila, int columna) {
		boolean ok = true;
		int celdaX = (int) fila / 3;
		int celdaY = (int) columna / 3;
		for (int i = 0; i < 3 && ok; i++) {
			for (int j = 0; j < 3 && ok; j++) {
				if (celdaX * 3 + i != fila && celdaY * 3 + j != columna)
					ok = !celdas[fila][columna].equals(celdas[celdaX * 3 + i][celdaY * 3 + j]);
			}
		}
		return ok;
	}

	/**
	 * Comprueba si la columna de la casilla en la posicion dada corresponde es
	 * valida.
	 * 
	 * @param fila    posicion de la fila de la casilla.
	 * @param columna posicion de la columna de la casilla.
	 * @return verdadero si la columna es valida y falso en caso contrario.
	 */
	public boolean comprobarColumna(int fila, int columna) {
		boolean ok = true;
		for (int j = 0; j < this.size && ok; j++) {
			if (celdas[j][columna].getValor() != 0 && fila != j) {
				ok = !(celdas[fila][columna].equals(celdas[j][columna]));
			}
		}
		return ok;
	}

	/**
	 * Comprueba si la fila de la casilla en la posicion dada corresponde es valida.
	 * 
	 * @param fila    posicion de la fila de la casilla.
	 * @param columna posicion de la fila de la casilla.
	 * @return verdadero si la fila es valida y falso en caso contrario.
	 */
	public boolean comprobarFila(int fila, int columna) {
		boolean ok = true;
		for (int j = 0; j < this.size && ok; j++) {
			if (celdas[fila][j].getValor() != 0 && columna != j) {
				ok = !celdas[fila][columna].equals(celdas[fila][j]);
			}
		}
		return ok;
	}

	/**
	 * Recupera la celda en la posicion dada.
	 * 
	 * @param fila    posicion de la fila de la casilla.
	 * @param columna posicion de la columna de la casilla.
	 * @return
	 */
	public Celda getCelda(int fila, int columna) {
		return celdas[fila][columna];
	}

	/**
	 * Termina con el juego en curso, volviendo a un estado de juego vacio.
	 */
	public void terminar() {
		timer.detener();
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				celdas[i][j] = new Celda();
			}
		}
	}

	/**
	 * Recupera el Reloj que lleva el tiempo en que este juego transcurre.
	 * 
	 * @return Reloj del juego en curso.
	 */
	public Reloj getReloj() {
		return timer;
	}

}