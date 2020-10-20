package logica;

import javax.swing.ImageIcon;

public class Grafica {
	protected ImageIcon icon;
	protected String[] imagenes;

	public Grafica() {
		this.icon = new ImageIcon();
		this.imagenes = new String[] { "\\img\\1.png", "\\img\\2.png", "\\img\\3.png", "\\img\\4.png", "\\img\\5.png",
				"\\img\\6.png", "\\img\\7.png", "\\img\\8.png", "\\img\\9.png" };
	}

	public void actualizar(int indice) {
		if (indice < this.imagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(this.imagenes[indice]));
			this.icon.setImage(imageIcon.getImage());
		}
	}
	
	public ImageIcon getIcon() {
		return icon;
	}
	
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	public String[] getImagenes() {
		return imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}
}
