package sudoku;

import javax.swing.JButton;

public class BotonSudoku extends JButton {
	private static final long serialVersionUID = 1670032114815262339L;
	protected int posX, posY;
	protected GUI juego;

	public BotonSudoku(int posX, int posY, GUI juego) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.juego = juego;
	}

	public GUI getJuego() {
		return juego;
	}

	public void setJuego(GUI juego) {
		this.juego = juego;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
