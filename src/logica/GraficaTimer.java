package logica;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Componente grafico que muestra el tiempo.
 * 
 * @author Nahuel Diaz
 *
 */
public class GraficaTimer extends JLabel {

	private static final long serialVersionUID = 6760200321651442430L;
	protected String[] imagenes;
	protected ImageIcon[] iconos;
	// labels digitos timer
	protected JLabel labelDecimalH, labelUnidadH, labelDecimalM, labelUnidadM, labelDecimalS, labelUnidadS;
	// label divisiones
	protected JLabel labelDiv, labelDiv2;

	/**
	 * inicializa el Reloj con los dijitos en 0.
	 */
	public GraficaTimer() {
		super();
		imagenes = new String[] { "/img/zero.png", "/img/1.png", "/img/2.png", "/img/3.png", "/img/4.png", "/img/5.png",
				"/img/6.png", "/img/7.png", "/img/8.png", "/img/9.png", "/img/div.png" };
		labelDecimalH = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[0])));
		labelUnidadH = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[0])));
		labelDecimalM = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[0])));
		labelUnidadM = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[0])));
		labelDecimalS = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[0])));
		labelUnidadS = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[0])));
		labelDiv = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[10])));
		labelDiv2 = new JLabel(new ImageIcon(this.getClass().getResource(imagenes[10])));

		iconos = new ImageIcon[imagenes.length];
		for (int i = 0; i < iconos.length; i++) {
			iconos[i] = new ImageIcon(getClass().getResource(imagenes[i]));
		}

		this.setLayout(new GridLayout(0, 8, 0, 0));
		this.setBackground(Color.white);

		this.add(labelDecimalH);
		this.add(labelUnidadH);

		this.add(labelDiv);

		this.add(labelDecimalM);
		this.add(labelUnidadM);

		this.add(labelDiv2);

		this.add(labelDecimalS);
		this.add(labelUnidadS);
	}

	/**
	 * Actualiza las imagenes de los digitos del reloj.
	 * 
	 * @param horas    Nro de horas a mostrar.
	 * @param minutos  Nro de minutos a mostrar.
	 * @param segundos Nro de segundos a mostrar.
	 */
	public void actualizar(int horas, int minutos, int segundos) {
		labelDecimalH.setIcon(iconos[horas / 10]);
		reDimensionar(labelDecimalH, iconos[horas / 10]);

		labelUnidadH.setIcon(iconos[horas % 10]);
		reDimensionar(labelUnidadH, iconos[horas % 10]);

		labelDecimalM.setIcon(iconos[minutos / 10]);
		reDimensionar(labelDecimalM, iconos[minutos / 10]);

		labelUnidadM.setIcon(iconos[minutos % 10]);
		reDimensionar(labelUnidadM, iconos[minutos % 10]);

		labelDecimalS.setIcon(iconos[segundos / 10]);
		reDimensionar(labelDecimalS, iconos[segundos / 10]);

		labelUnidadS.setIcon(iconos[segundos % 10]);
		reDimensionar(labelUnidadS, iconos[segundos % 10]);

	}

	/**
	 * Redimensiona los componentes graficos adaptandose al tamaño actual.
	 * 
	 * @param label   Componente a actualizar.
	 * @param grafico Imagen del componente.
	 */
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
}
