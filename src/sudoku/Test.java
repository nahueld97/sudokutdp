package sudoku;

import java.awt.EventQueue;
import java.io.File;

import logica.Juego;

public class Test {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Juego j = new Juego();
					File f = new File("src\\init.txt");
					GUI window = new GUI(j);
					window.iniciarJuego(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
