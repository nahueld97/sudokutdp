package sudoku;

import java.io.File;

import logica.Juego;

public class Test {

	public static void main(String[] args) {
		Juego j= new Juego();
		File f=new File("C:\\Users\\Usuario\\eclipse-workspace\\sudoku\\src\\init.txt");
		j.iniciar(f);
	}

}
