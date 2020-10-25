package sudoku;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import logica.Celda;
import logica.Grafica;
import logica.Juego;
import logica.JuegoException;

/**
 * Aplicacion de sudoku con interfaz grafica.
 * 
 * @author Nahuel Diaz
 *
 */
public class GUI {

	private static final File FILE = new File("solucion\\init.txt");

	protected JFrame frame;
	protected JPanel gamePane;
	protected JPanel optionPane;
	protected BotonSudoku[][] botonera;
	protected List<BotonSudoku> botonesIniciales;
	protected Juego juego;
	protected JButton botonPausa;
	protected JButton botonInicio;
	protected JButton botonReanudar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Juego j = new Juego();
				GUI window = new GUI(j);
				window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Inicializa la interfaz del juego lista para empezar a jugar.
	 * 
	 * @param juego Logica del juego que determina el comportamiento.
	 */
	public GUI(Juego juego) {
		this.juego = juego;
		botonesIniciales = new LinkedList<BotonSudoku>();
		initialize();
	}

	/**
	 * Inicializa los componentes graficos
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 750, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		gamePane = new JPanel();
		frame.getContentPane().add(gamePane, BorderLayout.CENTER);
		gamePane.setLayout(new GridLayout(9, 9, 0, 0));

		optionPane = new JPanel();
		frame.getContentPane().add(optionPane, BorderLayout.NORTH);
		FlowLayout layoutOption = new FlowLayout();
		layoutOption.setAlignment(FlowLayout.LEFT);
		optionPane.setLayout(layoutOption);
		optionPane.setBorder(new LineBorder(Color.BLACK));

		botonInicio = new JButton("Iniciar Juego");
		optionPane.add(botonInicio);
		botonInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msn = "Como jugar:\n" + "-Los numeros no pueden repetirse verticalmente\n"
						+ "-Tampoco pueden repetirse Horizontalmente\n" + "-Ni tampoco dentro de cada grupo\n"
						+ "-Diviertase!!";

				JOptionPane.showMessageDialog(frame, msn, "Info", JOptionPane.DEFAULT_OPTION);
				iniciarJuego(GUI.FILE);
				botonInicio.setEnabled(false);
				botonPausa.setEnabled(true);
			}
		});

		botonPausa = new JButton("Pausa");
		optionPane.add(botonPausa);
		botonPausa.setEnabled(false);
		botonPausa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				juego.getReloj().detener();
				for (BotonSudoku[] botonSudokus : botonera) {
					for (BotonSudoku botonSudoku : botonSudokus) {
						botonSudoku.setEnabled(false);
					}
				}
				botonPausa.setEnabled(false);
				botonReanudar.setEnabled(true);
			}
		});

		botonReanudar = new JButton("Reanudar");
		optionPane.add(botonReanudar);
		botonReanudar.setEnabled(false);
		botonReanudar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				juego.getReloj().reanudar();
				for (BotonSudoku[] botonSudokus : botonera) {
					for (BotonSudoku botonSudoku : botonSudokus) {
						if (!botonesIniciales.contains(botonSudoku)) {
							botonSudoku.setEnabled(true);
						}
					}
				}
				botonReanudar.setEnabled(false);
				botonPausa.setEnabled(true);
			}
		});

		// init botonera
		botonera = new BotonSudoku[9][9];
		for (int i = 0; i < botonera.length; i++) {
			for (int j = 0; j < botonera[i].length; j++) {
				botonera[i][j] = new BotonSudoku(i, j, this);
				BotonSudoku button = botonera[i][j];
				gamePane.add(button);
				button.setBackground(new Color(240, 240, 240, 255));
				button.addActionListener(new OyenteBotonSudoku(button));
				button.setEnabled(false);
				// Marco los bordes correspondientes al los 9 paneles

			}
		}

		JLabel timer = juego.getReloj().getGrafica();
		timer.setPreferredSize(new Dimension(415, 50));
		optionPane.add(timer);
	}

	/**
	 * Inicia el juego desde la solucion dada.
	 * 
	 * @param f Archivo de la solucion a cargar, esta debe respetar el formato: Los
	 *          nros del 1 al 9 separados por espacio. Cada linea representa una
	 *          fila del juego.
	 */
	public void iniciarJuego(File f) {
		try {
			juego.iniciar(f);
			for (int i = 0; i < botonera.length; i++) {
				for (int j = 0; j < botonera[i].length; j++) {
					BotonSudoku boton = botonera[i][j];
					Celda c = juego.getCelda(i, j);
					Grafica g = c.getGrafica();
					ImageIcon icono = g.getIcon();
					ImageIcon iconoDesabilitado = g.getDisabledIcon();
					boton.setIcon(icono);
					boton.setDisabledIcon(iconoDesabilitado);
					// desabilito y guardo los nros iniciales
					if (!c.isEmpty()) {
						boton.setEnabled(false);
						botonesIniciales.add(boton);
					} else {
						boton.setBackground(new Color(224, 224, 224, 255));
						boton.setEnabled(true);
					}
					dibujarBordes(i, j);
				}
			}

		} catch (JuegoException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame,
					"Solucion No existe \n asegurese que la solucion este en la carpeta solucion\n con el nombre init.txt",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Actualiza la casilla de la fila y columna deseada con el valor deseado.
	 * 
	 * @param fila    Nro que representa la fila a la cual se quiere actualizar su
	 *                valor.
	 * @param columna Nro que representa la columna a la cual se quiere actualizar
	 *                su valor.
	 * @param valor   Nro al cual se actualizara la casilla
	 */
	public void actualizar(int fila, int columna, int valor) {
		Celda c = juego.getCelda(fila, columna);
		c.actualizar(valor);
		BotonSudoku b = botonera[fila][columna];
		b.setIcon(c.getGrafica().getIcon());
		if (c.isEmpty() || (juego.comprobarColumna(fila, columna) && juego.comprobarFila(fila, columna)
				&& juego.comprobarPanel(fila, columna))) {
			b.setBackground(new Color(224, 224, 224, 255));
			if (juego.esGanador()) {
				juego.terminar();
				JOptionPane.showMessageDialog(frame, "¡Felicidades has ganado!", "Victoria",
						JOptionPane.DEFAULT_OPTION);
			}
		} else {
			b.setBackground(Color.RED);
		}
	}

	/**
	 * Metodo utilizado para la creación de la grilla clasica de sudoku. Dibuja los
	 * bordes del boton en la posicion dada.
	 * 
	 * @param i Nro de fila del boton
	 * @param j Nro de columna del boton
	 */
	private void dibujarBordes(int i, int j) {
		if (i == 0 || i == 3 || i == 6) {
			if (j == 0 || j == 3 || j == 6)
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(3, 3, 1, 1, Color.black));
			else if (j == 8)
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 3, Color.black));
			else
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1, Color.black));
		} else if (i == 8) {
			if (j == 0 || j == 3 || j == 6)
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.black));
			else if (j == 8)
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
			else
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
		} else {
			if (j == 0 || j == 3 || j == 6)
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.black));
			else if (j == 8)
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
			else
				botonera[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		}
	}
}
