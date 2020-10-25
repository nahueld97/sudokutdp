package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Esta clase se utiliza para poder contar el tiempo.
 * 
 * @author Nahuel Diaz
 *
 */
public class Reloj {

	protected GraficaTimer grafica;
	protected Timer timer;
	protected int horas, minutos, segundos;

	/**
	 * Inicializa el reloj en 0.
	 */
	public Reloj() {
		grafica = new GraficaTimer();
		horas = 0;
		minutos = 0;
		segundos = 0;
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (segundos < 59) {
					segundos++;
				} else {
					if (minutos < 59) {
						segundos = 0;
						minutos++;
					} else {
						minutos = 0;
						horas++;
					}
				}
				grafica.actualizar(horas, minutos, segundos);
			}
		});
	}

	/**
	 * Inica el reloj en 0.
	 */
	public void inicio() {
		horas = minutos = segundos = 0;
		timer.restart();
		timer.start();
	}

	/**
	 * Detiene el reloj, esta operacion no reinicia el reloj.
	 */
	public void detener() {
		if (timer.isRunning())
			timer.stop();
	}

	/**
	 * Reanuda el reloj en el tiempo que este llevaba.
	 */
	public void reanudar() {
		if (timer.isRunning())
			timer.start();
	}

	/**
	 * Obtiene el grafico del reloj.
	 * 
	 * @return GraficaTimer grafica del reloj.
	 */
	public GraficaTimer getGrafica() {
		return grafica;
	}

	/**
	 * Obtiene el nro de horas actual del reloj.
	 * 
	 * @return nro de hora que tiene el reloj.
	 */
	public int getHoras() {
		return horas;
	}

	/**
	 * Obtiene la cantidad de minutos actual del reloj sin tener en cuenta las horas
	 * transcurridas.
	 * 
	 * @return nro de minutos que tiene el reloj.
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * Obtiene la cantidad de segundos actual del reloj sin tener en cuenta los
	 * minutos ni las horas transcurridas.
	 * 
	 * @return nro de segundos que tiene el reloj.
	 */
	public int getSegundos() {
		return segundos;
	}
}
