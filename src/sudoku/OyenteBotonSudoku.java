package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * Oyente que modela el comportamiento de un BotonSudoku cuando este es
 * precionado.
 * 
 * @author Nahuel Diaz
 *
 */
public class OyenteBotonSudoku implements ActionListener {
	protected BotonSudoku boton;

	/**
	 * Crea una instancia de este oyente.
	 * 
	 * @param boton Boton al cual se le añade este oyente.
	 */
	public OyenteBotonSudoku(BotonSudoku boton) {
		this.boton = boton;
	}

	/**
	 * Obtiene el boton el cual este oyente pertenece.
	 * 
	 * @return BotonSudoku que tiene este oyente.
	 */
	public BotonSudoku getBoton() {
		return boton;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String[] options = new String[] { "Borrar", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Cancelar" };
		int response = JOptionPane.showOptionDialog(null, "Ingrese Opcion", "Numeros", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (response != 10) {
			boton.getJuego().actualizar(boton.getPosX(), boton.getPosY(), response);
		}
	}

}
