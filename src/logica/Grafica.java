package logica;

import javax.swing.ImageIcon;

public class Grafica {
	protected ImageIcon icon;
	protected ImageIcon disabledIcon;
	protected String[] imagenes;
	protected String[] imagenesDesabilitadas;

	public Grafica() {
		this.imagenes = new String[] { "/img/0.png", "/img/1.png", "/img/2.png", "/img/3.png", "/img/4.png",
				"/img/5.png", "/img/6.png", "/img/7.png", "/img/8.png", "/img/9.png" };
		this.imagenesDesabilitadas = new String[] { "/img/0.png", "/img/d1.png", "/img/d2.png", "/img/d3.png",
				"/img/d4.png", "/img/d5.png", "/img/d6.png", "/img/d7.png", "/img/d8.png", "/img/d9.png" };
		this.icon = new ImageIcon(this.getClass().getResource(this.imagenes[0]));
		this.disabledIcon = new ImageIcon(this.getClass().getResource(this.imagenesDesabilitadas[0]));
	}

	public void actualizar(int indice) {
		if (indice >= 0 && indice <= this.imagenes.length) {
			ImageIcon imagen = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			ImageIcon imagenDesabilitada = new ImageIcon(
					this.getClass().getResource(this.imagenesDesabilitadas[indice]));
			this.icon.setImage(imagen.getImage());
			this.disabledIcon.setImage(imagenDesabilitada.getImage());
		}
	}

	public ImageIcon getIcon() {
		return this.icon;
	}

	public ImageIcon getDisabledIcon() {
		return this.disabledIcon;
	}
}
